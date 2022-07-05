package com.sample.room_sample.data.repository

import com.sample.room_sample.data.db.TodoDatabase
import com.sample.room_sample.data.model.Todo
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class TodoRepositoryImpl @Inject constructor(
    private val db: TodoDatabase
) : TodoRepository {

    override fun getAll(): Flow<List<Todo>> = db.todoDao().getAll()

    override suspend fun addTodo(todo: Todo) = db.todoDao().addTodo(todo)

    override suspend fun updateTodo(todo: Todo) = db.todoDao().updateTodo(todo)

    override suspend fun deleteTodo(todo: Todo) = db.todoDao().deleteTodo(todo)

}