package com.example.todolist.database

import androidx.lifecycle.LiveData
import androidx.room.*

@Dao
interface ToDoItemDao {
    @Insert
    fun insert(toDoItem: ToDoItem)

    @Update
    fun update(toDoItem: ToDoItem)

    @Delete
    fun delete(toDoItem: ToDoItem)

    @Query("DELETE FROM to_do_item")
    fun deleteAll()

    @Query("SELECT * FROM to_do_item ORDER BY itemId DESC")
    fun getAll(): LiveData<List<ToDoItem>>
}