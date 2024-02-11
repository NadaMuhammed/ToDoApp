package com.example.todoapp.ui.home.screens

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.todoapp.database.database.ToDoDatabase
import com.example.todoapp.database.model.ToDo
import com.example.todoapp.databinding.FragmentToDoListBinding
import com.example.todoapp.timeInMillis
import com.example.todoapp.ui.adapters.TodoAdapter
import com.prolificinteractive.materialcalendarview.CalendarDay
import java.util.Calendar

class ToDoListFragment : Fragment() {
    lateinit var binding: FragmentToDoListBinding
    lateinit var todos: List<ToDo>
    var todoAdapter = TodoAdapter(emptyList())
    var calendarDay = CalendarDay.today()
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
        refreshTodos()
        binding.todosRV.adapter = todoAdapter
        binding.calendar.selectedDate = calendarDay
        binding.calendar.setOnDateChangedListener { widget, date, selected ->
            calendarDay = date
            refreshTodos()
        }
    }

    fun refreshTodos() {
        todos = ToDoDatabase.getInstance(requireContext()).todoDao()
            .getAllTasksByDate(calendarDay.timeInMillis())
        todoAdapter.updateTodos(todos)
    }
}