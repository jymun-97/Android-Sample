package com.sample.room_sample.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.sample.room_sample.data.model.Todo
import com.sample.room_sample.databinding.ItemTodoBinding

class TodoAdapter(
    private val itemCheckBoxClicked: (Todo) -> Unit
): ListAdapter<Todo, TodoAdapter.ViewHolder>(diffUtil) {

    inner class ViewHolder(
        private val binding: ItemTodoBinding
    ) : RecyclerView.ViewHolder(binding.root) {

        fun bind(todo: Todo) {
            binding.todo = todo

            binding.checkbox.setOnCheckedChangeListener { _, isChecked ->
                itemCheckBoxClicked(
                    Todo(todo.id, todo.task, isChecked)
                )
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            ItemTodoBinding.inflate(
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
        val diffUtil = object : DiffUtil.ItemCallback<Todo>() {
            override fun areItemsTheSame(oldItem: Todo, newItem: Todo): Boolean {
                return oldItem.id == newItem.id
            }

            override fun areContentsTheSame(oldItem: Todo, newItem: Todo): Boolean {
                return oldItem == newItem
            }

        }
    }
}