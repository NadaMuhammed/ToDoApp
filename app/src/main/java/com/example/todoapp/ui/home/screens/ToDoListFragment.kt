package com.example.todoapp.ui.home.screens

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.todoapp.database.database.ToDoDatabase
import com.example.todoapp.database.model.ToDo
import com.example.todoapp.databinding.FragmentToDoListBinding
import com.example.todoapp.ui.adapters.TodoAdapter
import java.util.Calendar

class ToDoListFragment : Fragment() {
    lateinit var binding: FragmentToDoListBinding
    lateinit var todos: List<ToDo>
    lateinit var todoAdapter: TodoAdapter
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentToDoListBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        todos = ToDoDatabase.getInstance(requireContext()).todoDao().getAllTasks()
        todoAdapter = TodoAdapter(todos)
        binding.todosRV.adapter = todoAdapter
    }
}