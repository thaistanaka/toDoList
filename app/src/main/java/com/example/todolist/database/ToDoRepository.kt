package com.example.todolist.database

import androidx.annotation.WorkerThread
import androidx.lifecycle.LiveData

class ToDoRepository(private val toDoItemDao: ToDoItemDao) {

    val toDoList: LiveData<List<ToDoItem>> = toDoItemDao.getAll()

    @Suppress("RedundantSuspendModifier")
    @WorkerThread
    suspend fun insert(toDoItem: ToDoItem) {
        toDoItemDao.insert(toDoItem)
    }

    @Suppress("RedundantSuspendModifier")
    @WorkerThread
    suspend fun update(toDoItem: ToDoItem) {
        toDoItemDao.update(toDoItem)
    }

    @Suppress("RedundantSuspendModifier")
    @WorkerThread
    suspend fun delete(toDoItem: ToDoItem) {
        toDoItemDao.delete(toDoItem)
    }
}