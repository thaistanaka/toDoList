package com.example.todolist.ToDoItemAdd

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.findNavController

import com.example.todolist.R
import com.example.todolist.database.ToDoItemDatabase
import com.example.todolist.databinding.FragmentToDoItemBinding
import kotlinx.android.synthetic.main.fragment_to_do_item.view.*
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job

class ToDoItemAddFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        //return inflater.inflate(R.layout.fragment_to_do_item, container, false)

        val binding:FragmentToDoItemBinding = DataBindingUtil.inflate(
            inflater, R.layout.fragment_to_do_item, container, false)
//
//        val application = requireNotNull(this.activity).application
//
//        val dataSource = ToDoItemDatabase.getInstance(application).toDoItemDao()
//
//        val viewModelFactory = ToDoItemAddViewModelFactory(dataSource, application)
//
//        val toDoItemAddViewModel =
//            ViewModelProviders.of(
//                this, viewModelFactory).get(ToDoItemAddViewModel::class.java)
//
//        binding.setLifecycleOwner(this)
//
//        binding.toDoItemAddViewModel = toDoItemAddViewModel

        binding.add.setOnClickListener {
            v: View ->
            v.add.findNavController().navigate(R.id.action_toDoItemFragment_to_toDoListFragment)
        }

        return binding.root
    }

}
