package com.example.todolist

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity


class MainActivity : AppCompatActivity() {
    //private lateinit var binding: ActivityMainBinding

    //private val toDoItem: ToDoItem = ToDoItem(title = "title", createdDate = Calendar.getInstance().time)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }
}
