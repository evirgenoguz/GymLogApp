package com.example.gymlogapp.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.gymlogapp.db.GymLogDatabase
import com.example.gymlogapp.models.Exercise
import com.example.gymlogapp.models.Workout
import com.example.gymlogapp.repositories.WorkoutRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.text.SimpleDateFormat
import java.util.*

class HomeViewModel(application: Application): AndroidViewModel(application) {

    val getAllWorkout: LiveData<List<Workout>>
    private val workoutRepository: WorkoutRepository

    init {
        val workoutDao = GymLogDatabase.getInstance(application).workoutDao()
        workoutRepository = WorkoutRepository(workoutDao)
        getAllWorkout = workoutDao.getAllWorkouts()
    }

    fun addWorkout(workout: Workout){
        viewModelScope.launch(Dispatchers.IO) {
            workoutRepository.addWorkout(workout)
        }
    }

    fun updateWorkout(workout: Workout){
        viewModelScope.launch(Dispatchers.IO) {
            workoutRepository.updateWorkout(workout)
        }
    }

    fun deleteWorkout(workout: Workout){
        viewModelScope.launch(Dispatchers.IO) {
            workoutRepository.deleteWorkout(workout)
        }
    }

}