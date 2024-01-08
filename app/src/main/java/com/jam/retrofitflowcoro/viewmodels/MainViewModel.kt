package com.jam.retrofitflowcoro.viewmodels

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.jam.retrofitflowcoro.repository.ApiResult
import com.jam.retrofitflowcoro.model.Product
import com.jam.retrofitflowcoro.repository.RetrofitService
import com.jam.retrofitflowcoro.repository.toResultFlow
import kotlinx.coroutines.launch

class MainViewModel : ViewModel() {

    private var mLiveDataProduct: MutableLiveData<ApiResult<List<Product?>>>? = MutableLiveData<ApiResult<List<Product?>>>()

     fun getProduct(): MutableLiveData<ApiResult<List<Product?>>>? {
         viewModelScope.launch {
             val flow = toResultFlow { RetrofitService.getInstance().getAllProduct()  }
             flow.collect{ result ->
                  mLiveDataProduct?.value = result
             }
         }
      return mLiveDataProduct
     }
}