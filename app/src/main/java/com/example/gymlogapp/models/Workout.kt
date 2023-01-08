package com.example.gymlogapp.models

import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity(tableName = "workouts")
data class Workout(
    @PrimaryKey(autoGenerate = true)
    val id: Int,
    val date: String,
    //burasi nullable oldugu icin ve exercise diye bisi olmadigi icin sanirsam hata aldim bakalim ogrenelim
//    val exercises: List<Exercise>,
    val whichType: String?
)
