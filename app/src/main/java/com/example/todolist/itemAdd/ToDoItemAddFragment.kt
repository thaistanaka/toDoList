package com.example.todolist.itemAdd

import android.os.Bundle
import android.text.TextUtils
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.findNavController
import com.example.todolist.R
import com.example.todolist.database.ToDoItem
import com.example.todolist.databinding.FragmentToDoItemBinding
import kotlinx.android.synthetic.main.fragment_to_do_item.view.*

class ToDoItemAddFragment : Fragment() {
    private var binding: FragmentToDoItemBinding? = null

    private var viewModel: ToDoItemAddViewModel? = null

    private var description: EditText? = null

    private var title: EditText? = null

    override fun onCreateView( inflater: LayoutInflater, container: ViewGroup?,
                               savedInstanceState: Bundle?): View? {

        binding = DataBindingUtil.inflate(
            inflater, R.layout.fragment_to_do_item, container, false
        )

        description = binding?.description
        title = binding?.title

        val application = requireNotNull(this.activity).application

        val viewModelFactory = ToDoItemAddViewModelFactory(application)

        viewModel =
            ViewModelProvider(this, viewModelFactory).get(ToDoItemAddViewModel::class.java)

        binding?.lifecycleOwner = this

        binding?.add?.setOnClickListener { v: View ->
            if (!TextUtils.isEmpty(title?.text)) {
                binding?.toDoItemAddViewModel = viewModel
                viewModel?.insert(
                    ToDoItem(
                        title = title?.text.toString(),
                        description = description?.text.toString()
                    )
                )
            }
            v.add.findNavController().navigate(R.id.action_toDoItemFragment_to_toDoListFragment)
        }

        return binding?.root
    }

}
