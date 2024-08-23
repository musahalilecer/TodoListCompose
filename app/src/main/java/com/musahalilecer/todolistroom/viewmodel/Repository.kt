package com.musahalilecer.todolistroom.viewmodel


import com.musahalilecer.todolistroom.model.Todo
import com.musahalilecer.todolistroom.model.TodoDatabase

class Repository(private val db: TodoDatabase) {
    suspend fun upsertTodo(todo: Todo){
        db.todoDao.upsertTodo(todo)
    }
    suspend fun deleteTodo(todo: Todo){
        db.todoDao.deleteTodo(todo)
    }
    fun getAllRecords() = db.todoDao.getAllRecords()


}