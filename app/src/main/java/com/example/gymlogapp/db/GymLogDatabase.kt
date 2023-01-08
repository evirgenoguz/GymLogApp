package com.example.gymlogapp.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.gymlogapp.models.Exercise
import com.example.gymlogapp.models.Workout

@Database(entities = [Workout::class, Exercise::class], version = 1, exportSchema = false)
abstract class GymLogDatabase: RoomDatabase() {
    abstract fun workoutDao(): WorkoutDao
    abstract fun exerciseDao(): ExerciseDao

    companion object {

        @Volatile
        var INSTANCE: GymLogDatabase? = null

        @Synchronized
        fun getInstance(context: Context): GymLogDatabase {
            if(INSTANCE == null){
                INSTANCE = Room.databaseBuilder(
                    context,
                    GymLogDatabase::class.java,
                    "gymLog.db"
                ).fallbackToDestructiveMigration()
                    .build()
            }
            return INSTANCE as GymLogDatabase
        }

    }

}