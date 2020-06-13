package com.github.anastasiazhukova.fitness.screens.fragments.water.presentation

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.github.anastasiazhukova.fitness.R
import com.github.anastasiazhukova.fitness.domain.water.WaterEntry
import com.github.anastasiazhukova.fitness.domain.water.WaterModel
import com.github.anastasiazhukova.fitness.screens.fragments.water.viewmodel.WaterViewModel
import com.github.anastasiazhukova.fitness.uicomponents.IDatePickerListener
import com.github.anastasiazhukova.fitness.uicomponents.IDatePickerSupport
import com.github.anastasiazhukova.fitness.uicomponents.IElementsListListener
import com.github.anastasiazhukova.fitness.utils.extensions.gone
import com.github.anastasiazhukova.fitness.utils.extensions.visible
import kotlinx.android.synthetic.main.fragment_water.*
import org.koin.androidx.scope.lifecycleScope
import org.koin.androidx.viewmodel.scope.viewModel


class WaterFragment :
    Fragment(R.layout.fragment_water),
    IDatePickerSupport,
    IDatePickerListener,
    IElementsListListener {

    private val waterViewModel: WaterViewModel by lifecycleScope.viewModel(this)
    private val waterAdapter = WaterAdapter()
    private val waterModelObserver = Observer<WaterScreenUiState> {
        updateUi(it)
    }
    private val addWaterDialogClickListener = object : IAddWaterDialogClickListener {
        override fun onAdded(type: String, amount: Int) {
            //todo
            val waterEntry =
                WaterEntry(
                    type = type,
                    amount = amount
                )

            waterViewModel.add(waterEntry)
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        elementsView?.apply {
            getItemsList().apply {
                adapter = waterAdapter
                layoutManager = LinearLayoutManager(context, RecyclerView.VERTICAL, false)
            }
            setListener(this@WaterFragment)
        }

        val currentlySelectedDate = waterViewModel.currentlySelectedDate

        datePicker?.apply {
            setDatePickerSupport(this@WaterFragment)
            setDatePickerListener(this@WaterFragment)
            setCurrentlySelectedDate(currentlySelectedDate)
        }

        waterViewModel.waterLiveData.observe(viewLifecycleOwner, waterModelObserver)
        waterViewModel.get(currentlySelectedDate)
    }

    override fun getFragmentManagerForDatePicker(): FragmentManager? = parentFragmentManager

    override fun onDatePicked(date: Long) = waterViewModel.get(date)

    override fun onAddButtonClicked() {
        parentFragmentManager.let { fragmentManager ->
            AddWaterEntryDialog().apply {
                setListener(addWaterDialogClickListener)
                show(fragmentManager)
            }
        }
    }

    private fun updateUi(uiState: WaterScreenUiState) {
        when (uiState) {
            is WaterScreenUiState.Loading -> setLoadingState()
            is WaterScreenUiState.OperationInProgress -> setOperationInProgressState()
            is WaterScreenUiState.Success -> setModel(uiState.model)
            is WaterScreenUiState.Error -> setError(uiState.e)
        }
    }

    private fun setLoadingState() {
        progress.visible()
        waterAdapter.items = emptyList()
    }

    private fun setOperationInProgressState() {
        progress.visible()
    }

    private fun setModel(model: WaterModel?) {
        progress.gone()
        model?.let {
            waterAdapter.items = it.entries
        }
    }

    private fun setError(e: Throwable) {
        progress.gone()
        Toast.makeText(context, "Error happend. ${e.message}", Toast.LENGTH_SHORT).show()
    }
}