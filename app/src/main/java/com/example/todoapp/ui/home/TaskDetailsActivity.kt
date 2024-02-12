package com.example.todoapp.ui.home

import android.annotation.SuppressLint
import android.app.DatePickerDialog
import android.app.DatePickerDialog.BUTTON_POSITIVE
import android.app.DatePickerDialog.OnDateSetListener
import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import android.widget.DatePicker
import androidx.appcompat.app.AppCompatActivity
import com.example.todoapp.Constants
import com.example.todoapp.R
import com.example.todoapp.clearExcess
import com.example.todoapp.database.database.ToDoDatabase
import com.example.todoapp.database.model.ToDo
import com.example.todoapp.databinding.ActivityTaskDetailsBinding
import java.text.SimpleDateFormat
import java.util.Calendar

class TaskDetailsActivity : AppCompatActivity() {
    private lateinit var binding: ActivityTaskDetailsBinding
    var taskTime = Calendar.getInstance()
    var toDo: ToDo? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityTaskDetailsBinding.inflate(layoutInflater)
        setContentView(binding.root)
        toDo = intent.getSerializableExtra(Constants.TODO) as ToDo
        initDatePicker()
        setOldDetails()
        initListeners()
    }

    private fun initListeners() {
        binding.saveBtn.setOnClickListener {
            updateTask()
            val intent = Intent(this, HomeActivity::class.java)
            startActivity(intent)
        }
        binding.backBtn.setOnClickListener {
            onBackPressed()
        }
    }


    @SuppressLint("SimpleDateFormat")
    private fun getDate(milliSeconds: Long): String? {
        val formatter = SimpleDateFormat("dd/MM/yyyy")
        return formatter.format(milliSeconds)
    }

    private fun updateTask() {
        taskTime.clearExcess()
        toDo!!.title = binding.detailsTaskTitleTil.editText?.text.toString()
        toDo!!.description = binding.detailsTaskDescriptionTil.editText?.text.toString()
        toDo!!.time = taskTime.timeInMillis
        ToDoDatabase.getInstance(this).todoDao().updateTask(toDo!!)
    }

    @SuppressLint("SetTextI18n")
    private fun initDatePicker() {
        binding.detailsTimeTv.setOnClickListener {
            val datePicker = DatePickerDialog(
                this, R.style.TimePickerTheme,
                { picker, year, month, day ->
                    taskTime.set(Calendar.YEAR, year)
                    taskTime.set(Calendar.MONTH, month)
                    taskTime.set(Calendar.DAY_OF_MONTH, day)
                    binding.detailsTimeTv.text =
                        "${taskTime.get(Calendar.DAY_OF_MONTH)} / ${taskTime.get(Calendar.MONTH) + 1} / ${
                            taskTime.get(Calendar.YEAR)
                        }"
                }, taskTime.get(Calendar.YEAR),
                taskTime.get(Calendar.MONTH),
                taskTime.get(Calendar.DAY_OF_MONTH)
            )
            datePicker.show()
            datePicker.datePicker.minDate = Calendar.getInstance().timeInMillis
            datePicker.getButton(DatePickerDialog.BUTTON_POSITIVE).setTextColor(Color.BLUE)
            datePicker.getButton(DatePickerDialog.BUTTON_NEGATIVE).setTextColor(Color.BLUE)
        }
    }

    private fun setOldDetails() {
        binding.detailsTaskTitleTil.editText?.setText(toDo!!.title)
        binding.detailsTaskDescriptionTil.editText?.setText(toDo!!.description)
        binding.detailsTimeTv.text = getDate(toDo!!.time)
        taskTime.timeInMillis = toDo!!.time
        taskTime.set(Calendar.YEAR, taskTime.get(Calendar.YEAR))
        taskTime.set(Calendar.MONTH, taskTime.get(Calendar.MONTH))
        taskTime.set(Calendar.DAY_OF_MONTH, taskTime.get(Calendar.DAY_OF_MONTH))
    }
}