package com.jam.retrofitflowcoro.repository

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn

import retrofit2.Response

fun <T> toResultFlow(call: suspend () -> Response<T>?) : Flow<ApiResult<T>?> {

    return flow {
        emit(ApiResult.Loading(null, true))
        val c = call()  /* have to initialize the call method first*/
        c?.let {
            try {
                if (c.isSuccessful && c.body() != null) {
                    c.body()?.let {
                        emit(ApiResult.Success(it))
                    }
                } else {
                    c.errorBody()?.let {
                        emit(ApiResult.Error(it.string()))
                    }
                }
            }catch (e: Exception){
                emit(ApiResult.Error(e.toString()))
            }
            }
    }.flowOn(Dispatchers.IO)
}