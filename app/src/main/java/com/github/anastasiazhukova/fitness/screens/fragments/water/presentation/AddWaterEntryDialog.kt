package com.github.anastasiazhukova.fitness.screens.fragments.water.presentation

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
import com.github.anastasiazhukova.fitness.utils.extensions.asInt
import com.github.anastasiazhukova.fitness.utils.extensions.disable
import com.github.anastasiazhukova.fitness.utils.extensions.enable

interface IAddWaterDialogClickListener {
    fun onAdded(amount: Int)
}

class AddWaterEntryDialog : DialogFragment() {

    private val ADD_WATER_ENTRY_DIALOG_TAG = "ADD_WATER_ENTRY_DIALOG_TAG"

    private var listener: IAddWaterDialogClickListener? = null

    fun show(fragmentManager: FragmentManager) {
        fragmentManager.beginTransaction().let { fragmentTransaction ->
            val previousDialog = fragmentManager.findFragmentByTag(ADD_WATER_ENTRY_DIALOG_TAG)

            if (previousDialog != null) {
                fragmentTransaction.remove(previousDialog)
            }

            this.show(fragmentTransaction, ADD_WATER_ENTRY_DIALOG_TAG)
        }
    }

    fun setListener(listener: IAddWaterDialogClickListener?) {
        this.listener = listener
    }

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        val dialog = super.onCreateDialog(savedInstanceState)

        with(dialog) {
            setContentView(R.layout.dialog_add_water_entry)
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
        val amountTextInput = view.findViewById<EditText>(R.id.amountField)

        amountTextInput?.addTextChangedListener(SimpleTextWatcher {
            if (it > 0) {
                saveButton.enable()
            } else {
                saveButton.disable()
            }
        })

        saveButton?.setOnClickListener {
            listener?.onAdded(amountTextInput.asInt())
            closeDialog()
        }

        cancelButton?.setOnClickListener { closeDialog() }
    }

    private fun closeDialog() {
        dialog?.cancel()
    }
}