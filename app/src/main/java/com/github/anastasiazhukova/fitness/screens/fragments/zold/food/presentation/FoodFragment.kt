package com.github.anastasiazhukova.fitness.screens.fragments.zold.food.presentation

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import com.github.anastasiazhukova.fitness.R
import com.github.anastasiazhukova.fitness.screens.fragments.zold.food.domain.TodayStatisticsModel
import com.github.anastasiazhukova.fitness.screens.fragments.zold.food.viewmodel.FoodViewModel
import kotlinx.android.synthetic.main.fragment_food.*
import org.koin.androidx.scope.lifecycleScope
import org.koin.androidx.viewmodel.scope.viewModel

class FoodFragment : Fragment(R.layout.fragment_food) {

    private val foodViewModel: FoodViewModel by lifecycleScope.viewModel(this)
    private val todayStatisticsModelObserver = Observer<TodayStatisticsModel?> {
        setTodayStatisticsModel(it)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        foodViewModel.todayStatisticsLiveData.observe(this, todayStatisticsModelObserver)
        foodViewModel.loadTodayStatistics()
    }

    private fun setTodayStatisticsModel(todayStatisticsModel: TodayStatisticsModel?) {
        todayStatisticsModel?.let { model ->
            model.caloriesModel?.let { myFoodCard.setCalories(it.value) }
            model.waterModel?.let { myWaterCard.setWater(it.value) }
        }
    }
}