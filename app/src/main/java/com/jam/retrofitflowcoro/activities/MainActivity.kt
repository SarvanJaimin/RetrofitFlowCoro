package com.jam.retrofitflowcoro.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.lifecycle.ViewModelProvider
import com.jam.retrofitflowcoro.repository.ApiResultHandler
import com.jam.retrofitflowcoro.model.Product
import com.jam.retrofitflowcoro.adapter.ProductAdapter
import com.jam.retrofitflowcoro.databinding.ActivityMainBinding
import com.jam.retrofitflowcoro.viewmodels.MainViewModel

class MainActivity : AppCompatActivity() {

    lateinit var viewModel: MainViewModel
    private val adapter = ProductAdapter()
    lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.recyclerview.adapter = adapter

        viewModel = ViewModelProvider(this).get(MainViewModel::class.java)

        viewModel.getProduct()?.observe(this){result ->
            Log.e("result==>",result?.data?.size.toString())
            val apiResultHandler = ApiResultHandler<List<Product?>?>(
                onSuccess = {
                    data -> manageData(data) }, onFailure = {} )

            apiResultHandler.handleApiResult(result)
            apiResultHandler.loading.observe(this) {
                binding.progressDialog.visibility = if (it) View.VISIBLE else View.GONE
            }
        }

    }

    private fun manageData(product: List<Product?>?){
        product?.toMutableList()?.let { adapter.setProduct(it) }
    }
}