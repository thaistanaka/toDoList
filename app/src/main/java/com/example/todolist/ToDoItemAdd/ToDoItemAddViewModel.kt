package com.example.todolist.ToDoItemAdd

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import com.example.todolist.database.ToDoItem
import com.example.todolist.database.ToDoItemDao
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job

class ToDoItemAddViewModel (toDoItemDao: ToDoItemDao, application: Application): AndroidViewModel(application) {
    private var viewModelJob = Job()
    private val uiScope = CoroutineScope(Dispatchers.Main + viewModelJob)
    private var itemAdd = MutableLiveData<ToDoItem?>()

    init {
    }

    override fun onCleared() {
        super.onCleared()
        viewModelJob.cancel()
    }
}