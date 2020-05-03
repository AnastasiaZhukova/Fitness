package com.github.anastasiazhukova.fitness.admin

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
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
                R.id.menu_clients -> null
                R.id.menu_exercises -> null
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
        bottomNavigation.selectedItemId = R.id.menu_clients
    }
}
