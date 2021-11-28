package com.example.castcletestapp

import android.telephony.PhoneNumberUtils
import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class OtpVerifyViewModel : ViewModel() {
    private var otpVerityList: ArrayList<String> = ArrayList<String>()
    lateinit var otpVerifyEnable: MutableLiveData<Int>

    fun getOtpVerifyList(): MutableLiveData<Int> {
        otpVerifyEnable = MutableLiveData<Int>()
        return otpVerifyEnable
    }

    fun createOtpVerify(otpText: String, position: Int) {
        if (otpText.isNotEmpty()) {
            otpVerityList.add(otpText)
        }
        if (otpText.isEmpty()) {
            otpVerityList.removeAt(position)
        }
        otpVerifyEnable.value = otpVerityList.size
    }

    fun showOtp(): String {
        var otpText = ""
        for (otp in otpVerityList) {
            otpText += otp
        }
        return otpText
    }
}