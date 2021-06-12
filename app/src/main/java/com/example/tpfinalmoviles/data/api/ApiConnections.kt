package com.example.tpfinalmoviles.data.api

import com.example.tpfinalmoviles.data.model.*
import retrofit2.Call
import retrofit2.http.*

interface ApiConnections {

    @POST("cow")
    fun createCow(@Body cow: Cow): Call<Cow>

    @GET("cow/{id}")
    fun getCow(@Path("id") id: Int): Call<Cow>

    @POST("herd")
    fun createHerd(@Body herd: Herd): Call<Herd>

    @GET("herd/{id}")
    fun getHerd(@Path("id") id: Int): Call<Herd>

    @POST("cowAlert")
    fun addCowAlert(@Body cowAlert: CowAlert): Call<CowAlert>

    @POST("herdAlert")
    fun addHerdAlert(@Body herdAlert: HerdAlert): Call<HerdAlert>

    @GET("cowFiredAlert")
    fun getCowAlerts(): Call<List<CowFiredAlert>>

    @GET("herdFiredAlert")
    fun getHerdAlerts(): Call<List<HerdFiredAlert>>

    @POST("session")
    fun toggleSession(@Body session: Session): Call<Boolean>

}