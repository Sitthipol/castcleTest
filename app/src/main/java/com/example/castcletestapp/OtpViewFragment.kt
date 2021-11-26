package com.example.castcletestapp

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import com.example.castcletestapp.databinding.FragmentOtpViewBinding
import com.example.castcletestapp.databinding.OtpVerifyFragmentBinding

class OtpViewFragment : Fragment() {

    private lateinit var viewModel: OtpVerifyViewModel
    private lateinit var binding: FragmentOtpViewBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentOtpViewBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel = ViewModelProvider(this).get(OtpVerifyViewModel::class.java)

    }
}