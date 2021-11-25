package com.example.castcletestapp.service

import com.example.castcletestapp.model.Country
import retrofit2.Call
import retrofit2.http.GET

interface ApiInterface {
    @GET
    fun getCountry() : Call<Country>
}