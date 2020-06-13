package com.github.anastasiazhukova.fitness.screens.fragments.calories.presentation

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.github.anastasiazhukova.fitness.R
import com.github.anastasiazhukova.fitness.domain.calories.CaloriesEntry
import com.github.anastasiazhukova.fitness.domain.calories.CaloriesModel
import com.github.anastasiazhukova.fitness.screens.fragments.calories.viewmodel.CaloriesViewModel
import com.github.anastasiazhukova.fitness.uicomponents.IDatePickerListener
import com.github.anastasiazhukova.fitness.uicomponents.IDatePickerSupport
import com.github.anastasiazhukova.fitness.uicomponents.IElementsListListener
import com.github.anastasiazhukova.fitness.utils.extensions.gone
import com.github.anastasiazhukova.fitness.utils.extensions.visible
import kotlinx.android.synthetic.main.fragment_water.*
import org.koin.androidx.scope.lifecycleScope
import org.koin.androidx.viewmodel.scope.viewModel


class CaloriesFragment :
    Fragment(R.layout.fragment_calories),
    IDatePickerSupport,
    IDatePickerListener,
    IElementsListListener {

    private val caloriesViewModel: CaloriesViewModel by lifecycleScope.viewModel(this)
    private val caloriesAdapter = CaloriesAdapter()
    private val caloriesModelObserver = Observer<CaloriesScreenUiState> {
        updateUi(it)
    }
    private val addWaterDialogClickListener = object : IAddCaloriesDialogClickListener {

        override fun onAdded(name: String, calories: Int, weight: Int) {
            val caloriesEntry =
                CaloriesEntry(
                    name = name,
                    weight = weight,
                    calories = calories
                )

            caloriesViewModel.add(caloriesEntry)
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        elementsView?.apply {
            getItemsList().apply {
                adapter = caloriesAdapter
                layoutManager = LinearLayoutManager(context, RecyclerView.VERTICAL, false)
            }
            setListener(this@CaloriesFragment)
        }

        val currentlySelectedDate = caloriesViewModel.currentlySelectedDate

        datePicker?.apply {
            setDatePickerSupport(this@CaloriesFragment)
            setDatePickerListener(this@CaloriesFragment)
            setCurrentlySelectedDate(currentlySelectedDate)
        }

        caloriesViewModel.caloriesLiveData.observe(viewLifecycleOwner, caloriesModelObserver)
        caloriesViewModel.get(currentlySelectedDate)
    }

    override fun getFragmentManagerForDatePicker(): FragmentManager? = parentFragmentManager

    override fun onDatePicked(date: Long) = caloriesViewModel.get(date)

    override fun onAddButtonClicked() {
        parentFragmentManager.let { fragmentManager ->
            AddCaloriesEntryDialog().apply {
                setListener(addWaterDialogClickListener)
                show(fragmentManager)
            }
        }
    }

    private fun updateUi(uiState: CaloriesScreenUiState) {
        when (uiState) {
            is CaloriesScreenUiState.Loading -> setLoadingState()
            is CaloriesScreenUiState.OperationInProgress -> setOperationInProgressState()
            is CaloriesScreenUiState.Success -> setModel(uiState.model)
            is CaloriesScreenUiState.Error -> setError(uiState.e)
        }
    }

    private fun setLoadingState() {
        progress.visible()
        caloriesAdapter.items = emptyList()
    }

    private fun setOperationInProgressState() {
        progress.visible()
    }

    private fun setModel(model: CaloriesModel?) {
        progress.gone()
        model?.let {
            caloriesAdapter.items = it.entries
        }
    }

    private fun setError(e: Throwable) {
        progress.gone()
        Toast.makeText(context, "Error happend. ${e.message}", Toast.LENGTH_SHORT).show()
    }
}