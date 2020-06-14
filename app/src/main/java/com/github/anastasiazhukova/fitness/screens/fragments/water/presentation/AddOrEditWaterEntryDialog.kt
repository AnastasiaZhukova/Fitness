package com.github.anastasiazhukova.fitness.screens.fragments.water.presentation

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
import com.github.anastasiazhukova.fitness.domain.water.WaterEntry
import com.github.anastasiazhukova.fitness.utils.SimpleTextWatcher
import com.github.anastasiazhukova.fitness.utils.extensions.asInt
import com.github.anastasiazhukova.fitness.utils.extensions.disable
import com.github.anastasiazhukova.fitness.utils.extensions.enable
import com.github.anastasiazhukova.fitness.utils.extensions.visible
import com.google.android.material.chip.ChipGroup

interface IWaterDialogClickListener {
    fun onAdded(type: String, amount: Int)
    fun onEdited(model: WaterEntry, type: String, amount: Int)
    fun onDeleted(model: WaterEntry)
}

class AddOrEditWaterEntryDialog : DialogFragment() {

    private val ADD_WATER_ENTRY_DIALOG_TAG = "ADD_WATER_ENTRY_DIALOG_TAG"

    private var model: WaterEntry? = null
    private var listener: IWaterDialogClickListener? = null

    fun show(fragmentManager: FragmentManager) {
        fragmentManager.beginTransaction().let { fragmentTransaction ->
            val previousDialog = fragmentManager.findFragmentByTag(ADD_WATER_ENTRY_DIALOG_TAG)

            if (previousDialog != null) {
                fragmentTransaction.remove(previousDialog)
            }

            this.show(fragmentTransaction, ADD_WATER_ENTRY_DIALOG_TAG)
        }
    }

    fun setModel(model: WaterEntry) {
        this.model = model
    }

    fun setListener(listener: IWaterDialogClickListener?) {
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
        val deleteButton = view.findViewById<Button>(R.id.deleteButton)
        val titleTextView = view.findViewById<TextView>(R.id.title)
        val amountTextInput = view.findViewById<EditText>(R.id.amountField)
        val waterGroup = view.findViewById<ChipGroup>(R.id.waterGroup)

        model?.let {
            titleTextView.setText(R.string.add_calories_entry_edit_dialog_title)
            amountTextInput.setText(it.amount.toString())

            val typeId = when (it.type) {
                "Water" -> R.id.water
                "Coffee" -> R.id.coffee
                "Milk" -> R.id.milk
                "Tea" -> R.id.tea
                else -> R.id.water
            }

            waterGroup.check(typeId)

            deleteButton.visible()
        }

        amountTextInput?.addTextChangedListener(SimpleTextWatcher {
            if (it > 0) {
                saveButton.enable()
            } else {
                saveButton.disable()
            }
        })

        saveButton?.setOnClickListener {
            val type = when (waterGroup.checkedChipId) {
                R.id.water -> "Water"
                R.id.coffee -> "Coffee"
                R.id.milk -> "Milk"
                R.id.tea -> "Tea"
                else -> "Water"
            }

            if (model != null) {
                listener?.onEdited(model!!, type, amountTextInput.asInt())
            } else {
                listener?.onAdded(type, amountTextInput.asInt())
            }

            closeDialog()
        }

        cancelButton?.setOnClickListener { closeDialog() }

        deleteButton.setOnClickListener {
            model?.let {
                listener?.onDeleted(it)
            }

            closeDialog()
        }
    }

    private fun closeDialog() {
        dialog?.cancel()
    }
}