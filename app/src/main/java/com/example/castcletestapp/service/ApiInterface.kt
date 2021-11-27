package com.example.castcletestapp.service

import com.example.castcletestapp.model.Country
import com.example.castcletestapp.model.CountryList
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Headers

interface ApiInterface {

    @Headers(
        "Accept-Version: 1.0",
        "Accept-Language: en"
    )
    @GET("metadata/country")
    fun getCountry() : Call<CountryList>

}