package com.sample.recyclerview_advanced_sample.util

import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import com.sample.recyclerview_advanced_sample.data.model.Contact
import com.sample.recyclerview_advanced_sample.ui.adapter.ContactAdapter

object BindingAdapters {

    @BindingAdapter("app:items")
    @JvmStatic
    fun setItems(recyclerView: RecyclerView, items: ArrayList<Contact>) {
        (recyclerView.adapter as ContactAdapter).submitList(items.toMutableList())
    }
}