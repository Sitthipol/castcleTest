package com.example.castcletestapp.repository

import com.example.castcletestapp.service.RetrofitClient
import android.util.Log
import androidx.lifecycle.MutableLiveData
import com.example.castcletestapp.model.Country
import com.example.castcletestapp.model.CountryList
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

object Repository {
    val countryLiveData = MutableLiveData<CountryList>()

    fun getCountry(): MutableLiveData<CountryList> {

        val call = RetrofitClient.apiInterface.getCountry()

        call.enqueue(object : Callback<CountryList> {
            override fun onFailure(call: Call<CountryList>, t: Throwable) {
                Log.v("DEBUG : Failure ", t.message.toString())
            }

            override fun onResponse(
                call: Call<CountryList>,
                response: Response<CountryList>
            ) {
                Log.v("DEBUG : Response ", response.body().toString())

                val data = response.body()

                data!!.apply {
//                    val code = code
//                    val dialCode = dialCode
//                    val name = name
//                    val flag = flag
                }
                    countryLiveData.value = CountryList(data.countryList)
            }
        })

        return countryLiveData
    }
}