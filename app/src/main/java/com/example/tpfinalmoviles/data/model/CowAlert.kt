package com.example.tpfinalmoviles.data.model

import com.google.gson.annotations.SerializedName

data class CowAlert(
    @SerializedName("id")
    val id: Int?,
    @SerializedName("cowId")
    val cowId: Int,
    @SerializedName("animalId")
    val animalId: Int?,
    @SerializedName("bcsThresholdMax")
    val bcsThresholdMax: Double,
    @SerializedName("bcsThresholdMin")
    val bcsThresholdMin: Double
)
