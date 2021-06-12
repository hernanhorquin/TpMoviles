package com.example.tpfinalmoviles.data.model

import com.google.gson.annotations.SerializedName

data class HerdFiredAlert(
    @SerializedName("id")
    val id: Int,
    @SerializedName("herd")
    val herd: Herd,
    @SerializedName("bcs_fired")
    val bcsFired: Double,
    @SerializedName("fecha")
    val fecha: String
)

