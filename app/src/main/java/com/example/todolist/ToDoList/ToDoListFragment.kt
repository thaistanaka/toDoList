package com.example.todolist.ToDoList

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import androidx.lifecycle.viewModelScope
import androidx.navigation.findNavController
import androidx.recyclerview.widget.LinearLayoutManager

import com.example.todolist.R
import com.example.todolist.database.ToDoItemDatabase
import com.example.todolist.databinding.FragmentToDoListBinding

class ToDoListFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val binding:FragmentToDoListBinding = DataBindingUtil.inflate(
            inflater, R.layout.fragment_to_do_list, container, false)

//        val recyclerView = binding.recyclerView
//        val adapter = ToDoListAdapter(context)
//        recyclerView.adapter = adapter
//        recyclerView.layoutManager = LinearLayoutManager(context)

//        val application = requireNotNull(this.activity).application
//
//        val dataSource = ToDoItemDatabase.getInstance(application).toDoItemDao()
//
//        val viewModelFactory = ToDoListViewModelFactory(dataSource, application)
//
//        val toDoListViewModel =
//            ViewModelProviders.of(
//                this, viewModelFactory).get(ToDoListViewModel::class.java)
//
//        binding.setLifecycleOwner(this)
//
//        binding.toDoListViewModel = toDoListViewModel

        binding.fab.setOnClickListener {
                view: View ->
            view.findNavController().navigate(R.id.action_toDoListFragment_to_toDoItemFragment)
        }

        return binding.root
    }

}
