package com.github.anastasiazhukova.statistics.presentation

import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.github.anastasiazhukova.fitness.utils.extensions.getFragmentExtra
import com.github.anastasiazhukova.fitness.utils.extensions.gone
import com.github.anastasiazhukova.fitness.utils.extensions.putFragmentExtra
import com.github.anastasiazhukova.fitness.utils.extensions.visible
import com.github.anastasiazhukova.statistics.R
import com.github.anastasiazhukova.statistics.domain.StatisticsEntry
import com.github.anastasiazhukova.statistics.domain.StatisticsPageModel
import com.github.anastasiazhukova.statistics.domain.StatisticsPageParams
import com.github.anastasiazhukova.statistics.viewmodel.StatisticsViewModel
import kotlinx.android.synthetic.main.fragment_statistics.*
import org.koin.androidx.scope.lifecycleScope
import org.koin.androidx.viewmodel.scope.viewModel


class StatisticsFragment : Fragment(R.layout.fragment_statistics), IValueSelectedListener {

    private val statisticsViewModel: StatisticsViewModel by lifecycleScope.viewModel(this)
    private val statisticsModelObserver = Observer<StatisticsPageUiState> {
        updateUi(it)
    }
    private val statisticsAdapter = StatisticsAdapter()
    private var isRefreshable = true

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        arguments?.getFragmentExtra<StatisticsPageParams>()?.let {
            statisticsViewModel.setParams(it)
            isRefreshable = it.isRefreshable
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        refreshLayout.isEnabled = isRefreshable
        refreshLayout.setOnRefreshListener {
            statisticsViewModel.load()
        }

        statisticsAdapter.setValueSelectedListener(this)

        elementsView?.apply {
            layoutManager = LinearLayoutManager(context, RecyclerView.VERTICAL, false)
            adapter = statisticsAdapter
        }

        statisticsViewModel.statisticsLiveData.observe(viewLifecycleOwner, statisticsModelObserver)
        statisticsViewModel.load()
    }

    val TAG = "DebugTag StatisticsFragment";

    override fun onValueSelected(entry: StatisticsEntry) {
        Log.d(TAG, "onValueSelected: entry = $entry")
    }

    private fun updateUi(uiState: StatisticsPageUiState) {
        when (uiState) {
            is StatisticsPageUiState.Loading -> setLoadingState()
            is StatisticsPageUiState.Success -> setModel(uiState.model)
            is StatisticsPageUiState.Error -> setError(uiState.e)
        }
    }

    private fun setLoadingState() {
        progress.visible()
        noDataMessage.gone()
        refreshLayout.isRefreshing = false
        statisticsAdapter.items = emptyList()
    }

    private fun setModel(model: StatisticsPageModel?) {
        progress.gone()
        model?.let {
            val items = model.items

            if (items.isEmpty()) {
                noDataMessage.visible()
            }

            statisticsAdapter.items = items
            statisticsAdapter.notifyDataSetChanged()
        }
    }

    private fun setError(e: Throwable) {
        progress.gone()
        Toast.makeText(context, "Error happened. ${e.message}", Toast.LENGTH_SHORT).show()
    }

    companion object Companion {
        fun newInstance(pageParams: StatisticsPageParams) =
            StatisticsFragment().apply {
                arguments = Bundle().apply { putFragmentExtra(pageParams) }
            }
    }
}