package com.example.todolist.list

import android.content.Context
import android.graphics.Color
import android.view.*
import android.widget.ProgressBar
import android.widget.TextView
import androidx.appcompat.widget.PopupMenu
import androidx.recyclerview.widget.RecyclerView
import com.example.todolist.R
import com.example.todolist.database.ToDoItem


class ToDoListAdapter internal constructor(
    context: Context?,
    progressBar: ProgressBar,
    viewModel: ToDoListViewModel
) : RecyclerView.Adapter<ToDoListAdapter.ToDoViewHolder>() {

    private val inflater: LayoutInflater = LayoutInflater.from(context)
    private var toDoList = emptyList<ToDoItem>()
    private val progressBar = progressBar
    private val context = context
    private val viewModel = viewModel

    inner class ToDoViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val toDoItemTitle: TextView = itemView.findViewById(R.id.title)
        val toDoItemDescription: TextView = itemView.findViewById(R.id.description)
        val item: View = itemView.findViewById(R.id.itemView)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ToDoViewHolder {
        val itemView = inflater.inflate(R.layout.recyclerview_item, parent, false)
        return ToDoViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: ToDoViewHolder, position: Int) {
        val toDoItem = toDoList[position]
        holder.toDoItemTitle.text = toDoItem.title
        if(toDoItem.description == ""){
            holder.toDoItemDescription.text = "There is no description"
        } else {
            holder.toDoItemDescription.text = toDoItem.description
        }

        if(toDoItem.done == true){
            holder.item.setBackgroundColor(Color.parseColor("#00aaff"))
        } else {
            holder.item.setBackgroundColor(Color.parseColor("#ffffff"))
        }

        progressBar.setVisibility(View.GONE);

        holder.item.setOnClickListener {
            toDoItem.done = true
            holder.item.setBackgroundColor(Color.parseColor("#00aaff"))
            viewModel.update(toDoItem)
        }

        holder.item.setOnLongClickListener {
            var menu = context?.let { context -> PopupMenu(context, it) }
            if (menu != null) {
                menu.inflate(R.menu.menu)
                menu.show()
                menu.setOnMenuItemClickListener {
                    when(it.itemId) {
                        R.id.delete -> {
                            viewModel.delete(toDoItem)
                            true
                        }
                        else -> {
                            true
                        }
                    }
                }
            }
            true
        }
    }

    internal fun setToDoList(toDoList: List<ToDoItem>) {
        this.toDoList = toDoList
        notifyDataSetChanged()
    }

    override fun getItemCount() = toDoList.size
}