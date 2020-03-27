package com.example.todolist.itemAdd

import android.os.Bundle
import android.text.TextUtils
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.findNavController

import com.example.todolist.R
import com.example.todolist.database.ToDoItem
import com.example.todolist.database.ToDoItemDatabase
import com.example.todolist.databinding.FragmentToDoItemBinding
import com.example.todolist.list.ToDoListAdapter
import kotlinx.android.synthetic.main.fragment_to_do_item.view.*

class ToDoItemAddFragment : Fragment() {
    private lateinit var binding: FragmentToDoItemBinding

    private lateinit var viewModel: ToDoItemAddViewModel

    private lateinit var description: EditText

    private lateinit var title: EditText

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        //return inflater.inflate(R.layout.fragment_to_do_item, container, false)

        binding = DataBindingUtil.inflate(
            inflater, R.layout.fragment_to_do_item, container, false)

        description = binding.description
        title = binding.title

        val application = requireNotNull(this.activity).application

        val viewModelFactory = ToDoItemAddViewModelFactory(
            title.text.toString(), description.text.toString(), application
        )

        viewModel =
            ViewModelProvider(this, viewModelFactory).get(ToDoItemAddViewModel::class.java)

        binding.setLifecycleOwner(this)

        binding.add.setOnClickListener {
                v: View ->
            if(!TextUtils.isEmpty(title.text)){
                binding.toDoItemAddViewModel = viewModel
                viewModel.insert(ToDoItem(title = title.text.toString(),
                    description = description.text.toString()))
            }
            v.add.findNavController().navigate(R.id.action_toDoItemFragment_to_toDoListFragment)
        }

        return binding.root
    }

}
