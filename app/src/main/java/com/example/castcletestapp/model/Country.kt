package com.example.castcletestapp.model

import com.google.gson.annotations.SerializedName

data class Country(
    @SerializedName("code")
    var code: String,
    @SerializedName("dialCode")
    var dialCode: String,
    @SerializedName("name")
    var name: String,
    @SerializedName("flag")
    var flag: String
)

