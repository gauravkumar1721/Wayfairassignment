package com.example.wayfairassignment.data.network

import com.example.wayfairassignment.domain.common.ErrorModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import retrofit2.HttpException
import retrofit2.Response
import java.lang.Exception

suspend fun <T, X> apiCall(
    apiCall: suspend () -> T,
    successTransform: (T) -> X
): com.example.wayfairassignment.domain.common.Result<Failure, X> {
    return when (val apiData = makeApiCall(apiCall, successTransform)) {
        is com.example.wayfairassignment.domain.common.Result.Success -> {
            apiData
        }
        is com.example.wayfairassignment.domain.common.Result.Error -> {
            apiData
        }
    }
}

suspend inline fun <T, X> makeApiCall(
    crossinline apiCall: suspend () -> T,
    successTransform: (T) -> X,
): com.example.wayfairassignment.domain.common.Result<Failure, X> {
    return try {
        val response = withContext(Dispatchers.IO) { apiCall.invoke() }
        if (response is Response<*>) {
            if (response.code() >= 400) {
                val errorMsg = response.errorBody().toString()
                com.example.wayfairassignment.domain.common.Result.Error(
                    Failure.DisplayableError(ErrorModel(errorMsg, response.code().toString()))
                )
            } else {
                com.example.wayfairassignment.domain.common.Result.Success(successTransform(response))
            }
        } else {
            com.example.wayfairassignment.domain.common.Result.Success(successTransform(response))
        }
    } catch (e: Exception) {
        return when (e) {
            is HttpException -> {
                com.example.wayfairassignment.domain.common.Result.Error(Failure.HttpError(ErrorModel("SomeThing went wrong", "")))
            }
            else -> {
                com.example.wayfairassignment.domain.common.Result.Error(Failure.DisplayableError(ErrorModel("Please try again", "")))
            }
        }
    }
}