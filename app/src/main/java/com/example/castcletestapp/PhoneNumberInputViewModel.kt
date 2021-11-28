package com.example.castcletestapp

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.castcletestapp.model.CountryList
import com.example.castcletestapp.repository.Repository

class PhoneNumberInputViewModel : ViewModel() {

    private var countryListLiveData = MutableLiveData<CountryList>()
    private var countryCodeListLiveData = MutableLiveData<ArrayList<String>>()
    private var countryDialCodeListLiveData = MutableLiveData<ArrayList<String>>()
    fun getCountryList(): MutableLiveData<CountryList> {
        countryListLiveData = Repository.getCountry()
        return countryListLiveData
    }

    fun setCountryList(countryList: CountryList) {
        var countryDialCode = ArrayList<String>()
        var countryCode = ArrayList<String>()
        countryCode.clear()
        countryDialCode.clear()
        for (data in countryList.countryList) {
            countryCode.add(data.code)
            countryDialCode.add(data.dialCode)
        }
        countryCodeListLiveData.value = countryCode
        countryDialCodeListLiveData.value = countryDialCode
    }

    fun getCountryCodeList(): MutableLiveData<ArrayList<String>> = countryCodeListLiveData
    fun getCountryDialCodeList(): MutableLiveData<ArrayList<String>> = countryDialCodeListLiveData

    fun getDefaultSpinner(): ArrayList<String> {
        var defaultArray = ArrayList<String>()
        defaultArray.add("TH")
        return defaultArray
    }

}