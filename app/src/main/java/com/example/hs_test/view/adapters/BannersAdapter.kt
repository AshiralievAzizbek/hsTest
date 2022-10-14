package com.example.hs_test.view.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.hs_test.data.model.Banner
import com.example.hs_test.databinding.BannerBinding
import com.example.hs_test.util.load

class BannersAdapter : ListAdapter<Banner, BannersAdapter.ViewHolder>(DiffCallback()) {

    inner class ViewHolder(private val binding: BannerBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(item: Banner) {
            binding.bannerImage.load(item.url)
        }
    }

    private class DiffCallback : DiffUtil.ItemCallback<Banner>() {
        override fun areItemsTheSame(oldItem: Banner, newItem: Banner): Boolean =
            oldItem.id == newItem.id

        override fun areContentsTheSame(oldItem: Banner, newItem: Banner): Boolean {
            return oldItem == newItem
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(BannerBinding.inflate(LayoutInflater.from(parent.context), parent, false))
    }

    override fun onBindViewHolder(holder: BannersAdapter.ViewHolder, position: Int) {
        holder.bind(getItem(position))
    }


}