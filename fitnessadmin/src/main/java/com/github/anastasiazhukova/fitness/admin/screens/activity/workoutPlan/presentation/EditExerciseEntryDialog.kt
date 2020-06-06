package com.github.anastasiazhukova.fitness.admin.screens.activity.workoutPlan.presentation

import android.app.Dialog
import android.os.Bundle
import android.view.View
import android.view.ViewGroup.LayoutParams.MATCH_PARENT
import android.view.ViewGroup.LayoutParams.WRAP_CONTENT
import android.widget.*
import androidx.fragment.app.DialogFragment
import androidx.fragment.app.FragmentManager
import com.github.anastasiazhukova.fitness.admin.R
import com.github.anastasiazhukova.fitness.admin.screens.common.exercise.domain.ExerciseModel
import com.github.anastasiazhukova.fitness.domain.workoutPlan.ExerciseEntry
import com.github.anastasiazhukova.fitness.utils.SimpleTextWatcher
import com.github.anastasiazhukova.fitness.utils.extensions.*

interface IEditExerciseEntryDialogClickListener {
    fun onAdded(entry: ExerciseEntry)
    fun onEdited(entry: ExerciseEntry)
    fun onDeleted(entry: ExerciseEntry)
}

class EditExerciseEntryDialog : DialogFragment() {

    private val EDIT_EXERCISE_DIALOG_TAG = "EDIT_EXERCISE_ENTRY_DIALOG_TAG"

    private var entry: ExerciseEntry? = null
    private var exercisesList: List<ExerciseModel>? = null
    private var listener: IEditExerciseEntryDialogClickListener? = null

    fun show(fragmentManager: FragmentManager) {
        fragmentManager.beginTransaction().let { fragmentTransaction ->
            val previousDialog = fragmentManager.findFragmentByTag(EDIT_EXERCISE_DIALOG_TAG)

            if (previousDialog != null) {
                fragmentTransaction.remove(previousDialog)
            }

            this.show(fragmentTransaction, EDIT_EXERCISE_DIALOG_TAG)
        }
    }

    fun setModel(entry: ExerciseEntry?) {
        this.entry = entry
    }

    fun setExercisesList(exercisesList: List<ExerciseModel>) {
        this.exercisesList = exercisesList
    }

    fun setListener(listener: IEditExerciseEntryDialogClickListener?) {
        this.listener = listener
    }

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        val dialog = super.onCreateDialog(savedInstanceState)

        with(dialog) {
            setContentView(R.layout.dialog_add_or_edit_exercise_entry)
            setCancelable(true)
            setCanceledOnTouchOutside(false)
            window?.apply {
                setLayout(MATCH_PARENT, WRAP_CONTENT)
                initViews(decorView)
            }
        }

        return dialog
    }

    private fun initViews(view: View) {
        val saveButton = view.findViewById<Button>(R.id.saveButton)
        val cancelButton = view.findViewById<Button>(R.id.cancelButton)
        val deleteButton = view.findViewById<Button>(R.id.deleteButton)
        val titleTextView = view.findViewById<TextView>(R.id.title)
        val exerciseSelector = view.findViewById<Spinner>(R.id.exerciseSelector)
        val timeInputTextView = view.findViewById<EditText>(R.id.timeInput)
        val descriptionTextView = view.findViewById<TextView>(R.id.description)
        val commentsTextView = view.findViewById<EditText>(R.id.comments)

        titleTextView.setText(R.string.edit_exercise_title_add)

        exercisesList?.let { exercisesList ->
            exerciseSelector.apply {
                adapter = ArrayAdapter(
                    view.context,
                    R.layout.support_simple_spinner_dropdown_item,
                    exercisesList.map { it.name }
                )

                onItemSelectedListener = object : AdapterView.OnItemSelectedListener {

                    override fun onItemSelected(
                        parent: AdapterView<*>?,
                        view: View?,
                        position: Int,
                        id: Long
                    ) {
                        descriptionTextView.text = exercisesList[position].description
                    }

                    override fun onNothingSelected(parent: AdapterView<*>?) {}
                }
            }

            entry?.let { entry ->
                val indexOfSelectedExercise =
                    exercisesList.indexOfFirst { it.id == entry.relatedExerciseId }
                val selectedExerciseModel = exercisesList[indexOfSelectedExercise]

                exerciseSelector.setSelection(indexOfSelectedExercise)
                timeInputTextView.setText(entry.timeInMillis.toSecondsAsInt().toString())
                descriptionTextView.text = selectedExerciseModel.description
                commentsTextView.setText(entry.comments)
                titleTextView.setText(R.string.edit_exercise_title_edit)

                deleteButton.visible()
                deleteButton.setOnClickListener {
                    listener?.onDeleted(entry)

                    closeDialog()
                }

                saveButton.enable()
            }

            timeInputTextView.addTextChangedListener(SimpleTextWatcher {
                if (it > 0 && !timeInputTextView.isNullOrEmpty()) {
                    saveButton.enable()
                } else {
                    saveButton.disable()
                }
            })

            saveButton?.setOnClickListener {
                if (entry != null) {
                    val selectedExercise = exercisesList[exerciseSelector.selectedItemPosition]

                    entry?.copy(
                        relatedExerciseId = selectedExercise.id,
                        name = selectedExercise.name,
                        timeInMillis = timeInputTextView.asInt().toMillisAsInt(),
                        comments = commentsTextView.getTextAsString()
                    )?.let { newEntry ->
                        listener?.onEdited(newEntry)
                    }
                } else {
                    val selectedExercise = exercisesList[exerciseSelector.selectedItemPosition]

                    val newEntry = ExerciseEntry(
                        relatedExerciseId = selectedExercise.id,
                        name = selectedExercise.name,
                        timeInMillis = timeInputTextView.asInt().toMillisAsInt(),
                        comments = commentsTextView.getTextAsString()
                    )

                    listener?.onAdded(newEntry)
                }

                closeDialog()
            }

            cancelButton?.setOnClickListener { closeDialog() }
        }
    }

    private fun closeDialog() {
        dialog?.cancel()
    }
}