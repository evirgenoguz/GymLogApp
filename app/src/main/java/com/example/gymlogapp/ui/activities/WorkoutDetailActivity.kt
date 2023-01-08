package com.example.gymlogapp.ui.activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.gymlogapp.adapters.ExercisesAdapter
import com.example.gymlogapp.databinding.ActivityWorkoutDetailBinding
import com.example.gymlogapp.viewmodel.WorkoutDetailViewModel

class WorkoutDetailActivity : AppCompatActivity() {

    companion object {
        var workoutId: Int = 0
    }

    private lateinit var binding: ActivityWorkoutDetailBinding
    private lateinit var workoutDetailMvvm: WorkoutDetailViewModel
    private lateinit var exercisesAdapter: ExercisesAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityWorkoutDetailBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        val intent = intent
        workoutId = intent.getIntExtra("workoutId", 0)

        workoutDetailMvvm = ViewModelProvider(this)[WorkoutDetailViewModel::class.java]


        workoutDetailMvvm.getExercisesByWorkoutId(workoutId)
        observerExercises(workoutId)
        prepareRecyclerView()
        onAddFabClicked(workoutId)




    }


    private fun prepareRecyclerView() {
        exercisesAdapter = ExercisesAdapter(workoutDetailMvvm)

        binding.rvExercises.apply {
            layoutManager = LinearLayoutManager(context)
            adapter = exercisesAdapter
        }
    }

    private fun onAddFabClicked(workoutId: Int) {
        binding.fabAddExercise.setOnClickListener {

            val intent = Intent(this, AddExerciseActivity::class.java)
            intent.putExtra("workoutId", workoutId)
            startActivity(intent)
//            Log.d("selam", "selam")
        }
    }

    private fun observerExercises(workoutId: Int){
        workoutDetailMvvm.observeExerciseListByWorkoutIdLiveData().observe(this, Observer { exercisesListByWorkoutId ->
            exercisesAdapter.setExerciseListByWorkoutId(exercisesListByWorkoutId)
        })
    }




}