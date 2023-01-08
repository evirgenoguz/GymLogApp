package com.example.gymlogapp.ui.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.navigation.Navigation
import androidx.navigation.ui.NavigationUI
import com.example.gymlogapp.R
import com.example.gymlogapp.databinding.ActivityMainBinding
import com.example.gymlogapp.models.Exercise
import com.example.gymlogapp.models.Workout
import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Date

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        val navController = Navigation.findNavController(this, R.id.hostContainer)
        NavigationUI.setupWithNavController(binding.bottomNavigationMain, navController)





    }

}