package com.example.tpfinalmoviles.data.model

import com.google.gson.annotations.SerializedName

data class CowFiredAlert(
    @SerializedName("id")
    val id: Int?,
    @SerializedName("cow")
    val cow: Cow,
    @SerializedName("bcs_fired")
    val bcsFired: Double,
    @SerializedName("fecha")
    val fecha: String
)
