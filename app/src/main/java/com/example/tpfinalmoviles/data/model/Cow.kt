package com.example.tpfinalmoviles.data.model

import com.google.gson.annotations.SerializedName

data class Cow(
    @SerializedName("id")
    val id: Int,
    @SerializedName("cantidadPartos")
    val cantidadPartos: Int,
    @SerializedName("electronicId")
    val electronicId: Int,
    @SerializedName("fechaNacimiento")
    val fechaNacimiento: String,
    @SerializedName("herdId")
    val herdId: Int,
    @SerializedName("peso")
    val peso: Double,
    @SerializedName("ultimaFechaParto")
    val ultimaFechaParto: String?
)