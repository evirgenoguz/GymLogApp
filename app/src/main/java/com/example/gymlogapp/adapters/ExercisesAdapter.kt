package com.example.gymlogapp.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.gymlogapp.databinding.ExerciseCardBinding
import com.example.gymlogapp.databinding.GymsDayCardBinding
import com.example.gymlogapp.models.Exercise
import com.example.gymlogapp.models.Workout
import com.example.gymlogapp.viewmodel.WorkoutDetailViewModel

class ExercisesAdapter(private val workoutDetailViewModel: WorkoutDetailViewModel): RecyclerView.Adapter<ExercisesAdapter.ExercisesViewHolder>() {

    private var exerciseListByWorkoutId = emptyList<Exercise>()


    fun setExerciseListByWorkoutId(exerciseListByWorkoutId: List<Exercise>){
        this.exerciseListByWorkoutId = exerciseListByWorkoutId as ArrayList<Exercise>
        notifyDataSetChanged()
    }

    inner class ExercisesViewHolder(val binding: ExerciseCardBinding): RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ExercisesViewHolder {
        return ExercisesViewHolder(
            ExerciseCardBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        )
    }

    override fun onBindViewHolder(holder: ExercisesViewHolder, position: Int) {
        holder.binding.tvExerciseName.text = exerciseListByWorkoutId[position].exerciseName
        holder.binding.tvWeightOrSecond.text = exerciseListByWorkoutId[position].averageWeight.toString()
    }

    override fun getItemCount(): Int {
        return exerciseListByWorkoutId.size
    }

}