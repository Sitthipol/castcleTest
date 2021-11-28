package com.example.castcletestapp

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Spinner
import androidx.core.content.ContextCompat
import androidx.core.os.bundleOf
import androidx.core.widget.doAfterTextChanged
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.example.castcletestapp.databinding.PhoneNumberInputFragmentBinding
import java.util.regex.Pattern

class PhoneNumberInputFragment : Fragment(), AdapterView.OnItemSelectedListener,
    View.OnClickListener {

    private lateinit var binding: PhoneNumberInputFragmentBinding
    private lateinit var viewModel: PhoneNumberInputViewModel
    private lateinit var mContext: Context


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
                viewModel.setCountryList(it)
            })

            viewModel.getCountryCodeList().observe(viewLifecycleOwner, Observer {
                setSpinnerAdapter(it, this.spinnerCountryList)
            })

            setSpinnerAdapter(viewModel.getDefaultSpinner(), this.spinnerCountryList)

            edPhoneNumber.doAfterTextChanged {
                if (!it.isNullOrBlank() && Pattern.matches("^[+]?[0-9]{9,13}\$", it)) {
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

    private fun setSpinnerAdapter(data: ArrayList<String>, spinnerCountryList: Spinner){
        val adapter = ArrayAdapter(
            mContext,
            R.layout.spinner_item_list,
            data
        )
        adapter.setDropDownViewResource(R.layout.spinner_item_list)
        spinnerCountryList.adapter = adapter
        spinnerCountryList.onItemSelectedListener = this@PhoneNumberInputFragment
        spinnerCountryList.setSelection(adapter.getPosition("TH"))
        adapter.notifyDataSetChanged()
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
        viewModel.getCountryDialCodeList().observe(viewLifecycleOwner, Observer {
            binding.phoneNumberHeaders.text = it[position]
        })
        binding.edPhoneNumber.requestFocus()
    }

    override fun onNothingSelected(adapterView: AdapterView<*>?) {

    }

    override fun onClick(view: View?) {
        when (view?.id) {
            R.id.btn_send_otp -> {
                val bundle =
                    bundleOf("phoneNumber" to "${binding.edPhoneNumber.text}")
                bundle.putString("countryCode", "${binding.phoneNumberHeaders.text}")
                findNavController().navigate(R.id.otpVerifyFragment, bundle)
            }
        }
    }

}