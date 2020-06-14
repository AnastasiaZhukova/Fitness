package com.github.anastasiazhukova.fitness.screens.fragments.calories.presentation

import android.app.Dialog
import android.os.Bundle
import android.view.View
import android.view.ViewGroup.LayoutParams.MATCH_PARENT
import android.view.ViewGroup.LayoutParams.WRAP_CONTENT
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.fragment.app.DialogFragment
import androidx.fragment.app.FragmentManager
import com.github.anastasiazhukova.fitness.R
import com.github.anastasiazhukova.fitness.domain.calories.CaloriesEntry
import com.github.anastasiazhukova.fitness.utils.SimpleTextWatcher
import com.github.anastasiazhukova.fitness.utils.extensions.*

interface IEditCaloriesDialogClickListener {
    fun onAdded(name: String, calories: Int, weight: Int)
    fun onEdited(model: CaloriesEntry, name: String, calories: Int, weight: Int)
    fun onDeleted(model: CaloriesEntry)
}

class AddOrEditCaloriesEntryDialog : DialogFragment() {

    private val ADD_CALORIES_ENTRY_DIALOG_TAG = "ADD_CALORIES_ENTRY_DIALOG_TAG"

    private var model: CaloriesEntry? = null
    private var listener: IEditCaloriesDialogClickListener? = null

    fun show(fragmentManager: FragmentManager) {
        fragmentManager.beginTransaction().let { fragmentTransaction ->
            val previousDialog = fragmentManager.findFragmentByTag(ADD_CALORIES_ENTRY_DIALOG_TAG)

            if (previousDialog != null) {
                fragmentTransaction.remove(previousDialog)
            }

            this.show(fragmentTransaction, ADD_CALORIES_ENTRY_DIALOG_TAG)
        }
    }

    fun setModel(model: CaloriesEntry) {
        this.model = model
    }

    fun setListener(listener: IEditCaloriesDialogClickListener?) {
        this.listener = listener
    }

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        val dialog = super.onCreateDialog(savedInstanceState)

        with(dialog) {
            setContentView(R.layout.dialog_add_calories_entry)
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
        val nameTextInput = view.findViewById<EditText>(R.id.nameField)
        val weightTextInput = view.findViewById<EditText>(R.id.weightField)
        val caloriesTextInput = view.findViewById<EditText>(R.id.caloriesField)

        model?.let {
            titleTextView.setText(R.string.add_calories_entry_edit_dialog_title)
            nameTextInput.setText(it.name)
            weightTextInput.setText(it.weight.toString())
            caloriesTextInput.setText(it.calories.toString())
            deleteButton.visible()
        }

        nameTextInput?.addTextChangedListener(SimpleTextWatcher {
            if (isValidInput(it, nameTextInput, weightTextInput, caloriesTextInput)) {
                saveButton.enable()
            } else {
                saveButton.disable()
            }
        })

        weightTextInput?.addTextChangedListener(SimpleTextWatcher {
            if (isValidInput(it, nameTextInput, weightTextInput, caloriesTextInput)) {
                saveButton.enable()
            } else {
                saveButton.disable()
            }
        })

        caloriesTextInput?.addTextChangedListener(SimpleTextWatcher {
            if (isValidInput(it, nameTextInput, weightTextInput, caloriesTextInput)) {
                saveButton.enable()
            } else {
                saveButton.disable()
            }
        })

        saveButton?.setOnClickListener {
            if (model != null) {
                listener?.onEdited(
                    model!!,
                    name = nameTextInput.getTextAsString(),
                    calories = caloriesTextInput.asInt(),
                    weight = weightTextInput.asInt()
                )
            } else {
                listener?.onAdded(
                    name = nameTextInput.getTextAsString(),
                    calories = caloriesTextInput.asInt(),
                    weight = weightTextInput.asInt()
                )
            }
            closeDialog()
        }

        deleteButton.setOnClickListener {
            model?.let {
                listener?.onDeleted(it)
            }

            closeDialog()
        }

        cancelButton?.setOnClickListener { closeDialog() }
    }

    private fun isValidInput(
        charactersCount: Int,
        nameTextInput: EditText?,
        weighTextInput: EditText?,
        caloriesTextInput: EditText?
    ): Boolean {
        return (charactersCount > 0
                && nameTextInput.charactersCount() > 0
                && weighTextInput.charactersCount() > 0
                && weighTextInput.asInt() > 0
                && caloriesTextInput.charactersCount() > 0
                && caloriesTextInput.asInt() > 0)
    }

    private fun closeDialog() {
        dialog?.cancel()
    }
}