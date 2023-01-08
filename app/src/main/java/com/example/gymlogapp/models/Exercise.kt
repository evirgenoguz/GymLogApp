package com.example.gymlogapp.models

import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity(tableName = "exercises")
data class Exercise(
    @PrimaryKey(autoGenerate = true)
    val id: Int,
    val workoutId: Int,
    var exerciseName: String,
    var sets: Int,
    var averageWeight: Int

)
