package com.example.tpfinalmoviles.data.model

import com.google.gson.annotations.SerializedName

data class HerdAlert(
    @SerializedName("id")
    val id: Int?,
    @SerializedName("herdId")
    val herdId: Int,
    @SerializedName("bcsThresholdMax")
    val bcsThresholdMax: Double,
    @SerializedName("bcsThresholdMin")
    val bcsThresholdMin: Double
)
