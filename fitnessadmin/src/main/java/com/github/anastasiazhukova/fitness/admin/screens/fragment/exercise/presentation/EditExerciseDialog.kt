package com.github.anastasiazhukova.fitness.admin.screens.fragment.exercise.presentation

import android.app.Dialog
import android.os.Bundle
import android.view.View
import android.view.ViewGroup.LayoutParams.MATCH_PARENT
import android.view.ViewGroup.LayoutParams.WRAP_CONTENT
import android.widget.Button
import android.widget.EditText
import androidx.fragment.app.DialogFragment
import androidx.fragment.app.FragmentManager
import com.github.anastasiazhukova.fitness.admin.R
import com.github.anastasiazhukova.fitness.admin.screens.fragment.exercise.domain.ExerciseModel
import com.github.anastasiazhukova.fitness.utils.SimpleTextWatcher
import com.github.anastasiazhukova.fitness.utils.extensions.*

interface IEditExerciseDialogClickListener {
    fun onAdded(name: String, description: String)
    fun onEdited(model: ExerciseModel, name: String, description: String)
    fun onDeleted(model: ExerciseModel)
}

class EditExerciseDialog : DialogFragment() {

    private val EDIT_EXERCISE_DIALOG_TAG = "EDIT_EXERCISE_DIALOG_TAG"

    private var model: ExerciseModel? = null
    private var listener: IEditExerciseDialogClickListener? = null

    fun show(fragmentManager: FragmentManager) {
        fragmentManager.beginTransaction().let { fragmentTransaction ->
            val previousDialog = fragmentManager.findFragmentByTag(EDIT_EXERCISE_DIALOG_TAG)

            if (previousDialog != null) {
                fragmentTransaction.remove(previousDialog)
            }

            this.show(fragmentTransaction, EDIT_EXERCISE_DIALOG_TAG)
        }
    }

    fun setModel(model: ExerciseModel) {
        this.model = model
    }

    fun setListener(listener: IEditExerciseDialogClickListener?) {
        this.listener = listener
    }

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        val dialog = super.onCreateDialog(savedInstanceState)

        with(dialog) {
            setContentView(R.layout.dialog_add_or_edit_exercise)
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
        val nameTextInput = view.findViewById<EditText>(R.id.name)
        val descriptionTextInput = view.findViewById<EditText>(R.id.description)

        if (model != null) {
            deleteButton.visible()
        }

        nameTextInput?.addTextChangedListener(SimpleTextWatcher {
            if (it > 0 && !descriptionTextInput.isNullOrEmpty()) {
                saveButton.enable()
            } else {
                saveButton.disable()
            }
        })

        descriptionTextInput?.addTextChangedListener(SimpleTextWatcher {
            if (it > 0 && !nameTextInput.isNullOrEmpty()) {
                saveButton.enable()
            } else {
                saveButton.disable()
            }
        })

        saveButton?.setOnClickListener {
            if (model != null) {
                listener?.onEdited(
                    model!!,
                    nameTextInput.getTextAsString(),
                    descriptionTextInput.getTextAsString()
                )
            } else {
                listener?.onAdded(
                    nameTextInput.getTextAsString(),
                    descriptionTextInput.getTextAsString()
                )
            }

            closeDialog()
        }

        cancelButton?.setOnClickListener { closeDialog() }

        deleteButton?.setOnClickListener {
            if (model != null) {
                listener?.onDeleted(model!!)
            }

            closeDialog()
        }
    }

    private fun closeDialog() {
        dialog?.cancel()
    }
}