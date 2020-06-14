package com.github.anastasiazhukova.fitness.admin.screens.activity.main

import android.content.Context
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.github.anastasiazhukova.fitness.admin.R
import com.github.anastasiazhukova.fitness.admin.screens.activity.authentication.AuthenticationActivity
import com.github.anastasiazhukova.fitness.admin.screens.fragment.clients.presentation.ClientsFragment
import com.github.anastasiazhukova.fitness.admin.screens.fragment.exercise.presentation.ExerciseFragment
import com.github.anastasiazhukova.fitness.authentication.auth.IAuthenticationManager
import com.github.anastasiazhukova.fitness.utils.extensions.startActivity
import kotlinx.android.synthetic.main.activity_main.*
import org.koin.android.ext.android.inject

class MainActivity : AppCompatActivity(R.layout.activity_main) {

    private val authenticationManager: IAuthenticationManager by inject()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        initBottomNavigation()
        selectDefaultNavigationItem()

        logoutButton.setOnClickListener {
            authenticationManager.logout()
            AuthenticationActivity.start(this)
            finish()
        }
    }

    private fun initBottomNavigation() {
        bottomNavigation.setOnNavigationItemSelectedListener {
            when (it.itemId) {
                R.id.menu_clients -> ClientsFragment()
                R.id.menu_exercises -> ExerciseFragment()
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
        bottomNavigation.selectedItemId =
            R.id.menu_clients
    }

    companion object Companion {
        fun start(context: Context) {
            context.startActivity(MainActivity::class.java)
        }
    }
}
