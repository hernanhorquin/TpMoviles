package com.example.tpfinalmoviles.data.repository

import com.example.tpfinalmoviles.data.api.CowApi
import com.example.tpfinalmoviles.data.model.Cow
import com.example.tpfinalmoviles.utils.Result
import java.lang.Exception

class CowRepository {

    private val apiService = CowRequestGenerator()

    fun createCow(cow: Cow): Result<Cow> {
        val callResponse = apiService.createService(CowApi::class.java)
            .createCow(cow)
        val response = callResponse.execute()

        response.let {
            if (response.isSuccessful)
                response.body()?.let { cow ->
                    return Result.Success(cow)
                }
        }
        return Result.Failure(Exception("bad_request"))
    }

    fun getCow(): Result<Cow> {
        val callResponse = apiService.createService(CowApi::class.java)
            .getCow()
        val response = callResponse.execute()

        response.let {
            if (response.isSuccessful)
                response.body()?.let { cow ->
                    return Result.Success(cow)
                }
        }
        return Result.Failure(Exception("bad_request"))
    }
}