package com.github.anastasiazhukova.fitness.screens.activities.main.presentation

import android.content.Context
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import com.github.anastasiazhukova.fitness.R
import com.github.anastasiazhukova.fitness.domain.weight.WeightModel
import com.github.anastasiazhukova.fitness.screens.activities.authentication.AuthenticationActivity
import com.github.anastasiazhukova.fitness.screens.activities.main.viewmodel.MainViewModel
import com.github.anastasiazhukova.fitness.screens.fragments.calories.presentation.CaloriesFragment
import com.github.anastasiazhukova.fitness.screens.fragments.water.presentation.WaterFragment
import com.github.anastasiazhukova.fitness.screens.fragments.workoutPlan.presentation.WorkoutPlanFragment
import com.github.anastasiazhukova.fitness.utils.constants.Constants.String.EMPTY
import com.github.anastasiazhukova.fitness.utils.extensions.startActivity
import com.github.anastasiazhukova.statistics.domain.StatisticsPageParams
import com.github.anastasiazhukova.statistics.presentation.StatisticsFragment
import kotlinx.android.synthetic.main.activity_main.*
import org.koin.androidx.scope.lifecycleScope
import org.koin.androidx.viewmodel.scope.viewModel

class MainActivity : AppCompatActivity(R.layout.activity_main) {

    private val mainViewModel: MainViewModel by lifecycleScope.viewModel(this)
    private val weightModelObserver = Observer<WeightModel> {
        showWeightDialog()
    }
    private val addWeightDialogClickListener = object : IWeightDialogClickListener {
        override fun onAdded(amount: Float) {
            mainViewModel.addWeight(amount)
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        initBottomNavigation()
        selectDefaultNavigationItem()
        mainViewModel.weightLiveData.observe(this, weightModelObserver)

        logoutButton.setOnClickListener {
            mainViewModel.logout()
            AuthenticationActivity.start(this)
            finish()
        }
    }

    override fun onResume() {
        super.onResume()

        mainViewModel.getWeight()
    }

    private fun initBottomNavigation() {
        bottomNavigation.setOnNavigationItemSelectedListener {
            when (it.itemId) {
                R.id.menu_water -> WaterFragment()
                R.id.menu_calories -> CaloriesFragment()
                R.id.menu_dashboard -> WorkoutPlanFragment()
                R.id.menu_statistics -> {
                    val clientId = mainViewModel.getCurrentUserId() ?: EMPTY
                    StatisticsFragment.newInstance(StatisticsPageParams(clientId = clientId))
                }
                else -> null
            }?.let { fragment ->
                supportFragmentManager
                    .beginTransaction()
                    .replace(R.id.content, fragment)
                    .commit()
            }

            true
        }
    }

    private fun selectDefaultNavigationItem() {
        bottomNavigation.selectedItemId = R.id.menu_dashboard
    }

    private fun showWeightDialog() {
        AddWeightEntryDialog().apply {
            setListener(addWeightDialogClickListener)
            show(supportFragmentManager)
        }
    }

    companion object Companion {
        fun start(context: Context) {
            context.startActivity(MainActivity::class.java)
        }
    }
}