package com.example.todolist.ToDoList

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.lifecycle.LiveData
import androidx.recyclerview.widget.RecyclerView
import com.example.todolist.R
import com.example.todolist.database.ToDoItem

class ToDoListAdapter internal constructor(
    context: Context?
) : RecyclerView.Adapter<ToDoListAdapter.ToDoViewHolder>() {

    private val inflater: LayoutInflater = LayoutInflater.from(context)
    private var toDoList = emptyList<ToDoItem>() // Cached copy of words

    inner class ToDoViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val toDoItemTitle: TextView = itemView.findViewById(R.id.title)
        val toDoItemDescription: TextView = itemView.findViewById(R.id.description)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ToDoViewHolder {
        val itemView = inflater.inflate(R.layout.recyclerview_item, parent, false)
        return ToDoViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: ToDoViewHolder, position: Int) {
        val toDoItem = toDoList[position]
        holder.toDoItemTitle.text = toDoItem.title
        holder.toDoItemDescription.text = toDoItem.description
    }

    internal fun setToDoList(toDoList: List<ToDoItem>) {
        this.toDoList = toDoList
        notifyDataSetChanged()
    }

    override fun getItemCount() = toDoList.size
}