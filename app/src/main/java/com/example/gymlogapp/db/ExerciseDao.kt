package com.example.gymlogapp.db

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.example.gymlogapp.models.Exercise
import com.example.gymlogapp.models.Workout

@Dao
interface ExerciseDao {

    @Query(value = "Select * From exercises Where workoutId == :workoutId")
    fun getExercisesByWorkoutId(workoutId: Int): LiveData<List<Exercise>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun addExercise(exercise: Exercise)

    @Delete
    suspend fun deleteExercise(exercise: Exercise)

    @Update
    suspend fun updateExercise(exercise: Exercise)
}