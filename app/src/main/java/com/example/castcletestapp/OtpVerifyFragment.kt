package com.example.castcletestapp

import android.content.Context
import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import android.os.CountDownTimer
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.castcletestapp.databinding.OtpVerifyFragmentBinding

class OtpVerifyFragment : Fragment() {

    companion object {
        fun newInstance() = OtpVerifyFragment()
    }

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

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel = ViewModelProvider(this).get(OtpVerifyViewModel::class.java)

        activity?.getSharedPreferences("VERIFY_PREF", Context.MODE_PRIVATE).also {
            it?.getString("phoneNumber", "").apply {
                //Your code was sent to +66 89492 3675 format
                binding.tvOtpDecs.text = "Your code was sent to $this"
            }
        }
        count = object : CountDownTimer(60000, 100) {
            override fun onTick(time: Long) {
                Log.d("Beznnz", "onTick: ${time/1000} ")
                binding.tvResendTimer.text = "Resend Code ${time/1000}s"
            }

            override fun onFinish() {
                Log.d("Beznnz", "onFinish: 0")
            }
        }
        binding.tvResend.setOnClickListener {
            count?.cancel()
            (count as CountDownTimer).start()
        }
    }

    override fun onPause() {
        super.onPause()
        count?.cancel()
    }
}