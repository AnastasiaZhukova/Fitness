package com.github.anastasiazhukova.fitness.admin.screens.activity.workoutPlan.presentation

import android.content.Context
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.FragmentManager
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.ItemTouchHelper
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
import com.github.anastasiazhukova.fitness.uicomponents.ItemMoveCallback
import com.github.anastasiazhukova.fitness.utils.SimpleTextWatcher
import com.github.anastasiazhukova.fitness.utils.constants.Constants.String.EMPTY
import com.github.anastasiazhukova.fitness.utils.extensions.*
import kotlinx.android.synthetic.main.activity_workout_plan.*
import kotlinx.android.synthetic.main.view_workout_calories_picker.*
import kotlinx.android.synthetic.main.view_workout_level_picker.*
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
                val itemMoveCallback = ItemMoveCallback(exerciseAdapter)
                val itemTouchHelper = ItemTouchHelper(itemMoveCallback)
                itemTouchHelper.attachToRecyclerView(this)
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

        saveButton.setOnClickListener {
            saveModel()
        }

        deleteButton.setOnClickListener {
            deleteModel()
        }

        caloriesInput.addTextChangedListener(SimpleTextWatcher {
            updateSaveButton()

            if (!caloriesInput.isNullOrEmpty()) {
                workoutPlanViewModel.setCalories(caloriesInput.asInt())
            }
        })
        fitnessLevelGroup.setOnCheckedChangeListener { _, _ ->
            updateSaveButton()

            workoutPlanViewModel.setLevel(getFitnessLevelIntById(fitnessLevelGroup.checkedChipId))
        }
    }

    override fun onResume() {
        super.onResume()

        workoutPlanViewModel.workoutPlanLiveData.observe(this, workoutPlanModelObserver)
        workoutPlanViewModel.getWorkoutPlanModel(workoutPlanViewModel.currentlySelectedDate)
    }

    override fun getFragmentManagerForDatePicker(): FragmentManager? = supportFragmentManager

    override fun onDatePicked(date: Long) = workoutPlanViewModel.getWorkoutPlanModel(date)

    override fun onAddButtonClicked() {
        showEditExerciseDialog()
    }

    override fun onAdded(entry: ExerciseEntry) {
        workoutPlanViewModel.addExercise(entry)
    }

    override fun onEdited(entry: ExerciseEntry) {
        workoutPlanViewModel.updateExercise(entry)
    }

    override fun onDeleted(entry: ExerciseEntry) {
        workoutPlanViewModel.deleteExercise(entry)
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
        caloriesInput.setText(EMPTY)
        caloriesInput.disable()
        totalTime.text = EMPTY
        fitnessLevelGroup.check(View.NO_ID)
        fitnessLevelGroup.disable()
        elementsView.disableAddOption()
    }

    private fun setOperationInProgressState() {
        progress.visible()
    }

    private fun setModel(model: WorkoutPlanModel?) {
        progress.gone()
        model?.let {
            exerciseAdapter.items = it.entries

            caloriesInput.setText(model.calories.toString())
            caloriesInput.enable()

            totalTime.text = String.format(
                resources.getString(R.string.total_time),
                model.duration.toReadableTime(
                    resources.getString(R.string.sec),
                    resources.getString(R.string.min)
                )
            )

            fitnessLevelGroup.check(getFitnessLevelIdByInt(model.level))
            fitnessLevelGroup.enable()

            elementsView.enableAddOption()
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

    private fun saveModel() {
        workoutPlanViewModel.save()
    }

    private fun deleteModel() {
        workoutPlanViewModel.delete()
    }

    private fun updateSaveButton() {
        if (isValidInput()) {
            saveButton.enable()
        } else {
            saveButton.disable()
        }
    }

    private fun isValidInput(): Boolean {
        return !caloriesInput.isNullOrEmpty() && fitnessLevelGroup.checkedChipId != View.NO_ID && exerciseAdapter.items.isNotEmpty()
    }

    private fun getFitnessLevelIdByInt(level: Int): Int {
        return when (level) {
            1 -> R.id.fitnessLevel1
            2 -> R.id.fitnessLevel2
            3 -> R.id.fitnessLevel3
            4 -> R.id.fitnessLevel4
            5 -> R.id.fitnessLevel5
            else -> R.id.fitnessLevel1
        }
    }

    private fun getFitnessLevelIntById(id: Int): Int {
        return when (id) {
            R.id.fitnessLevel1 -> 1
            R.id.fitnessLevel2 -> 2
            R.id.fitnessLevel3 -> 3
            R.id.fitnessLevel4 -> 4
            R.id.fitnessLevel5 -> 5
            else -> 1
        }
    }

    companion object Companion {
        fun start(clientDetailsParams: ClientDetailsParams, context: Context) {
            context.startActivity(WorkoutPlanActivity::class.java, clientDetailsParams)
        }
    }
}