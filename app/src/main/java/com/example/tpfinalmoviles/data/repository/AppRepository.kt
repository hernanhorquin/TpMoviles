package com.example.tpfinalmoviles.data.repository

import com.example.tpfinalmoviles.data.api.ApiConnections
import com.example.tpfinalmoviles.data.model.*
import com.example.tpfinalmoviles.utils.Result
import java.lang.Exception

class AppRepository {
    private val apiService = DatabaseRequestGenerator()

    fun createCow(cow: Cow): Result<Cow> {
        val callResponse = apiService.createService(ApiConnections::class.java)
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

    fun getCow(id: Int): Result<Cow> {
        val callResponse = apiService.createService(ApiConnections::class.java)
            .getCow(id)
        val response = callResponse.execute()

        response.let {
            if (response.isSuccessful)
                response.body()?.let { cow ->
                    return Result.Success(cow)
                }
        }
        return Result.Failure(Exception("bad_request"))
    }

    fun getHerd(id: Int): Result<Herd> {
        val callResponse = apiService.createService(ApiConnections::class.java)
            .getHerd(id)
        val response = callResponse.execute()

        response.let {
            if (response.isSuccessful)
                response.body()?.let { herd ->
                    return Result.Success(herd)
                }
        }
        return Result.Failure(Exception("bad_request"))
    }

    fun createHerd(herd: Herd): Result<Herd> {
        val callResponse = apiService.createService(ApiConnections::class.java)
            .createHerd(herd)
        val response = callResponse.execute()

        response.let {
            if (response.isSuccessful)
                response.body()?.let { herd ->
                    return Result.Success(herd)
                }
        }
        return Result.Failure(Exception("bad_request"))
    }

    fun addHerdAlert(herdAlert: HerdAlert): Result<HerdAlert> {
        val callResponse = apiService.createService(ApiConnections::class.java)
            .addHerdAlert(herdAlert)
        val response = callResponse.execute()

        response.let {
            if (response.isSuccessful)
                response.body()?.let { herdAlert ->
                    return Result.Success(herdAlert)
                }
        }
        return Result.Failure(Exception("bad_request"))
    }

    fun addCowAlert(cowAlert: CowAlert): Result<CowAlert> {
        val callResponse = apiService.createService(ApiConnections::class.java)
            .addCowAlert(cowAlert)
        val response = callResponse.execute()

        response.let {
            if (response.isSuccessful)
                response.body()?.let { cowAlert ->
                    return Result.Success(cowAlert)
                }
        }
        return Result.Failure(Exception("bad_request"))
    }

    fun getCowAlerts(): Result<List<CowFiredAlert>> {
        val callResponse = apiService.createService(ApiConnections::class.java)
            .getCowAlerts()
        val response = callResponse.execute()

        response.let {
            if (response.isSuccessful) {
                response.body().let { cowAlerts ->
                    return Result.Success(cowAlerts)
                }
            }
        }
        return Result.Failure(Exception("bad_request"))
    }

    fun getHerdAlerts(): Result<List<HerdFiredAlert>> {
        val callResponse = apiService.createService(ApiConnections::class.java)
            .getHerdAlerts()
        val response = callResponse.execute()

        response.let {
            if (response.isSuccessful)
                response.body()?.let { herdAlerts ->
                    return Result.Success(herdAlerts)
                }
        }
        return Result.Failure(Exception("bad_request"))
    }

    fun toggleSession(session: Session): Result<Boolean> {
        val callResponse = apiService.createService(ApiConnections::class.java)
            .toggleSession(session)
        val response = callResponse.execute()

        response.let {
            if (response.isSuccessful)
                response.body()?.let { sessionEnabled ->
                    return Result.Success(sessionEnabled)
                }
        }
        return Result.Failure(Exception("bad_request"))
    }

}