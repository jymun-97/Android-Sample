package com.sample.room_sample.data.repository

import com.sample.room_sample.data.model.Todo
import kotlinx.coroutines.flow.Flow

interface TodoRepository {

    fun getAll() : Flow<List<Todo>>

    suspend fun addTodo(todo: Todo)

    suspend fun updateTodo(todo: Todo)

    suspend fun deleteTodo(todo: Todo)
}