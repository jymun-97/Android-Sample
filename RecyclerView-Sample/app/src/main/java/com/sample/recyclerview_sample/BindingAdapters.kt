package com.sample.recyclerview_sample

import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView

object BindingAdapters {

    @BindingAdapter("app:items")
    @JvmStatic
    fun setItems(recyclerView: RecyclerView, items: List<Number>) {
        if (recyclerView.adapter == null) {
            recyclerView.adapter = NumberAdapter()
        }
        (recyclerView.adapter as NumberAdapter).submitList(items.toMutableList())
    }
}