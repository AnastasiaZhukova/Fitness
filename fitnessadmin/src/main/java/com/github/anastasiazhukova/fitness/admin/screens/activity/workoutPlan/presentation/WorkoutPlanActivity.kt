package com.github.anastasiazhukova.fitness.admin.screens.activity.workoutPlan.presentation

import android.content.Context
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.FragmentManager
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.github.anastasiazhukova.fitness.admin.R
import com.github.anastasiazhukova.fitness.admin.screens.activity.workoutPlan.viewmodel.WorkoutPlanViewModel
import com.github.anastasiazhukova.fitness.admin.screens.common.clientDetails.ClientDetailsParams
import com.github.anastasiazhukova.fitness.domain.workoutPlan.ExerciseEntry
import com.github.anastasiazhukova.fitness.domain.workoutPlan.WorkoutPlanModel
import com.github.anastasiazhukova.fitness.uicomponents.IDatePickerListener
import com.github.anastasiazhukova.fitness.uicomponents.IDatePickerSupport
import com.github.anastasiazhukova.fitness.uicomponents.IElementsListListener
import com.github.anastasiazhukova.fitness.utils.extensions.getActivityExtra
import com.github.anastasiazhukova.fitness.utils.extensions.gone
import com.github.anastasiazhukova.fitness.utils.extensions.startActivity
import com.github.anastasiazhukova.fitness.utils.extensions.visible
import kotlinx.android.synthetic.main.activity_workout_plan.*
import org.koin.androidx.scope.lifecycleScope
import org.koin.androidx.viewmodel.scope.viewModel

class WorkoutPlanActivity : AppCompatActivity(R.layout.activity_workout_plan),
    IDatePickerSupport,
    IDatePickerListener,
    IElementsListListener,
    IEditExerciseEntryDialogClickListener,
    IExerciseClickListener {

    private val workoutPlanViewModel: WorkoutPlanViewModel by lifecycleScope.viewModel(this)
    private val exerciseAdapter = ExerciseAdapter()
    private val workoutPlanModelObserver = Observer<WorkoutPlanScreenUiState> {
        updateUi(it)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        intent.getActivityExtra<ClientDetailsParams>()?.let { clientDetailsParams ->
            workoutPlanViewModel.setUser(clientDetailsParams)

            clientName.text = clientDetailsParams.name
        }

        elementsView?.apply {
            getItemsList().apply {
                exerciseAdapter.setExerciseEditClickListener(this@WorkoutPlanActivity)
                adapter = exerciseAdapter
                layoutManager = LinearLayoutManager(context, RecyclerView.VERTICAL, false)
            }
            setListener(this@WorkoutPlanActivity)
        }

        val currentlySelectedDate = workoutPlanViewModel.currentlySelectedDate

        datePicker?.apply {
            setDatePickerSupport(this@WorkoutPlanActivity)
            setDatePickerListener(this@WorkoutPlanActivity)
            setCurrentlySelectedDate(currentlySelectedDate)
        }
    }

    override fun onResume() {
        super.onResume()

        workoutPlanViewModel.workoutPlanLiveData.observe(this, workoutPlanModelObserver)
        workoutPlanViewModel.get(workoutPlanViewModel.currentlySelectedDate)
    }

    override fun getFragmentManagerForDatePicker(): FragmentManager? = supportFragmentManager

    override fun onDatePicked(date: Long) = workoutPlanViewModel.get(date)

    override fun onAddButtonClicked() {
        showEditExerciseDialog()
    }

    override fun onAdded(entry: ExerciseEntry) {
        workoutPlanViewModel.add(entry)
    }

    override fun onEdited(entry: ExerciseEntry) {
        workoutPlanViewModel.update(entry)
    }

    override fun onDeleted(entry: ExerciseEntry) {
        workoutPlanViewModel.delete(entry)
    }

    override fun onEditClicked(model: ExerciseEntry) {
        showEditExerciseDialog(model)
    }

    private fun updateUi(uiState: WorkoutPlanScreenUiState) {
        when (uiState) {
            is WorkoutPlanScreenUiState.Loading -> setLoadingState()
            is WorkoutPlanScreenUiState.OperationInProgress -> setOperationInProgressState()
            is WorkoutPlanScreenUiState.Success -> setModel(uiState.model?.workoutPlanModel)
            is WorkoutPlanScreenUiState.Error -> setError(uiState.e)
        }
    }

    private fun setLoadingState() {
        progress.visible()
        exerciseAdapter.items = emptyList()
    }

    private fun setOperationInProgressState() {
        progress.visible()
    }

    private fun setModel(model: WorkoutPlanModel?) {
        progress.gone()
        model?.let {
            exerciseAdapter.items = it.entries
        }
    }

    private fun setError(e: Throwable) {
        progress.gone()
        Toast.makeText(this, "Error happened. ${e.message}", Toast.LENGTH_SHORT).show()
    }

    private fun showEditExerciseDialog(exerciseEntry: ExerciseEntry? = null) {
        supportFragmentManager.let { fragmentManager ->
            EditExerciseEntryDialog().apply {
                workoutPlanViewModel.getExercisesList()?.let {
                    setExercisesList(it)
                }
                setModel(exerciseEntry)
                setListener(this@WorkoutPlanActivity)
                show(fragmentManager)
            }
        }
    }


    companion object Companion {
        fun start(clientDetailsParams: ClientDetailsParams, context: Context) {
            context.startActivity(WorkoutPlanActivity::class.java, clientDetailsParams)
        }
    }
}