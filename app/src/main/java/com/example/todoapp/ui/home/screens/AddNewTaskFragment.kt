package com.example.todoapp.ui.home.screens

import android.annotation.SuppressLint
import android.app.TimePickerDialog
import android.graphics.Color
import android.os.Bundle
import android.text.format.DateFormat
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TimePicker
import android.widget.Toast
import androidx.core.widget.addTextChangedListener
import com.example.todoapp.R
import com.example.todoapp.databinding.FragmentAddNewTaskBinding
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import java.lang.String
import java.util.Calendar
import kotlin.Boolean
import kotlin.Int

class AddNewTaskFragment : BottomSheetDialogFragment(), TimePickerDialog.OnTimeSetListener {
    lateinit var binding: FragmentAddNewTaskBinding
    var calendar = Calendar.getInstance()
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentAddNewTaskBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initListeners()
    }

    private fun initListeners() {
        binding.addFab.setOnClickListener {
            if (validateInputs()) {
                Toast.makeText(requireContext(), "Task Added", Toast.LENGTH_SHORT).show()
                requireActivity().supportFragmentManager.beginTransaction().remove(this).commit()
            } else {
                Toast.makeText(requireContext(), "Please Fill All The Fields", Toast.LENGTH_SHORT)
                    .show()
            }
        }
        binding.taskTil.editText!!.addTextChangedListener {
            validateInputs()
        }
        binding.descriptionTil.editText!!.addTextChangedListener {
            validateInputs()
        }
        binding.timeTv.text =
            "${calendar.get(Calendar.HOUR_OF_DAY)}:${calendar.get(Calendar.MINUTE)}"
        binding.timeTv.setOnClickListener {
            val timePickerDialog = TimePickerDialog(
                context,
                R.style.TimePickerTheme,
                this,
                calendar.get(Calendar.HOUR),
                calendar.get(Calendar.MINUTE),
                DateFormat.is24HourFormat(context)
            )
            timePickerDialog.show()
            timePickerDialog.getButton(TimePickerDialog.BUTTON_POSITIVE).setTextColor(Color.BLUE)
            timePickerDialog.getButton(TimePickerDialog.BUTTON_NEGATIVE).setTextColor(Color.BLUE)
        }
    }

    private fun validateInputs(): Boolean {
        var isValid = true
        if (binding.taskTil.editText!!.text.isEmpty()) {
            binding.taskTil.error = "Please Enter Task Title"
            isValid = false
        } else {
            binding.taskTil.error = null
        }
        if (binding.descriptionTil.editText!!.text.isEmpty()) {
            binding.descriptionTil.error = "Please Enter Task Description"
            isValid = false
        } else {
            binding.descriptionTil.error = null
        }
        return isValid
    }

    @SuppressLint("DefaultLocale")
    override fun onTimeSet(timePicker: TimePicker?, hour: Int, minute: Int) {
        val isPM: Boolean = hour >= 12
        binding.timeTv.text = String.format("%02d:%02d %s",
            if (hour == 12 || hour == 0) 12 else hour % 12,
            minute,
            if (isPM) "PM" else "AM"
        )

    }
}