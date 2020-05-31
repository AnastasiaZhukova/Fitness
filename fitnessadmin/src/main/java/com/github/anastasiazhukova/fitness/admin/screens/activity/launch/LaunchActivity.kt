package com.github.anastasiazhukova.fitness.admin.screens.activity.launch

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.github.anastasiazhukova.fitness.admin.R
import com.github.anastasiazhukova.fitness.admin.screens.activity.authentication.AuthenticationActivity
import com.github.anastasiazhukova.fitness.admin.screens.activity.main.MainActivity
import com.github.anastasiazhukova.fitness.authentication.auth.IAuthenticationManager
import org.koin.android.ext.android.inject

class LaunchActivity : AppCompatActivity(R.layout.activity_launch) {

    private val authManager: IAuthenticationManager by inject()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        if (authManager.isLoggedIn()) {
            MainActivity.start(this)
        } else {
            AuthenticationActivity.start(this)
        }

        finish()
    }
}