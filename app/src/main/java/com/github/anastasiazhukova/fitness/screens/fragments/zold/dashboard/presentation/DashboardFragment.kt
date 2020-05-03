package com.github.anastasiazhukova.fitness.screens.fragments.zold.dashboard.presentation

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import com.github.anastasiazhukova.fitness.R
import com.github.anastasiazhukova.fitness.screens.activities.workout.presentation.WorkoutActivity
import com.github.anastasiazhukova.fitness.screens.fragments.zold.dashboard.domain.DashboardModel
import com.github.anastasiazhukova.fitness.screens.fragments.zold.dashboard.domain.MyGoal
import com.github.anastasiazhukova.fitness.screens.fragments.zold.dashboard.domain.Statistics
import com.github.anastasiazhukova.fitness.screens.fragments.zold.dashboard.domain.Workout
import com.github.anastasiazhukova.fitness.screens.fragments.zold.dashboard.viewmodel.DashboardViewModel
import kotlinx.android.synthetic.main.fragment_home.*
import org.koin.androidx.scope.lifecycleScope
import org.koin.androidx.viewmodel.scope.viewModel

class DashboardFragment : Fragment(R.layout.fragment_home) {

    private val dashboardViewModel: DashboardViewModel by lifecycleScope.viewModel(this)
    private val dashboardModelObserver = Observer<DashboardModel?> {
        setDashboardModel(it)
    }
    private val startWorkoutListener = View.OnClickListener { startWorkout() }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        dashboardViewModel.dashboardLiveData.observe(viewLifecycleOwner, dashboardModelObserver)
        dashboardViewModel.load()
    }

    private fun setDashboardModel(dashboardModel: DashboardModel?) {
        dashboardModel?.let { model ->
            setWorkout(model.workout)
            setMyGoal(model.myGoal)
            setTodayStatistics(model.todayStatistics)
            setGeneralStatistics(model.generalStatistics)
        }
    }

    private fun setWorkout(workout: Workout?) {
        when (workout) {
            is Workout.Break -> setWorkoutBreak(workout)
            is Workout.Ready -> setWorkoutReady(workout)
        }
    }

    private fun setWorkoutBreak(workout: Workout.Break) {
        workoutCard.setWorkoutBreakMode()
        workoutCard.setNextWorkoutDay(workout.nextWorkoutDay)
    }

    private fun setWorkoutReady(workout: Workout.Ready) {
        workoutCard.setWorkoutReadyMode()
        workoutCard.setWorkoutCalories(workout.totalCalories)
        workoutCard.setWorkoutDuration(workout.totalTime)
        workoutCard.setStartWorkoutButtonClickListener(startWorkoutListener)
    }

    private fun setMyGoal(myGoal: MyGoal?) {
        myGoal?.let { goal ->
            myGoalCard.setWeightGoal(goal.weightGoal)
            myGoalCard.setCaloriesGoal(goal.caloriesGoal)
            myGoalCard.setWaterGoal(goal.waterGoal)
        }
    }

    private fun setTodayStatistics(statistics: Statistics?) {
        val label = context?.getString(R.string.statistics_today_label)
        todayRatingCounter.setLabel(label)

        statistics?.let {
            when (statistics) {
                is Statistics.ToBeSet -> {
                    todayRatingCounter.setRatingValue(null)
                }
                is Statistics.Value -> {
                    todayRatingCounter.setRatingValue(statistics.value)
                }
            }
        }
    }

    private fun setGeneralStatistics(statistics: Statistics?) {
        val label = context?.getString(R.string.statistics_general_label)
        generalRatingCounter.setLabel(label)

        statistics?.let {
            when (statistics) {
                is Statistics.ToBeSet -> {
                    generalRatingCounter.setRatingValue(null)
                }
                is Statistics.Value -> {
                    generalRatingCounter.setRatingValue(statistics.value)
                }
            }
        }
    }

    private fun startWorkout() {
        context?.let {
            //WorkoutActivity.start(it)
        }
    }
}