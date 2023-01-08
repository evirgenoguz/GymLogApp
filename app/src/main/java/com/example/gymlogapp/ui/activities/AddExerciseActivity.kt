package com.example.gymlogapp.ui.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.ViewModelProvider
import com.example.gymlogapp.databinding.ActivityAddExerciseBinding
import com.example.gymlogapp.models.Exercise
import com.example.gymlogapp.viewmodel.WorkoutDetailViewModel

class AddExerciseActivity : AppCompatActivity() {

    private lateinit var binding: ActivityAddExerciseBinding
    private lateinit var workoutDetailMvvm: WorkoutDetailViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAddExerciseBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        workoutDetailMvvm = ViewModelProvider(this)[WorkoutDetailViewModel::class.java]
        val intent = intent
        WorkoutDetailActivity.workoutId = intent.getIntExtra("workoutId", 0)
        onAddButtonClicked(WorkoutDetailActivity.workoutId)

    }

    private fun onAddButtonClicked(workoutId: Int) {
        binding.bAddExercise.setOnClickListener {
            val exerciseName = binding.etExerciseName.text.toString()
            val exerciseWeight = binding.etWeigth.text.toString()
            val exerciseSet = binding.etSet.text.toString()

            val exercise = Exercise(0, workoutId, exerciseName, exerciseSet.toInt(), exerciseWeight.toInt())

            workoutDetailMvvm.addExercise(exercise)


        }
    }
}