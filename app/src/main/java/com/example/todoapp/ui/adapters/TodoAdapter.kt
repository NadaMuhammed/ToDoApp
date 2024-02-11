package com.example.todoapp.ui.adapters

import android.graphics.Color
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.todoapp.database.model.ToDo
import com.example.todoapp.databinding.TaskItemBinding

class TodoAdapter(var todos: List<ToDo>) :
    RecyclerView.Adapter<TodoAdapter.TodoViewHolder>() {

    class TodoViewHolder(var binding: TaskItemBinding) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TodoViewHolder {
        return TodoViewHolder(
            TaskItemBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun getItemCount(): Int = todos.size

    override fun onBindViewHolder(holder: TodoViewHolder, position: Int) {
        holder.binding.taskTitleTv.text = todos[position].title
        holder.binding.taskDescriptionTv.text = todos[position].description
//        holder.binding.checkBtn.setOnClickListener {
//            onCheck.invoke()
//            holder.binding.checkBtn.setBackgroundColor(Color.TRANSPARENT)
//            holder.binding.checkBtn.setCompoundDrawablesWithIntrinsicBounds(null, null, null, null)
//        }
    }

    fun updateTodos(todos: List<ToDo>){
        this.todos = todos
        notifyDataSetChanged()
    }
}