package com.example.castcletestapp

import android.annotation.SuppressLint
import android.content.Context
import android.os.Bundle
import android.os.CountDownTimer
import android.telephony.PhoneNumberUtils
import android.text.Editable
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import android.widget.Toast
import androidx.core.content.ContextCompat
import androidx.core.widget.doAfterTextChanged
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.castcletestapp.databinding.OtpVerifyFragmentBinding

class OtpVerifyFragment : Fragment() {

    companion object {
        fun newInstance() = OtpVerifyFragment()
    }

    private lateinit var mContext: Context
    private var count: CountDownTimer? = null
    private lateinit var binding: OtpVerifyFragmentBinding
    private lateinit var viewModel: OtpVerifyViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = OtpVerifyFragmentBinding.inflate(layoutInflater)
        return binding.root
    }

    @SuppressLint("SetTextI18n")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel = ViewModelProvider(this).get(OtpVerifyViewModel::class.java)

        count = object : CountDownTimer(60000, 100) {
            override fun onTick(time: Long) {
                binding.tvResendTimer.text = "Resend Code ${time / 1000}s"
            }

            override fun onFinish() {

            }
        }.start()

        binding.otpVerify.apply {
            edittextTextWatcher(this.edPin1)
            edittextTextWatcher(this.edPin2)
            edittextTextWatcher(this.edPin3)
            edittextTextWatcher(this.edPin4)
            edittextTextWatcher(this.edPin5)
            edittextTextWatcher(this.edPin6)
        }

        binding.apply {

            this.tvOtpDecs.text = getString(R.string.otp_code_sent__with_phone_number) + " ${arguments?.getString("countryCode")} ${PhoneNumberUtils.formatNumber(arguments?.getString("phoneNumber"))}"
            this.btnNext.setOnClickListener {
                Toast.makeText(mContext, "OTP verify ${viewModel.showOtp()}", Toast.LENGTH_SHORT).show()
            }

            viewModel.getOtpVerifyList().observe(viewLifecycleOwner, Observer { otpSize ->
                if (otpSize == 6) {
                    this.btnNext.setBackgroundResource(R.drawable.light_blue_round_background)
                    this.btnNext.setTextColor(ContextCompat.getColor(mContext, R.color.white))
                } else {
                    this.btnNext.setBackgroundResource(R.drawable.black_round_background)
                    this.btnNext.setTextColor(
                        ContextCompat.getColor(
                            mContext,
                            R.color.disable_black
                        )
                    )
                }

            })
        }

        binding.tvResend.setOnClickListener {
            count?.cancel()
            (count as CountDownTimer).start()
        }
    }

    private fun otpVerifyView(
        nextView: View,
        prevView: View,
        editText: Editable,
        position: Int
    ) {
        if (editText.toString().isNotEmpty()) {
            nextView.requestFocus()
        }
        if (editText.toString().isEmpty()) {
            prevView.requestFocus()
        }
        viewModel.createOtpVerify(editText.toString(), position)
    }

    private fun edittextTextWatcher(editText: EditText) {
        editText.doAfterTextChanged {
            if (it != null) {
                binding.otpVerify.apply {
                    when (editText) {
                        this.edPin1 -> {
                            otpVerifyView(this.edPin2, this.edPin1, it, 0)
                        }
                        this.edPin2 -> {
                            otpVerifyView(this.edPin3, this.edPin1, it, 1)
                        }
                        this.edPin3 -> {
                            otpVerifyView(this.edPin4, this.edPin2, it, 2)
                        }
                        this.edPin4 -> {
                            otpVerifyView(this.edPin5, this.edPin3, it, 3)
                        }
                        this.edPin5 -> {
                            otpVerifyView(this.edPin6, this.edPin4, it, 4)
                        }
                        this.edPin6 -> {
                            otpVerifyView(this.edPin6, this.edPin5, it, 5)
                        }
                    }
                }
            }

        }
    }

    override fun onPause() {
        super.onPause()
        count?.cancel()
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        this.mContext = context
    }
}