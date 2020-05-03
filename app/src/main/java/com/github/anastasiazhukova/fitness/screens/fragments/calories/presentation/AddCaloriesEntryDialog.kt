package com.github.anastasiazhukova.fitness.screens.fragments.calories.presentation

import android.app.Dialog
import android.os.Bundle
import android.view.View
import android.view.ViewGroup.LayoutParams.MATCH_PARENT
import android.view.ViewGroup.LayoutParams.WRAP_CONTENT
import android.widget.Button
import android.widget.EditText
import androidx.fragment.app.DialogFragment
import androidx.fragment.app.FragmentManager
import com.github.anastasiazhukova.fitness.R
import com.github.anastasiazhukova.fitness.utils.SimpleTextWatcher
import com.github.anastasiazhukova.fitness.utils.extensions.*

interface IAddCaloriesDialogClickListener {
    fun onAdded(name: String, calories: Int, weight: Int)
}

class AddCaloriesEntryDialog : DialogFragment() {

    private val ADD_CALORIES_ENTRY_DIALOG_TAG = "ADD_CALORIES_ENTRY_DIALOG_TAG"

    private var listener: IAddCaloriesDialogClickListener? = null

    fun show(fragmentManager: FragmentManager) {
        fragmentManager.beginTransaction().let { fragmentTransaction ->
            val previousDialog = fragmentManager.findFragmentByTag(ADD_CALORIES_ENTRY_DIALOG_TAG)

            if (previousDialog != null) {
                fragmentTransaction.remove(previousDialog)
            }

            this.show(fragmentTransaction, ADD_CALORIES_ENTRY_DIALOG_TAG)
        }
    }

    fun setListener(listener: IAddCaloriesDialogClickListener?) {
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
        val nameTextInput = view.findViewById<EditText>(R.id.nameField)
        val weightTextInput = view.findViewById<EditText>(R.id.weightField)
        val caloriesTextInput = view.findViewById<EditText>(R.id.caloriesField)

        nameTextInput?.addTextChangedListener(SimpleTextWatcher {
            if (it > 0 && weightTextInput.charactersCount() > 0 && caloriesTextInput.charactersCount() > 0) {
                saveButton.enable()
            } else {
                saveButton.disable()
            }
        })

        weightTextInput?.addTextChangedListener(SimpleTextWatcher {
            if (it > 0 && nameTextInput.charactersCount() > 0 && caloriesTextInput.charactersCount() > 0) {
                saveButton.enable()
            } else {
                saveButton.disable()
            }
        })

        caloriesTextInput?.addTextChangedListener(SimpleTextWatcher {
            if (it > 0 && nameTextInput.charactersCount() > 0 && weightTextInput.charactersCount() > 0) {
                saveButton.enable()
            } else {
                saveButton.disable()
            }
        })

        saveButton?.setOnClickListener {
            listener?.onAdded(
                name = nameTextInput.getTextAsString(),
                calories = caloriesTextInput.asInt(),
                weight = weightTextInput.asInt()
            )
            closeDialog()
        }

        cancelButton?.setOnClickListener { closeDialog() }
    }

    private fun closeDialog() {
        dialog?.cancel()
    }
}