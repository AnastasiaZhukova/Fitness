package com.github.anastasiazhukova.fitness.screens.activities.authentication

import android.content.Context
import android.os.Bundle
import android.os.PersistableBundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.github.anastasiazhukova.fitness.R
import com.github.anastasiazhukova.fitness.screens.fragments.login.presentation.LoginFragment
import com.github.anastasiazhukova.fitness.screens.fragments.register.presentation.RegisterFragment
import com.github.anastasiazhukova.fitness.utils.extensions.startActivity
import kotlinx.android.synthetic.main.activity_authentication.*

class AuthenticationActivity : AppCompatActivity(R.layout.activity_authentication) {

    private var isLoginMode = true

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        isLoginMode = savedInstanceState?.getBoolean(IS_LOGIN_PARAM, true) ?: true
        changeMode(isLoginMode)
        changeAuthenticationMethodButton?.setOnClickListener {
            changeMode(!isLoginMode)
        }
    }

    override fun onSaveInstanceState(outState: Bundle, outPersistentState: PersistableBundle) {
        super.onSaveInstanceState(outState, outPersistentState)

        outState.putBoolean(IS_LOGIN_PARAM, isLoginMode)
    }

    private fun changeMode(isLoginMode: Boolean) {
        if (isLoginMode) {
            setLoginMode()
        } else {
            setRegisterMode()
        }
    }

    private fun setLoginMode() {
        isLoginMode = true
        showFragment(LoginFragment())
        changeAuthenticationMethodButton.setText(R.string.authentication_no_account)
    }

    private fun setRegisterMode() {
        isLoginMode = false
        showFragment(RegisterFragment())
        changeAuthenticationMethodButton.setText(R.string.authentication_have_account)
    }


    private fun showFragment(fragment: Fragment) {
        supportFragmentManager
            .beginTransaction()
            .replace(R.id.authenticationContainer, fragment)
            .commit()
    }

    companion object Companion {
        private const val IS_LOGIN_PARAM = "IS_LOGIN_PARAM"

        fun start(context: Context) {
            context.startActivity(AuthenticationActivity::class.java)
        }
    }
}