package com.example.castcletestapp

import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ArrayAdapter
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.example.castcletestapp.databinding.PhoneNumberInputFragmentBinding

class PhoneNumberInputFragment : Fragment(), AdapterView.OnItemSelectedListener {

    private lateinit var binding: PhoneNumberInputFragmentBinding
    private lateinit var viewModel: PhoneNumberInputViewModel
    private lateinit var mContext: Context
    var countryDialCode = ArrayList<String>()

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

        var countryName = ArrayList<String>()

        binding.apply {
            viewModel.getCountryList().observe(viewLifecycleOwner, Observer {
                countryName.clear()
                countryDialCode.clear()
                for (data in it.countryList) {
                    countryName.add(data.name)
                    countryDialCode.add(data.dialCode)
                }

                val adapter = ArrayAdapter(
                    mContext,
                    R.layout.spinner_item_list,
                    countryName
                )
                adapter.setDropDownViewResource(R.layout.spinner_item_list)
                this.spinnerCountryList.adapter = adapter
                this.spinnerCountryList.onItemSelectedListener = this@PhoneNumberInputFragment
                this.spinnerCountryList.setSelection(adapter.getPosition("Thailand"))
            })
            this.btnSendOtp.setOnClickListener {
                val bundle = bundleOf("phoneNumber" to "${binding.phoneNumberHeaders.text}${edPhoneNumber.text}")
                findNavController().navigate(R.id.otpVerifyFragment, bundle)
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
        Log.d("Beznnz", "nothing Selected")
    }
}