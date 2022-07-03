package com.sample.viewpager2_sample.util

import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import androidx.viewpager2.widget.ViewPager2
import com.sample.viewpager2_sample.data.model.Contact
import com.sample.viewpager2_sample.ui.adapter.ContactAdapter

object BindingAdapters {

    @BindingAdapter("app:items")
    @JvmStatic
    fun setItems(viewPager2: ViewPager2, items: List<Contact>) {
        (viewPager2.adapter as ContactAdapter).submitList(items.toMutableList())
    }
}