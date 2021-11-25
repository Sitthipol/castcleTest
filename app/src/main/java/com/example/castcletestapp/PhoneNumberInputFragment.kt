package com.example.castcletestapp

import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.castcletestapp.databinding.PhoneNumberInputFragmentBinding

class PhoneNumberInputFragment : Fragment() {

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
        var listCountry = ArrayList<Int>()

        for (i in 1..4){
            listCountry.add(i)
        }

        viewModel.getCountryList().observe(viewLifecycleOwner, Observer {
            for (item in it.countryList) {
                Log.d("Beznnz"," code: ${item.code}")
            }
        })
        val adapter = ArrayAdapter(mContext, android.R.layout.simple_spinner_dropdown_item, listCountry)
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)

        binding.apply {
            btnSendOtp.setOnClickListener { Log.d("Beznnz", "test") }
            countryList.adapter = adapter
        }
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        this.mContext = context
    }
}