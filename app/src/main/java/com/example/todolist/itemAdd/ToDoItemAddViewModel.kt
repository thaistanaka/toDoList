package com.example.todolist.itemAdd

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.todolist.database.ToDoItem
import com.example.todolist.database.ToDoItemDao
import com.example.todolist.database.ToDoItemDatabase
import com.example.todolist.database.ToDoRepository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import kotlin.coroutines.CoroutineContext

class ToDoItemAddViewModel (application: Application): AndroidViewModel(application) {
    private var viewModelJob = Job()
    private val coroutineContext: CoroutineContext
        get() = viewModelJob + Dispatchers.Main
    private val scope = CoroutineScope(coroutineContext)
    val toDoList: LiveData<List<ToDoItem>>
    private val repository: ToDoRepository

    init {
        val toDoItemDao = ToDoItemDatabase.getInstance(application, scope).toDoItemDao()
        repository = ToDoRepository(toDoItemDao)
        toDoList = repository.toDoList
    }

    fun insert(toDoItem: ToDoItem) = scope.launch(Dispatchers.IO) {
        repository.insert(toDoItem)
    }

    override fun onCleared() {
        super.onCleared()
        viewModelJob.cancel()
    }
}