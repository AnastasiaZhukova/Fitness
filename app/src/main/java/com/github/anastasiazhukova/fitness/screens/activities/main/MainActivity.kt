package com.github.anastasiazhukova.fitness.screens.activities.main

import android.content.Context
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.github.anastasiazhukova.fitness.R
import com.github.anastasiazhukova.fitness.screens.fragments.calories.presentation.CaloriesFragment
import com.github.anastasiazhukova.fitness.screens.fragments.statistics.presentation.StatisticsFragment
import com.github.anastasiazhukova.fitness.screens.fragments.water.presentation.WaterFragment
import com.github.anastasiazhukova.fitness.screens.fragments.workoutPlan.presentation.WorkoutPlanFragment
import com.github.anastasiazhukova.fitness.utils.extensions.startActivity
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity(R.layout.activity_main) {

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
                R.id.menu_statistics -> StatisticsFragment()
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