package com.sample.room_sample.util

import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import com.sample.room_sample.data.model.Todo
import com.sample.room_sample.ui.adapter.TodoAdapter

object BindingAdapters {

    @BindingAdapter("app:items")
    @JvmStatic
    fun setItems(recyclerView: RecyclerView, items: List<Todo>) {
        (recyclerView.adapter as TodoAdapter).submitList(items.toMutableList())
    }
}