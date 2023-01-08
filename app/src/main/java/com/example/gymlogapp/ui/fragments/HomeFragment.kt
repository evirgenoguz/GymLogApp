package com.example.gymlogapp.ui.fragments

import android.app.AlertDialog
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.gymlogapp.databinding.FragmentHomeBinding
import com.example.gymlogapp.models.Workout
import com.example.gymlogapp.adapters.WorkoutsAdapter
import com.example.gymlogapp.databinding.AddWorkoutDialogBinding
import com.example.gymlogapp.viewmodel.HomeViewModel
import java.text.SimpleDateFormat
import java.util.*


class HomeFragment : Fragment() {

    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!



    private lateinit var workoutList: List<Workout>
    private lateinit var workoutsAdapter: WorkoutsAdapter

    private lateinit var homeMvvm: HomeViewModel



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        homeMvvm = ViewModelProvider(this)[HomeViewModel::class.java]

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentHomeBinding.inflate(layoutInflater, container, false)
        val view = binding.root
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        prepareRecyclerView()
        observerWorkouts()

        onAddFabClicked()

    }

    private fun onAddFabClicked() {
        binding.floatingActionButton.setOnClickListener {
            val binding = AddWorkoutDialogBinding.inflate(LayoutInflater.from(context))
            binding.datePicker.maxDate = System.currentTimeMillis()
            val addWorkoutDialogBuilder = AlertDialog.Builder(requireContext())
                .setView(binding.root)
                .show()
//            binding.etDate.text = "Today's Date" as Editable

            binding.bAdd.setOnClickListener {
                val month = binding.datePicker.month
                val day = binding.datePicker.dayOfMonth
                val year = binding.datePicker.year

                val workoutDate = convertDateToString(day, month, year)

                val workout = Workout(0, workoutDate, binding.etTypeOfWorkout.text.toString())
                homeMvvm.addWorkout(workout)
                addWorkoutDialogBuilder.dismiss()
            }
        }

    }

    private fun convertDateToString(day: Int, month: Int, year: Int): String {
        val calendar = Calendar.getInstance()
        calendar.set(Calendar.DAY_OF_MONTH, day)
        calendar.set(Calendar.MONTH, month)
        calendar.set(Calendar.YEAR, year)

// Create a SimpleDateFormat with the desired date format
        val format = SimpleDateFormat("dd/MM/yyyy", Locale.getDefault())

// Format the Calendar object as a String in the desired format
        val dateString = format.format(calendar.time)

        return dateString
    }

    private fun observerWorkouts() {
       homeMvvm.getAllWorkout.observe(viewLifecycleOwner, Observer { workoutList ->
           workoutsAdapter.setWorkoutList(workoutList)
       })
    }

    private fun prepareRecyclerView() {
        workoutsAdapter = WorkoutsAdapter(homeMvvm)

        binding.rvGymsDay.apply {
            layoutManager = LinearLayoutManager(context)
            adapter = workoutsAdapter
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}