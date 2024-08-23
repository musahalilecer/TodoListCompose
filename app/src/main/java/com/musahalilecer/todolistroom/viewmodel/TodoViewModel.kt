package com.musahalilecer.todolistroom.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import com.musahalilecer.todolistroom.model.Todo
import kotlinx.coroutines.launch

class TodoViewModel(private val repository: Repository): ViewModel() {

    fun getAllRecords() = repository.getAllRecords().asLiveData(viewModelScope.coroutineContext)

    fun upsertNote(todo: Todo){
        viewModelScope.launch {
            repository.upsertTodo(todo)
        }
    }
    fun deleteTodo(todo: Todo){
        viewModelScope.launch {
            repository.deleteTodo(todo)
        }
    }

}