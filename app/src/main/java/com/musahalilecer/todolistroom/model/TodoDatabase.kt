package com.musahalilecer.todolistroom.model

import androidx.room.Database
import androidx.room.RoomDatabase

@Database
    (entities = [Todo::class],
    version = 1)
abstract class TodoDatabase: RoomDatabase() {
    abstract val todoDao: TodoDao
}