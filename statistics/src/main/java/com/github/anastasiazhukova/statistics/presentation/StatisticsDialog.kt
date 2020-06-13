package com.github.anastasiazhukova.statistics.presentation

import android.app.Dialog
import android.os.Bundle
import android.view.View
import android.view.ViewGroup.LayoutParams.MATCH_PARENT
import android.view.ViewGroup.LayoutParams.WRAP_CONTENT
import android.widget.Button
import android.widget.TextView
import androidx.fragment.app.DialogFragment
import androidx.fragment.app.FragmentManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.github.anastasiazhukova.fitness.utils.extensions.toReadableDate
import com.github.anastasiazhukova.fitness.utils.extensions.visible
import com.github.anastasiazhukova.statistics.R
import com.github.anastasiazhukova.statistics.domain.StatisticsEntry

class StatisticsDialog : DialogFragment() {

    private val STATISTICS_DIALOG_TAG = "STATISTICS_DIALOG_TAG"

    private var model: StatisticsEntry? = null

    fun show(fragmentManager: FragmentManager) {
        fragmentManager.beginTransaction().let { fragmentTransaction ->
            val previousDialog = fragmentManager.findFragmentByTag(STATISTICS_DIALOG_TAG)

            if (previousDialog != null) {
                fragmentTransaction.remove(previousDialog)
            }

            this.show(fragmentTransaction, STATISTICS_DIALOG_TAG)
        }
    }

    fun setModel(model: StatisticsEntry) {
        this.model = model
    }

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        val dialog = super.onCreateDialog(savedInstanceState)

        with(dialog) {
            setContentView(R.layout.dialog_statistics)
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
        val items = view.findViewById<RecyclerView>(R.id.items)
        val closeButton = view.findViewById<Button>(R.id.closeButton)
        val titleTextView = view.findViewById<TextView>(R.id.title)
        val noDataMessage = view.findViewById<TextView>(R.id.noDataMessage)

        model?.let { entry ->
            when (entry) {
                is StatisticsEntry.Water -> {
                    val waterModel = entry.model

                    titleTextView.text = waterModel.date.toReadableDate()
                    val waterAdapter = WaterAdapter()
                    items.adapter = waterAdapter
                    items.layoutManager = LinearLayoutManager(context, RecyclerView.VERTICAL, false)

                    waterAdapter.items = waterModel.entries
                }
                is StatisticsEntry.Calories -> {
                    val caloriesModel = entry.model

                    titleTextView.text = caloriesModel.date.toReadableDate()
                    val caloriesAdapter = CaloriesAdapter()
                    items.adapter = caloriesAdapter
                    items.layoutManager = LinearLayoutManager(context, RecyclerView.VERTICAL, false)

                    caloriesAdapter.items = caloriesModel.entries
                }
                is StatisticsEntry.Weight -> {
                    titleTextView.text = entry.date.toReadableDate()
                    noDataMessage.visible()
                }
            }
        }

        closeButton.setOnClickListener {
            closeDialog()
        }
    }

    private fun closeDialog() {
        dialog?.cancel()
    }
}