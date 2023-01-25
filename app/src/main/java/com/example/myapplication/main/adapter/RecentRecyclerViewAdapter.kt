package com.example.myapplication.main.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.myapplication.databinding.ItemRecyclerRecentBinding
import com.example.myapplication.main.model.Category

class RecentRecyclerViewAdapter() :
    ListAdapter<Category, RecentRecyclerViewAdapter.CategoryViewHolder>(
        diffUtil
    ) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CategoryViewHolder {
        return CategoryViewHolder(
            ItemRecyclerRecentBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: CategoryViewHolder, position: Int) {
        holder.bind(currentList[position])
    }

    inner class CategoryViewHolder(private val binding: ItemRecyclerRecentBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(data: Category) {
            binding.data = data
        }
    }

    companion object {
        val diffUtil = object : DiffUtil.ItemCallback<Category>() {
            override fun areContentsTheSame(oldItem: Category, newItem: Category) =
                oldItem == newItem

            override fun areItemsTheSame(oldItem: Category, newItem: Category) =
                oldItem.id == newItem.id
        }
    }
}