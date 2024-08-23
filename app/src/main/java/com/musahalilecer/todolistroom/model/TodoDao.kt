package com.musahalilecer.todolistroom.model

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Query
import androidx.room.Upsert
import kotlinx.coroutines.flow.Flow

@Dao
interface TodoDao {
    @Delete
    suspend fun deleteTodo(todo: Todo)

    @Upsert
    suspend fun upsertTodo(todo: Todo)

    @Query("SELECT * FROM todo")
    fun getAllRecords(): Flow<List<Todo>>


}