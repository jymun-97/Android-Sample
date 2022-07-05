package com.sample.room_sample.ui.viewmodel

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.sample.room_sample.data.model.Todo
import com.sample.room_sample.data.repository.TodoRepositoryImpl
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class TodoViewModel @Inject constructor(
    private val repo: TodoRepositoryImpl
) : ViewModel() {


    val todoList : StateFlow<List<Todo>> =
        repo.getAll().stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5000),
            initialValue = emptyList()
        )

    val inputTask = MutableLiveData<String?>()

    fun insertTodo() = viewModelScope.launch(Dispatchers.IO) {
        inputTask.value ?: return@launch
        repo.addTodo(
            Todo(task = inputTask.value!!)
        )
    }

    fun updateTodo(todo: Todo) = viewModelScope.launch(Dispatchers.IO) {
        repo.updateTodo(todo)
    }

    fun deleteTodo(todo: Todo) = viewModelScope.launch(Dispatchers.IO) {
        repo.deleteTodo(todo)
    }
}