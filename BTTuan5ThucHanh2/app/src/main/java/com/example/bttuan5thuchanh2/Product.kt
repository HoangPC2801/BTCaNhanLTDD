package com.example.bttuan5thuchanh2

import com.google.gson.annotations.SerializedName

data class Product(
    @SerializedName("id") val id: String = "",
    @SerializedName("name") val name: String = "",
    @SerializedName("price") val price: Double = 0.0,
    @SerializedName("des") val description: String = "",
    @SerializedName("imgURL") val image: String = ""
)