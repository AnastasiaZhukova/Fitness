package com.github.anastasiazhukova.fitness.screens.activities.main

import android.content.Context
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.github.anastasiazhukova.fitness.R
import com.github.anastasiazhukova.fitness.authentication.user.IUserIdHolder
import com.github.anastasiazhukova.fitness.screens.fragments.calories.presentation.CaloriesFragment
import com.github.anastasiazhukova.fitness.screens.fragments.water.presentation.WaterFragment
import com.github.anastasiazhukova.fitness.screens.fragments.workoutPlan.presentation.WorkoutPlanFragment
import com.github.anastasiazhukova.fitness.utils.constants.Constants.String.EMPTY
import com.github.anastasiazhukova.fitness.utils.extensions.startActivity
import com.github.anastasiazhukova.statistics.domain.StatisticsPageParams
import com.github.anastasiazhukova.statistics.presentation.StatisticsFragment
import kotlinx.android.synthetic.main.activity_main.*
import org.koin.android.ext.android.inject

class MainActivity : AppCompatActivity(R.layout.activity_main) {

    private val userIdHolder: IUserIdHolder by inject()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        initBottomNavigation()
        selectDefaultNavigationItem()
    }

    private fun initBottomNavigation() {
        bottomNavigation.setOnNavigationItemSelectedListener {
            when (it.itemId) {
                R.id.menu_water -> WaterFragment()
                R.id.menu_calories -> CaloriesFragment()
                R.id.menu_dashboard -> WorkoutPlanFragment()
                R.id.menu_statistics -> {
                    val clientId = userIdHolder.getCurrentUserId() ?: EMPTY
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

    companion object Companion {
        fun start(context: Context) {
            context.startActivity(MainActivity::class.java)
        }
    }
}