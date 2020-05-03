package com.github.anastasiazhukova.fitness.screens.activities.workout.presentation

import android.content.Context
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import com.github.anastasiazhukova.fitness.R
import com.github.anastasiazhukova.fitness.screens.activities.workout.domain.model.ExerciseEntry
import com.github.anastasiazhukova.fitness.screens.activities.workout.domain.model.WorkoutModel
import com.github.anastasiazhukova.fitness.screens.activities.workout.domain.params.WorkoutParams
import com.github.anastasiazhukova.fitness.screens.activities.workout.viewmodel.WorkoutViewModel
import com.github.anastasiazhukova.fitness.utils.constants.Constants
import com.github.anastasiazhukova.fitness.utils.constants.Constants.String.EMPTY
import com.github.anastasiazhukova.fitness.utils.extensions.getActivityExtra
import com.github.anastasiazhukova.fitness.utils.extensions.gone
import com.github.anastasiazhukova.fitness.utils.extensions.startActivity
import com.github.anastasiazhukova.fitness.utils.extensions.visible
import kotlinx.android.synthetic.main.activity_workout.*
import org.koin.androidx.scope.lifecycleScope
import org.koin.androidx.viewmodel.scope.viewModel

class WorkoutActivity : AppCompatActivity(R.layout.activity_workout) {

    val TAG = "DebugTag WorkoutActivity";

    private val workoutViewModel: WorkoutViewModel by lifecycleScope.viewModel(this)
    private val workoutModelObserver = Observer<WorkoutUiState> {
        setWorkoutUiState(it)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        workoutViewModel.workoutLiveData.observe(this, workoutModelObserver)

        (intent.getActivityExtra<WorkoutParams>())?.let { params ->
            workoutViewModel.load(params)
        }

        initButtons()
    }

    private fun initButtons() {
        pauseResumeButton.setOnClickListener {
            if (!workoutViewModel.isPaused) {
                workoutViewModel.isPaused = true
                countDownTimer.pause()
                pauseResumeButton.setText(R.string.workout_resume)
            } else {
                workoutViewModel.isPaused = false
                countDownTimer.restart()
                pauseResumeButton.setText(R.string.workout_pause)
            }
        }

        finishButton.setOnClickListener {
            finishWorkout()
        }
    }

    private fun finishWorkout() {
        finish()
    }

    private fun setWorkoutUiState(workoutUiState: WorkoutUiState) {
        when (workoutUiState) {
            is WorkoutUiState.Loading -> setLoadingState()
            is WorkoutUiState.Success -> startWorkout(workoutUiState.model)
            is WorkoutUiState.Error -> setErrorState(workoutUiState.e)
        }
    }

    private fun setLoadingState() {
        progress.visible()
        workoutContainer.gone()
        Log.d(TAG, "setLoadingState: ")
    }

    private fun setErrorState(e: Throwable) {
        Log.d(TAG, "setErrorState: e = $e")
    }

    private fun startWorkout(workoutModel: WorkoutModel?) {
        progress.gone()
        workoutContainer.visible()

        workoutModel?.let {
            countDownTimer.setOnCountdownEndListener {
                countDownTimer.setOnCountdownEndListener(null)
                startExercise(workoutModel.items, 0)
            }
            countDownTimer.start(Constants.Time.SECONDS_5_IN_MILLIS)
        }

    }

    private fun startExercise(items: List<ExerciseEntry>, position: Int) {
        if (position < items.count()) {
            workoutViewModel.currentExerciseIndex = position
            processWorkoutEntity(items[position]) {
                startExercise(items, position + 1)
            }
        } else {
            finishWorkout()
        }
    }

    private fun processWorkoutEntity(exerciseEntry: ExerciseEntry, onEnd: () -> Unit) {
        countDownTimer.setOnCountdownEndListener {
            countDownTimer.setOnCountdownEndListener(null)
            onEnd()
        }

        exerciseLabel.text = exerciseEntry.name


        if (exerciseEntry.description.isNotEmpty() || !exerciseEntry.comments.isNullOrEmpty()) {
            descriptionContainer.visible()
            description.text = exerciseEntry.description
            comments.text = exerciseEntry.comments ?: EMPTY
        } else {
            descriptionContainer.gone()
        }

        countDownTimer.start(exerciseEntry.time.toLong())
    }

    companion object Companion {
        fun start(context: Context, params: WorkoutParams) {
            context.startActivity(WorkoutActivity::class.java, params)
        }
    }
}