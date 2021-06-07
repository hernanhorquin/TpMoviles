package com.example.tpfinalmoviles.data.model

import com.google.gson.annotations.SerializedName

data class Herd(
    @SerializedName("id")
    val id: Int?,
    @SerializedName("location")
    val location: String
)