package com.example.todoapp.home

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.Fragment
import com.example.todoapp.R
import com.example.todoapp.home.screens.SettingsFragment
import com.example.todoapp.home.screens.ToDoListFragment
import com.example.todoapp.databinding.ActivityHomeBinding

class HomeActivity : AppCompatActivity() {
    lateinit var binding: ActivityHomeBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityHomeBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setCurrentFragment(ToDoListFragment())
        binding.bottomNavView.setOnItemSelectedListener {
            when (it.itemId) {
                R.id.list -> setCurrentFragment(ToDoListFragment())
                R.id.settings -> setCurrentFragment(SettingsFragment())
                else -> false
            }
        }
    }

    private fun setCurrentFragment(fragment: Fragment): Boolean {
        supportFragmentManager.beginTransaction().replace(R.id.fragmentContainer, fragment).commit()
        if (fragment is ToDoListFragment) {
            binding.title.text = "To Do List"
        } else if (fragment is SettingsFragment) {
            binding.title.text = "Settings"
        }
        return true
    }


}