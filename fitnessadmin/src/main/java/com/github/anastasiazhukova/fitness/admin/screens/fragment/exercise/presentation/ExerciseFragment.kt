package com.github.anastasiazhukova.fitness.admin.screens.fragment.exercise.presentation

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.github.anastasiazhukova.fitness.admin.R
import com.github.anastasiazhukova.fitness.admin.screens.fragment.exercise.domain.ExerciseModel
import com.github.anastasiazhukova.fitness.admin.screens.fragment.exercise.viewmodel.ExercisesViewModel
import com.github.anastasiazhukova.fitness.utils.extensions.gone
import com.github.anastasiazhukova.fitness.utils.extensions.visible
import kotlinx.android.synthetic.main.fragment_clients.*
import kotlinx.android.synthetic.main.fragment_clients.progress
import kotlinx.android.synthetic.main.fragment_exercise.*
import org.koin.androidx.scope.lifecycleScope
import org.koin.androidx.viewmodel.scope.viewModel


class ExerciseFragment : Fragment(R.layout.fragment_exercise), IExerciseClickListener,
    IEditExerciseDialogClickListener {

    private val exerciseViewModel: ExercisesViewModel by lifecycleScope.viewModel(this)
    private val exerciseAdapter = ExerciseAdapter()
    private val exercisesStateObserver = Observer<ExercisesScreenUiState> {
        setExercisesUiState(it)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        exerciseAdapter.setExerciseEditClickListener(this)
        addButton.setOnClickListener {
            onAddClicked()
        }

        clientsList?.apply {
            adapter = exerciseAdapter
            layoutManager = LinearLayoutManager(context, RecyclerView.VERTICAL, false)
        }

        exerciseViewModel.exercisesLiveData.observe(
            viewLifecycleOwner,
            exercisesStateObserver
        )

        exerciseViewModel.load()
    }

    override fun onEditClicked(model: ExerciseModel) {
        EditExerciseDialog().apply {
            setModel(model)
            setListener(this@ExerciseFragment)
            show(parentFragmentManager)
        }
    }

    override fun onAdded(name: String, description: String) {
        val newModel = ExerciseModel(
            name = name,
            description = description
        )

        exerciseViewModel.add(newModel)
    }

    override fun onEdited(model: ExerciseModel, name: String, description: String) {
        val newModel = model.copy(
            name = name,
            description = description
        )

        exerciseViewModel.update(newModel)
    }

    override fun onDeleted(model: ExerciseModel) {
        exerciseViewModel.delete(model)
    }

    private fun onAddClicked() {
        parentFragmentManager.let { fragmentManager ->
            EditExerciseDialog().apply {
                setListener(this@ExerciseFragment)
                show(fragmentManager)
            }
        }
    }

    private fun setExercisesUiState(uiState: ExercisesScreenUiState) {
        when (uiState) {
            is ExercisesScreenUiState.Loading -> setLoadingState()
            is ExercisesScreenUiState.Success -> setModel(uiState.model)
            is ExercisesScreenUiState.Error -> setError(uiState.e)
        }
    }

    private fun setLoadingState() {
        progress.visible()
        exerciseAdapter.items = emptyList()
    }

    private fun setModel(model: List<ExerciseModel>) {
        progress.gone()
        exerciseAdapter.items = model
    }

    private fun setError(e: Throwable) {
        progress.gone()
        Toast.makeText(context, "Error happened. ${e.message}", Toast.LENGTH_SHORT).show()
    }
}