package com.example.todolist.list

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.todolist.database.ToDoItem
import com.example.todolist.database.ToDoItemDao
import com.example.todolist.database.ToDoItemDatabase
import com.example.todolist.database.ToDoRepository
import kotlinx.coroutines.*
import kotlin.coroutines.CoroutineContext

class ToDoListViewModel (application: Application): AndroidViewModel(application) {
    private var viewModelJob = Job()
    private val coroutineContext: CoroutineContext
        get() = viewModelJob + Dispatchers.Main
    private val scope = CoroutineScope(coroutineContext)
    private var itemSelected = MutableLiveData<ToDoItem?>()
    val toDoList: LiveData<List<ToDoItem>>
    private val repository: ToDoRepository

    init {
        val toDoItemDao = ToDoItemDatabase.getInstance(application, scope).toDoItemDao()
        repository = ToDoRepository(toDoItemDao)
        toDoList = repository.toDoList
    }

    fun update(toDoItem: ToDoItem) = scope.launch(Dispatchers.IO) {
        repository.update(toDoItem)
    }

    fun delete(toDoItem: ToDoItem) = scope.launch(Dispatchers.IO) {
        repository.delete(toDoItem)
    }

    override fun onCleared() {
        super.onCleared()
        viewModelJob.cancel()
    }


}