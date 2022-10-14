package com.example.hs_test.view.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.hs_test.R
import com.example.hs_test.data.Product
import com.example.hs_test.databinding.ProductBinding
import com.example.hs_test.util.load

class ProductsAdapter : ListAdapter<Product, ProductsAdapter.ViewHolder>(DiffCallback()) {

    inner class ViewHolder(private val binding: ProductBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bindData(product: Product) {
            binding.apply {
                productTitle.text = product.name
                productDescription.text = product.description
                productPrice.text = itemView.context.getString(R.string.price_format, product.price)
                productImage.load(product.img)
            }


        }
    }

    private class DiffCallback : DiffUtil.ItemCallback<Product>() {
        override fun areItemsTheSame(oldItem: Product, newItem: Product) = oldItem.id == newItem.id

        override fun areContentsTheSame(oldItem: Product, newItem: Product) = oldItem == newItem
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            ProductBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        )
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bindData(getItem(position))
    }

}
