package com.github.anastasiazhukova.fitness.screens.fragments.statistics.presentation

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.github.anastasiazhukova.fitness.R
import com.github.anastasiazhukova.fitness.screens.fragments.statistics.domain.StatisticsPageModel
import com.github.anastasiazhukova.fitness.screens.fragments.statistics.viewmodel.StatisticsViewModel
import kotlinx.android.synthetic.main.fragment_statistics.*
import org.koin.androidx.scope.lifecycleScope
import org.koin.androidx.viewmodel.scope.viewModel


class StatisticsFragment : Fragment(R.layout.fragment_statistics) {

    private val statisticsViewModel: StatisticsViewModel by lifecycleScope.viewModel(this)
    private val statisticsModelObserver = Observer<StatisticsPageModel?> {
        setStatisticsModel(it)
    }

    private val statisticsAdapter = StatisticsAdapter()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        elementsView?.apply {
            layoutManager = LinearLayoutManager(context, RecyclerView.VERTICAL, false)
            adapter = statisticsAdapter
        }

        statisticsViewModel.statisticsLiveData.observe(this, statisticsModelObserver)
        statisticsViewModel.load()
    }

    private fun setStatisticsModel(model: StatisticsPageModel?) {
        model?.let {
            statisticsAdapter.items = model.items
            statisticsAdapter.notifyDataSetChanged()
        }
    }
}