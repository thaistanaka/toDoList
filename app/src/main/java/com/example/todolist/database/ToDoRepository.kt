package com.example.todolist.database

import androidx.lifecycle.LiveData

class ToDoRepository(private val toDoItemDao: ToDoItemDao) {

    val toDoList: LiveData<List<ToDoItem>> = toDoItemDao.getAll()

    suspend fun insert(toDoItem: ToDoItem) {
        toDoItemDao.insert(toDoItem)
    }
}