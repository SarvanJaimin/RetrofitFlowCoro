package com.jam.retrofitflowcoro.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.jam.retrofitflowcoro.databinding.AdapterProductBinding
import com.jam.retrofitflowcoro.model.Product


class ProductAdapter : RecyclerView.Adapter<MainViewHolder>() {


    private var productList = mutableListOf<Product?>()

    fun setProduct(products: MutableList<Product?>) {
        this.productList = products
        notifyDataSetChanged()
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MainViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = AdapterProductBinding.inflate(inflater, parent, false)
        return MainViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return productList.size
    }

    override fun onBindViewHolder(holder: MainViewHolder, position: Int) {
            val product = productList[position]
            holder.binding.name.text = product?.name
            Glide.with(holder.itemView.context).load(product?.imageUrl).into(holder.binding.imageview)

    }


}

class MainViewHolder(val binding: AdapterProductBinding) : RecyclerView.ViewHolder(binding.root) {

}