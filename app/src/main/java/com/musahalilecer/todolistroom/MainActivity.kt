package com.musahalilecer.todolistroom

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.room.Room
import com.musahalilecer.todolistroom.model.Todo
import com.musahalilecer.todolistroom.model.TodoDatabase
import com.musahalilecer.todolistroom.ui.theme.TodoListRoomTheme
import com.musahalilecer.todolistroom.viewmodel.Repository
import com.musahalilecer.todolistroom.viewmodel.TodoViewModel

class MainActivity : ComponentActivity() {

    private val db by lazy {
        Room.databaseBuilder(
            applicationContext,
            TodoDatabase::class.java,
            name = "todo"
        ).build()
    }
    private val viewModel by viewModels<TodoViewModel>(
        factoryProducer = {
            object : ViewModelProvider.Factory {
                override fun <T : ViewModel> create(modelClass: Class<T>): T {
                    return TodoViewModel(Repository(db)) as T
                }
            }
        }
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            TodoListRoomTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    var title by remember {
                        mutableStateOf("")
                    }
                    var note by remember {
                        mutableStateOf("")
                    }
                    val todo = Todo(
                        title = title,
                        note = note
                    )
                    var todoList by remember {
                        mutableStateOf(listOf<Todo>())
                    }
                    viewModel.getAllRecords().observe(this@MainActivity) {
                        todoList = it
                    }
                    Column(
                        Modifier.padding(12.dp),
                        verticalArrangement = Arrangement.spacedBy(12.dp)
                    ) {
                        TextField(
                            value = title,
                            onValueChange = { title = it },
                            placeholder = { Text(text = "Add Title") }
                        )
                        TextField(
                            value = note,
                            onValueChange = { note = it },
                            placeholder = { Text(text = "Add Note") }
                        )
                        Button(onClick = {
                            viewModel.upsertNote(todo)
                            title = ""
                            note = ""
                        }) {
                            Text(text = "Add Todo")
                        }
                        Button(onClick = {viewModel.deleteTodo(todo)}) {
                            Text(text = "Delete Todo")
                        }
                        LazyColumn {
                            items(todoList) { todo ->
                                Column(Modifier.fillMaxWidth()) {
                                    Text(text = "Title: ${todo.title}")
                                    Text(text = "Note: ${todo.note}")
                                }
                            }
                        }
                    }
                }
            }
        }
    }
}



@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    TodoListRoomTheme {

    }
}