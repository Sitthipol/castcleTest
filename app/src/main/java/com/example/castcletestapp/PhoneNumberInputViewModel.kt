package com.example.castcletestapp

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.castcletestapp.model.CountryList
import com.example.castcletestapp.repository.Repository

class PhoneNumberInputViewModel : ViewModel() {

    private lateinit var servicesLiveData: MutableLiveData<CountryList>
    fun getCountryList(): MutableLiveData<CountryList> {
        servicesLiveData = Repository.getCountry()

        return servicesLiveData
    }

}