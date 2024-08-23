package com.musahalilecer.todolistroom.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "todo")
class Todo(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    val title: String,
    val note: String
)