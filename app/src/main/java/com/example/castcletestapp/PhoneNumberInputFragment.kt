package com.example.castcletestapp

import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.EditText
import androidx.core.content.ContextCompat
import androidx.core.os.bundleOf
import androidx.core.widget.addTextChangedListener
import androidx.core.widget.doAfterTextChanged
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.example.castcletestapp.databinding.PhoneNumberInputFragmentBinding

class PhoneNumberInputFragment : Fragment(), AdapterView.OnItemSelectedListener,
    View.OnClickListener {

    private lateinit var binding: PhoneNumberInputFragmentBinding
    private lateinit var viewModel: PhoneNumberInputViewModel
    private lateinit var mContext: Context
    var countryDialCode = ArrayList<String>()
    var countryCode = ArrayList<String>()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = PhoneNumberInputFragmentBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel = ViewModelProvider(this).get(PhoneNumberInputViewModel::class.java)

        binding.apply {
            viewModel.getCountryList().observe(viewLifecycleOwner, Observer {
                countryCode.clear()
                countryDialCode.clear()
                for (data in it.countryList) {
                    countryCode.add(data.code)
                    countryDialCode.add(data.dialCode)
                }

                val adapter = ArrayAdapter(
                    mContext,
                    R.layout.spinner_item_list,
                    countryCode
                )
                adapter.setDropDownViewResource(R.layout.spinner_item_list)
                this.spinnerCountryList.adapter = adapter
                this.spinnerCountryList.onItemSelectedListener = this@PhoneNumberInputFragment
                this.spinnerCountryList.setSelection(adapter.getPosition("TH"))
            })

            edPhoneNumber.doAfterTextChanged {
                if (!it.isNullOrBlank()) {
                    this.btnSendOtp.isEnabled = true
                    this.btnSendOtp.setOnClickListener(this@PhoneNumberInputFragment)
                    this.btnSendOtp.setTextColor(ContextCompat.getColor(mContext, R.color.white))
                    this.btnSendOtp.setBackgroundResource(R.drawable.light_blue_round_background)
                }else {
                    this.btnSendOtp.isEnabled = false
                    this.btnSendOtp.setTextColor(ContextCompat.getColor(mContext, R.color.disable_black))
                    this.btnSendOtp.setBackgroundResource(R.drawable.black_round_background)
                }
            }

        }
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        this.mContext = context
    }

    override fun onItemSelected(
        adapterView: AdapterView<*>?,
        view: View?,
        position: Int,
        id: Long
    ) {
        binding.phoneNumberHeaders.text = countryDialCode[position]
    }

    override fun onNothingSelected(adapterView: AdapterView<*>?) {

    }

    override fun onClick(view: View?) {
        when (view?.id) {
            R.id.btn_send_otp -> {
                val bundle =
                    bundleOf("phoneNumber" to "${binding.phoneNumberHeaders.text}${binding.edPhoneNumber.text}")
                findNavController().navigate(R.id.otpVerifyFragment, bundle)
            }
        }
    }

}