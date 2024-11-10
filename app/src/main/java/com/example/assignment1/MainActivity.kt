package com.example.assignment1

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.viewinterop.AndroidView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.assignment1.ui.theme.Assignment1Theme
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import java.io.InputStreamReader
import androidx.compose.material3.Scaffold

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val todos = loadToDoItemsFromJson()

        setContent {
            Assignment1Theme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    ToDoListScreen(
                        todos = todos,
                        modifier = Modifier.padding(innerPadding)
                    )
                }
            }
        }
    }

    private fun loadToDoItemsFromJson(): List<ToDoItem> {
        val inputStream = assets.open("MOCK_DATA.json")
        val reader = InputStreamReader(inputStream)
        val itemType = object : TypeToken<List<ToDoItem>>() {}.type
        return Gson().fromJson(reader, itemType)
    }
}

@Composable
fun ToDoListScreen(todos: List<ToDoItem>, modifier: Modifier = Modifier) {
    val onClick: (ToDoItem) -> Unit = { item ->
        println("Clicked task: ${item.title}")
    }

    AndroidView(
        modifier = modifier.fillMaxSize(),
        factory = { context ->
            RecyclerView(context).apply {
                layoutManager = LinearLayoutManager(context)
                adapter = ToDoAdapter(todos, onClick)
            }
        }
    )
}

@Preview(showBackground = true)
@Composable
fun ToDoListScreenPreview() {
    Assignment1Theme {
        ToDoListScreen(
            todos = listOf(
                ToDoItem(1, "Task 1", "Description 1", "2024-11-10", false),
                ToDoItem(2, "Task 2", "Description 2", "2024-11-11", true)
            )
        )
    }
}