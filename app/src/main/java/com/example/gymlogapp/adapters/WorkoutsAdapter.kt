package com.example.gymlogapp.adapters

import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.gymlogapp.databinding.GymsDayCardBinding
import com.example.gymlogapp.models.Workout
import com.example.gymlogapp.ui.activities.WorkoutDetailActivity
import com.example.gymlogapp.viewmodel.HomeViewModel

class WorkoutsAdapter(private val homeMvvm: HomeViewModel): RecyclerView.Adapter<WorkoutsAdapter.WorkoutsViewHolder>() {

    private var workoutList = emptyList<Workout>()



    fun setWorkoutList(workoutList: List<Workout>){
        this.workoutList = workoutList as ArrayList<Workout>
        notifyDataSetChanged()
    }

    inner class WorkoutsViewHolder(val binding: GymsDayCardBinding): RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): WorkoutsViewHolder {
        return WorkoutsViewHolder(
            GymsDayCardBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        )
    }

    override fun onBindViewHolder(holder: WorkoutsViewHolder, position: Int) {
        holder.binding.tvGymsDate.text = workoutList[position].date
        holder.binding.tvYourRate.text = workoutList[position].whichType

        holder.binding.imgDelete.setOnClickListener {
            deleteItem(workoutList[position])
        }

        holder.binding.workoutItem.setOnClickListener {
            clickedRvItem(workoutList[position].id, it)

        }
    }

    private fun clickedRvItem(workoutId: Int, view: View) {
        val intent = Intent(view.context, WorkoutDetailActivity::class.java)
        intent.putExtra("workoutId", workoutId)
        view.context.startActivity(intent)
    }


    private fun deleteItem(workout: Workout) {
        homeMvvm.deleteWorkout(workout)
    }

    override fun getItemCount(): Int {
        return workoutList.size
    }
}