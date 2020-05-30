package com.github.anastasiazhukova.fitness.admin.screens.fragment.login.presentation

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import com.github.anastasiazhukova.fitness.admin.R
import com.github.anastasiazhukova.fitness.admin.screens.activity.main.MainActivity
import com.github.anastasiazhukova.fitness.admin.screens.fragment.login.viewmodel.LoginViewModel
import com.github.anastasiazhukova.fitness.utils.extensions.*
import kotlinx.android.synthetic.main.fragment_login.*
import org.koin.androidx.scope.lifecycleScope
import org.koin.androidx.viewmodel.scope.viewModel

class LoginFragment : Fragment(R.layout.fragment_login) {

    private val loginViewModel: LoginViewModel by lifecycleScope.viewModel(this)
    private val loginResultObserver = Observer<LoginUiState> {
        setUiState(it)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        loginViewModel.authenticationResultLiveData.observe(viewLifecycleOwner, loginResultObserver)
        initViews()
    }


    private fun initViews() {
        loginButton.setOnClickListener {
            if (validateFields()) {
                loginViewModel.login(email.getTextAsString(), password.getTextAsString())
            }
        }
    }

    private fun validateFields(): Boolean {
        if (email.text.isNullOrEmpty() || password.text.isNullOrEmpty()) {
            showError()

            return false
        }

        return true
    }

    private fun showError() {
        Toast.makeText(
            context,
            R.string.authentication_login_credentials_error,
            Toast.LENGTH_SHORT
        ).show()
    }

    private fun setUiState(loginUiState: LoginUiState) {
        when (loginUiState) {
            is LoginUiState.Loading -> setLoadingState()
            is LoginUiState.Success -> navigateNextScreen()
            is LoginUiState.Error -> setErrorState()
        }
    }

    private fun setLoadingState() {
        progress.visible()
        loginButton.disable()
    }

    private fun navigateNextScreen() {
        context?.let {
            MainActivity.start(it)
        }
    }

    private fun setErrorState() {
        progress.gone()
        loginButton.enable()
        showError()
    }
}