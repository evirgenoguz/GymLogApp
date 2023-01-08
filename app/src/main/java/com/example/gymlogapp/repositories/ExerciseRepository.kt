package com.example.gymlogapp.repositories

import androidx.lifecycle.LiveData
import com.example.gymlogapp.db.ExerciseDao
import com.example.gymlogapp.models.Exercise

class ExerciseRepository(private val exerciseDao: ExerciseDao) {

    fun getExercisesByWorkoutId(workoutId: Int): LiveData<List<Exercise>>{
        return exerciseDao.getExercisesByWorkoutId(workoutId)
    }

    suspend fun addExercise(exercise: Exercise){
        exerciseDao.addExercise(exercise)
    }

    suspend fun deleteExercise(exercise: Exercise){
        exerciseDao.deleteExercise(exercise)
    }

    suspend fun updateExercise(exercise: Exercise){
        exerciseDao.updateExercise(exercise)
    }
}