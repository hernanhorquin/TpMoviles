package com.example.tpfinalmoviles.data.api

import com.example.tpfinalmoviles.data.model.Cow
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Query

interface CowApi {

    @POST("cow")
    fun createCow(@Body cow: Cow): Call<Cow>

    @GET("cow/1")
    fun getCow(): Call<Cow>
}