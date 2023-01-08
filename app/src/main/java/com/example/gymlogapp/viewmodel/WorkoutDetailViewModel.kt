package com.example.gymlogapp.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import com.example.gymlogapp.db.GymLogDatabase
import com.example.gymlogapp.models.Exercise
import com.example.gymlogapp.repositories.ExerciseRepository
import com.example.gymlogapp.ui.activities.WorkoutDetailActivity
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class WorkoutDetailViewModel(application: Application): AndroidViewModel(application) {

    private val exerciseRepository: ExerciseRepository
    private var exercisesByWorkoutId: LiveData<List<Exercise>>

    init {
        val exerciseDao = GymLogDatabase.getInstance(application).exerciseDao()
        exerciseRepository = ExerciseRepository(exerciseDao)
        exercisesByWorkoutId = exerciseDao.getExercisesByWorkoutId(WorkoutDetailActivity.workoutId)
    }

    fun getExercisesByWorkoutId(workoutId: Int){
        viewModelScope.launch ( Dispatchers.IO) {
            exercisesByWorkoutId = exerciseRepository.getExercisesByWorkoutId(workoutId)
        }
    }

    fun addExercise(exercise: Exercise){
        viewModelScope.launch(Dispatchers.IO) {
            exerciseRepository.addExercise(exercise)
        }
    }

    fun updateExercise(exercise: Exercise){
        viewModelScope.launch(Dispatchers.IO) {
            exerciseRepository.updateExercise(exercise)
        }
    }

    fun deleteExercise(exercise: Exercise){
        viewModelScope.launch(Dispatchers.IO) {
            exerciseRepository.deleteExercise(exercise)
        }
    }

    fun observeExerciseListByWorkoutIdLiveData(): LiveData<List<Exercise>>{
        return exercisesByWorkoutId
    }
}