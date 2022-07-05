package com.sample.room_sample.data.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "todo")
data class Todo(
    @PrimaryKey(autoGenerate = true)
    val id: Long = 0L,
    val task: String,
    var isChecked: Boolean = false
)