package com.example.castcletestapp.model

import com.google.gson.annotations.SerializedName

data class CountryList(
    @SerializedName("payload")
    var countryList: ArrayList<Country>
)
