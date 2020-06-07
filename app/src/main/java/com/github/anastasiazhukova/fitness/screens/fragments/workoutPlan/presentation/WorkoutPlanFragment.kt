package com.github.anastasiazhukova.fitness.screens.fragments.workoutPlan.presentation

import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import com.github.anastasiazhukova.fitness.R
import com.github.anastasiazhukova.fitness.domain.workoutPlan.WorkoutPlanModel
import com.github.anastasiazhukova.fitness.screens.activities.workout.presentation.WorkoutActivity
import com.github.anastasiazhukova.fitness.screens.fragments.workoutPlan.viewmodel.WorkoutPlanViewModel
import com.github.anastasiazhukova.fitness.utils.extensions.gone
import com.github.anastasiazhukova.fitness.utils.extensions.visible
import kotlinx.android.synthetic.main.fragment_workout_plan.*
import org.koin.androidx.scope.lifecycleScope
import org.koin.androidx.viewmodel.scope.viewModel

class WorkoutPlanFragment : Fragment(R.layout.fragment_workout_plan) {

    val TAG = "DebugTag WorkoutPlanFragment";

    private val workoutPlanViewModel: WorkoutPlanViewModel by lifecycleScope.viewModel(this)
    private val workoutPlanUiStateObserver = Observer<WorkoutPlanUiState> {
        setWorkoutPlanUiState(it)
    }

    private val startWorkoutListener = View.OnClickListener { startWorkout() }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        workoutPlanViewModel.workoutPlanLiveData.observe(
            viewLifecycleOwner,
            workoutPlanUiStateObserver
        )
    }

    override fun onResume() {
        super.onResume()

        workoutPlanViewModel.get()
    }

    private fun setWorkoutPlanUiState(workoutPlanUiState: WorkoutPlanUiState) {
        when (workoutPlanUiState) {
            is WorkoutPlanUiState.Loading -> setLoadingState()
            is WorkoutPlanUiState.Success -> setWorkoutPlanModel(workoutPlanUiState.model)
            is WorkoutPlanUiState.Error -> setErrorState(workoutPlanUiState.e)
        }
    }

    private fun setLoadingState() {
        workoutCard.gone()
        progress.visible()
        Log.d(TAG, "setLoadingState: ")
    }


    private fun setWorkoutPlanModel(model: WorkoutPlanModel?) {
        Log.d(TAG, "setWorkoutPlanModel: model = $model")
        progress.gone()
        workoutCard.visible()

        if (model == null) {
            setWorkoutBreak()
        } else {
            setWorkoutReady(model)
        }
    }

    private fun setErrorState(e: Throwable) {
        Log.d(TAG, "setErrorState: e = $e")
    }

    private fun setWorkoutBreak() {
        workoutCard.setWorkoutBreakMode()
    }

    private fun setWorkoutReady(model: WorkoutPlanModel) {
        workoutCard.setWorkoutReadyMode()
        workoutCard.setWorkoutCalories(model.calories)
        workoutCard.setWorkoutDuration(model.duration)
        workoutCard.setWorkoutLevel(model.level.toString())
        workoutCard.setStartWorkoutButtonClickListener(startWorkoutListener)
    }

    private fun startWorkout() {
        context?.let {

            workoutPlanViewModel.getWorkoutParams()?.let { params ->
                WorkoutActivity.start(it, params)
            }
        }
    }
}