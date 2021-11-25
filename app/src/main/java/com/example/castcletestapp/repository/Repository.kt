package com.example.castcletestapp.repository

import com.example.castcletestapp.service.RetrofitClient
import android.util.Log
import androidx.lifecycle.MutableLiveData
import com.example.castcletestapp.model.Country
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

object Repository {
    val countryLiveData = MutableLiveData<Country>()

    fun getCountry(): MutableLiveData<Country> {

        val call = RetrofitClient.apiInterface.getCountry()

        call.enqueue(object: Callback<Country> {
            override fun onFailure(call: Call<Country>, t: Throwable) {
                Log.v("DEBUG : ", t.message.toString())
            }

            override fun onResponse(
                call: Call<Country>,
                response: Response<Country>
            ) {
                Log.v("DEBUG : ", response.body().toString())

                val data = response.body()

                val countryList = data!!.country

                countryLiveData.value = Country(countryList)
            }
        })

        return countryLiveData
    }
}