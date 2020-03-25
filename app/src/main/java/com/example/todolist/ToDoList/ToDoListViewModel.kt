package com.example.todolist.ToDoList

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

class ToDoListViewModel (toDoItemDao: ToDoItemDao, application: Application): AndroidViewModel(application) {
    private var viewModelJob = Job()
    private val uiScope = CoroutineScope(Dispatchers.Main + viewModelJob)
    private var itemSelected = MutableLiveData<ToDoItem?>()
    val toDoList: LiveData<List<ToDoItem>>
        get() {
            TODO()
        }
    private val repository: ToDoRepository

    init {
        repository = ToDoRepository(toDoItemDao)
        repository.toDoList
    }

    fun insert(toDoItem: ToDoItem) = viewModelScope.launch{
        repository.insert(toDoItem)
    }

    override fun onCleared() {
        super.onCleared()
        viewModelJob.cancel()
    }


}