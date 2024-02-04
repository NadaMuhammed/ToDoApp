package com.example.todoapp.home.screens

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.todoapp.R
import com.example.todoapp.databinding.FragmentAddNewTaskBinding
import com.google.android.material.bottomsheet.BottomSheetDialogFragment

class AddNewTaskFragment : BottomSheetDialogFragment() {
    lateinit var binding: FragmentAddNewTaskBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentAddNewTaskBinding.inflate(inflater, container, false)
        return binding.root
    }
}