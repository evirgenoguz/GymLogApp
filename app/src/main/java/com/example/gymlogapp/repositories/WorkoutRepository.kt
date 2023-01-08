package com.example.gymlogapp.repositories

import androidx.lifecycle.LiveData
import com.example.gymlogapp.db.WorkoutDao
import com.example.gymlogapp.models.Workout

class WorkoutRepository(private val workoutDao: WorkoutDao) {

    val getAllWorkouts: LiveData<List<Workout>> = workoutDao.getAllWorkouts()

    suspend fun addWorkout(workout: Workout){
        workoutDao.addWorkout(workout)
    }

    suspend fun deleteWorkout(workout: Workout){
        workoutDao.deleteWorkout(workout)
    }

    suspend fun updateWorkout(workout: Workout){
        workoutDao.updateWorkout(workout)
    }

}