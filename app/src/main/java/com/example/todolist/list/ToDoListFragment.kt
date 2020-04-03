package com.example.todolist.list

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.findNavController
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView.ItemDecoration
import com.example.todolist.R
import com.example.todolist.databinding.FragmentToDoListBinding
import kotlinx.android.synthetic.main.fragment_to_do_list.*


class ToDoListFragment : Fragment() {

    private var binding: FragmentToDoListBinding? = null

    private var viewModel: ToDoListViewModel? = null

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        binding = DataBindingUtil.inflate(
            inflater, R.layout.fragment_to_do_list, container, false)

        val application = requireNotNull(this.activity).application

        val viewModelFactory = ToDoListViewModelFactory(application)

        viewModel =
            ViewModelProvider(this, viewModelFactory).get(ToDoListViewModel::class.java)

        binding?.lifecycleOwner = this

        val recyclerView = binding?.recyclerView
        val adapter = ToDoListAdapter(context, binding?.progressbar!!, viewModel!!)
        recyclerView?.adapter = adapter
        recyclerView?.layoutManager = LinearLayoutManager(context)
        val itemDecoration: ItemDecoration =
            DividerItemDecoration(context, DividerItemDecoration.VERTICAL)
        recyclerView?.addItemDecoration(itemDecoration)

        viewModel?.toDoList?.observe(viewLifecycleOwner, Observer { toDoList ->
            toDoList?.let { adapter.setToDoList(it)
                if(adapter.itemCount == 0){
                    progressbar.visibility = View.GONE
                    recyclerView?.visibility = View.GONE
                    binding?.empty?.visibility = View.VISIBLE
                } else {
                    recyclerView?.visibility = View.VISIBLE
                    binding?.empty?.visibility = View.GONE
                }}
        })

        binding?.toDoListViewModel = viewModel

        binding?.fab?.setOnClickListener {
                view: View ->
            view.findNavController().navigate(R.id.action_toDoListFragment_to_toDoItemFragment)
        }

        return binding?.root
    }

}
