package com.example.tpfinalmoviles.data.model

import com.google.gson.annotations.SerializedName

data class Cow(
    @SerializedName("id")
    val id: Int?,
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
    val ultimaFechaParto: String?,
    @SerializedName("cc")
    val cc: Double?
)

/*
{
    "id": 1,
    "electronicId": 1,
    "fechaNacimiento": "2017-09-17T03:00:00.000+0000",
    "ultimaFechaParto": null,
    "cantidadPartos": 0,
    "peso": 450.5,
    "herdId": 1,
    "cowBcsId": 0,
    "fechaBcs": null,
    "cc": 0
}
 */