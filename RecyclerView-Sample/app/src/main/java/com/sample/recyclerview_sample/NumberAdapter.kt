package com.sample.recyclerview_sample

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.sample.recyclerview_sample.databinding.ItemNumberBinding

class NumberAdapter: ListAdapter<Number, NumberAdapter.ViewHolder>(diffUtil) {

    inner class ViewHolder(
        private val binding: ItemNumberBinding
    ): RecyclerView.ViewHolder(binding.root) {

        fun bind(number: Number) {
            binding.number = number
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            ItemNumberBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(currentList[position])
    }

    companion object {
        val diffUtil = object : DiffUtil.ItemCallback<Number>() {
            override fun areItemsTheSame(oldItem: Number, newItem: Number): Boolean
                = oldItem.value == newItem.value

            override fun areContentsTheSame(oldItem: Number, newItem: Number): Boolean
                = oldItem == newItem
        }
    }
}