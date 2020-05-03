package com.github.anastasiazhukova.fitness.screens.activities.authentication.presentation

import android.content.Context
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import com.github.anastasiazhukova.fitness.R
import com.github.anastasiazhukova.fitness.screens.activities.authentication.viewmodel.AuthenticationViewModel
import com.github.anastasiazhukova.fitness.screens.activities.main.MainActivity
import com.github.anastasiazhukova.fitness.utils.Result
import com.github.anastasiazhukova.fitness.utils.extensions.gone
import com.github.anastasiazhukova.fitness.utils.extensions.startActivity
import com.github.anastasiazhukova.fitness.utils.extensions.visible
import kotlinx.android.synthetic.main.activity_authentication.*
import org.koin.androidx.scope.lifecycleScope
import org.koin.androidx.viewmodel.scope.viewModel

class AuthenticationActivity : AppCompatActivity(R.layout.activity_authentication) {

    val TAG = "DebugTag AuthenticationActivity";

    private val authenticationViewModel: AuthenticationViewModel by lifecycleScope.viewModel(this)
    private val authenticationModelObserver = Observer<Result<Boolean>> {
        resolveResult(it)
    }

    private var isLoginMode = true

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        initAuthenticationButton()
        initChangeAuthenticationMethodButton()
    }

    override fun onResume() {
        super.onResume()

        authenticationViewModel.authenticationResultLiveData.observe(
            this,
            authenticationModelObserver
        )
    }

    private fun initAuthenticationButton() {
        authenticationButton?.setOnClickListener {
            if (isLoginMode) {
                tryLogin()
            } else {
                tryRegister()
            }
        }
    }

    private fun initChangeAuthenticationMethodButton() {
        changeAuthenticationMethodButton?.setOnClickListener {
            if (isLoginMode) {
                setRegisterMode()
            } else {
                setLoginMode()
            }
        }
    }

    private fun resolveResult(it: Result<Boolean>) {
        when (it) {
            is Result.Success -> navigateNextScreen()
            is Result.Error -> showError(it.exception)
        }
    }

    private fun tryLogin() {
        val email = email.text?.toString()
        val password = password.text?.toString()

        if (!email.isNullOrEmpty() && !password.isNullOrEmpty()) {
            authenticationViewModel.login(email, password)
        } else {
            showError("Invalid e-mail or password")
        }
    }

    private fun tryRegister() {
        val email = email.text?.toString()
        val password = password.text?.toString()

        if (!email.isNullOrEmpty() && !password.isNullOrEmpty()) {
            authenticationViewModel.register(email, password)
        } else {
            showError("Invalid e-mail or password")
        }
    }

    private fun setLoginMode() {
        isLoginMode = true
        authenticationButton.setText(R.string.authentication_login)
        changeAuthenticationMethodButton.setText(R.string.authentication_no_account)
        repeatPasswordLayout.gone()
    }

    private fun setRegisterMode() {
        isLoginMode = false
        authenticationButton.setText(R.string.authentication_register)
        changeAuthenticationMethodButton.setText(R.string.authentication_have_account)
        repeatPasswordLayout.visible()
    }

    private fun showError(exception: Throwable) {
        Log.e(TAG, "showError: exception = ${exception.message}", exception)
        showError(exception.message)
    }

    private fun showError(message: String?) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
    }

    private fun navigateNextScreen() {
        MainActivity.start(this)
    }

    companion object Companion {
        fun start(context: Context) {
            context.startActivity(AuthenticationActivity::class.java)
        }
    }
}