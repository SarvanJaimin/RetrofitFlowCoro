package com.jam.retrofitflowcoro.repository

import androidx.lifecycle.MutableLiveData


class ApiResultHandler<T>(private val onSuccess: (T?) -> Unit, private val onFailure: (T?) -> Unit)  {


    var loading = MutableLiveData<Boolean>()

    fun handleApiResult(result: ApiResult<T?>) {
        when (result.status) {
            ApiStatus.LOADING -> {
               loading.value = true
            }

            ApiStatus.SUCCESS -> {
                loading.value = false
                onSuccess(result.data)
            }

            ApiStatus.ERROR -> {
                loading.value = false
                onFailure(result.data)
            }
        }
    }
}