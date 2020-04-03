package com.example.todolist.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.sqlite.db.SupportSQLiteDatabase
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

@Database(entities = arrayOf(ToDoItem::class), version = 1, exportSchema = false)
abstract class ToDoItemDatabase : RoomDatabase() {
    abstract fun toDoItemDao(): ToDoItemDao

    companion object {
        @Volatile
        private var INSTANCE: ToDoItemDatabase? = null

        fun getInstance(context: Context, scope: CoroutineScope): ToDoItemDatabase {
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    ToDoItemDatabase::class.java,
                    "todo_database"
                )
                    .fallbackToDestructiveMigration()
                    .addCallback(ToDoItemDatabaseCallback(scope))
                    .build()
                INSTANCE = instance
                instance
            }
        }

        private class ToDoItemDatabaseCallback(private val scope: CoroutineScope) : RoomDatabase.Callback() {

            override fun onOpen(db: SupportSQLiteDatabase) {
                super.onOpen(db)
                INSTANCE?.let { database ->
                    scope.launch(Dispatchers.IO) {
                        populateDatabase(database.toDoItemDao())
                    }
                }
            }
        }

        fun populateDatabase(toDoItemDao: ToDoItemDao) {
            toDoItemDao.deleteAll()
        }
    }
}