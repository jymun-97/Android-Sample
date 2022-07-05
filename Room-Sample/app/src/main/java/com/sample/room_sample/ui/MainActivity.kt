package com.sample.room_sample.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.sample.room_sample.R
import com.sample.room_sample.databinding.ActivityMainBinding
import com.sample.room_sample.ui.adapter.TodoAdapter
import com.sample.room_sample.ui.viewmodel.TodoViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private lateinit var binding : ActivityMainBinding
    private lateinit var todoAdapter: TodoAdapter
    private val todoViewModel by viewModels<TodoViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.apply {
            viewmodel = todoViewModel
            lifecycleOwner = this@MainActivity
        }

        todoAdapter = TodoAdapter(
            itemCheckBoxClicked = {
                todoViewModel.updateTodo(it)
            }
        )
        binding.recyclerView.apply {
            adapter = todoAdapter
            layoutManager = LinearLayoutManager(this@MainActivity)
        }
        setItemSwipeToLeft()
    }

    private fun setItemSwipeToLeft() {
        val itemTouchHelperCallback = object : ItemTouchHelper.SimpleCallback(
            0, ItemTouchHelper.LEFT
        ) {
            override fun onMove(
                recyclerView: RecyclerView,
                viewHolder: RecyclerView.ViewHolder,
                target: RecyclerView.ViewHolder
            ): Boolean = true

            override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
                val position = viewHolder.adapterPosition
                val target = todoAdapter.currentList[position]

                target?.let {
                    todoViewModel.deleteTodo(it)
                }
            }
        }
        ItemTouchHelper(itemTouchHelperCallback).attachToRecyclerView(binding.recyclerView)
    }
}