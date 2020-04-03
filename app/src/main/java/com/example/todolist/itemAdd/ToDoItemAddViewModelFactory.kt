package com.example.todolist.itemAdd

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider

class ToDoItemAddViewModelFactory(private val application: Application) : ViewModelProvider.Factory {
    @Suppress("unchecked_cast")
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(ToDoItemAddViewModel::class.java)) {
            return ToDoItemAddViewModel(application) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}