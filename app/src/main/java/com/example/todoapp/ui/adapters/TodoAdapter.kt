package com.example.todoapp.ui.adapters

import android.content.Context
import android.graphics.Color
import android.os.Handler
import android.os.Looper
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.example.todoapp.R
import com.example.todoapp.database.model.ToDo
import com.example.todoapp.databinding.TaskItemBinding

class TodoAdapter(var todos: List<ToDo>) :
    RecyclerView.Adapter<TodoAdapter.TodoViewHolder>() {
    var onClick: OnClick? = null

    inner class TodoViewHolder(var binding: TaskItemBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(toDo: ToDo, position: Int) {
            binding.taskTitleTv.text = toDo.title
            binding.taskDescriptionTv.text = toDo.description
            binding.checkBtn.setBackgroundDrawable(
                ContextCompat.getDrawable(
                    binding.checkBtn.context,
                    R.drawable.rectangle
                )
            )
            binding.checkBtn.text = ""
            binding.checkBtn.setCompoundDrawablesWithIntrinsicBounds(
                0,
                R.drawable.ic_check,
                0,
                0
            )
            binding.checkBtn.setPadding(0, 20, 0, 0)
            binding.verticalLine.setBackgroundDrawable(
                ContextCompat.getDrawable(
                    binding.verticalLine.context,
                    R.drawable.dragging_bar
                )
            )
            binding.taskTitleTv.setTextColor(
                ContextCompat.getColor(
                    binding.taskTitleTv.context,
                    R.color.blue
                )
            )
            binding.leftItem.setOnClickListener {
                onClick?.onDeleteClick(todos[position], position)
            }
            binding.checkBtn.setOnClickListener {
                onClick?.onCheckClick(todos[position], position)
                binding.checkBtn.setBackgroundColor(Color.TRANSPARENT)
                binding.checkBtn.setCompoundDrawablesWithIntrinsicBounds(
                    null,
                    null,
                    null,
                    null
                )
                binding.checkBtn.text = "Done!"
                binding.checkBtn.setPadding(0, 0, 0, 0)
                binding.verticalLine.setBackgroundDrawable(
                    ContextCompat.getDrawable(
                        binding.verticalLine.context,
                        R.drawable.dragging_bar_done
                    )
                )
                binding.taskTitleTv.setTextColor(
                    ContextCompat.getColor(
                        binding.taskTitleTv.context,
                        R.color.green
                    )
                )
            }
            binding.dragItem.setOnClickListener {
                onClick?.onTaskClick(todos[position], position)
            }
        }
    }

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
        val toDo = todos[position]
        holder.bind(toDo, position)
    }

    fun updateTodos(todos: List<ToDo>) {
        this.todos = todos
        notifyDataSetChanged()
    }

    interface OnClick {
        fun onDeleteClick(toDo: ToDo, position: Int)
        fun onCheckClick(toDo: ToDo, position: Int)
        fun onTaskClick(toDo: ToDo, position: Int)
    }
}