package com.github.anastasiazhukova.fitness.screens.fragments.register.presentation

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import com.github.anastasiazhukova.fitness.R
import com.github.anastasiazhukova.fitness.screens.activities.main.MainActivity
import com.github.anastasiazhukova.fitness.screens.fragments.register.viewmodel.RegisterViewModel
import com.github.anastasiazhukova.fitness.utils.constants.Constants.Authentication.MIN_PASSWORD_LENGTH
import com.github.anastasiazhukova.fitness.utils.constants.Constants.FitnessLevel.HARD
import com.github.anastasiazhukova.fitness.utils.constants.Constants.FitnessLevel.LIGHT
import com.github.anastasiazhukova.fitness.utils.constants.Constants.FitnessLevel.MEDIUM
import com.github.anastasiazhukova.fitness.utils.extensions.*
import kotlinx.android.synthetic.main.fragment_register.*
import kotlinx.android.synthetic.main.view_fitness_level_picker.*
import kotlinx.android.synthetic.main.view_gender_picker.*
import org.koin.androidx.scope.lifecycleScope
import org.koin.androidx.viewmodel.scope.viewModel

class RegisterFragment : Fragment(R.layout.fragment_register) {

    private val registerViewModel: RegisterViewModel by lifecycleScope.viewModel(this)
    private val registerResultObserver = Observer<RegisterUiState> {
        setUiState(it)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        registerViewModel.authenticationResultLiveData.observe(
            viewLifecycleOwner,
            registerResultObserver
        )
        initViews()
    }


    private fun initViews() {
        registerButton.setOnClickListener {
            if (validateFields()) {
                registerViewModel.register(
                    email.getTextAsString(),
                    password.getTextAsString(),
                    buildUserInfoParams()
                )
            }
        }
    }

    private fun validateFields(): Boolean {
        var isValid = false

        when {
            email.isNullOrEmpty() -> {
                showError(R.string.authentication_register_email_error)
            }
            password.isNullOrEmpty() || password.charactersCount() < MIN_PASSWORD_LENGTH -> {
                showError(R.string.authentication_register_password_error)
            }
            repeatPassword.text?.toString() != password.text?.toString() -> {
                showError(R.string.authentication_register_password_repeat_error)
            }
            name.isNullOrEmpty() -> {
                showError(R.string.authentication_register_name_error)
            }
            trainerNickname.isNullOrEmpty() -> {
                showError(R.string.authentication_trainer_nickname)
            }
            genderGroup.checkedChipId == View.NO_ID -> {
                showError(R.string.authentication_register_gender_error)
            }
            weight.isNullOrEmpty() || weight.asInt() <= 0 -> {
                showError(R.string.authentication_register_weight_error)
            }
            height.isNullOrEmpty() || height.asInt() <= 0 -> {
                showError(R.string.authentication_register_height_error)
            }
            fitnessLevelGroup.checkedChipId == View.NO_ID -> {
                showError(R.string.authentication_register_fitness_level_error)
            }
            goalCalories.isNullOrEmpty() || goalCalories.asInt() <= 0 -> {
                showError(R.string.authentication_register_goal_calories_error)
            }
            goalWater.isNullOrEmpty() || goalWater.asInt() <= 0 -> {
                showError(R.string.authentication_register_goal_water_error)
            }
            else -> {
                isValid = true
            }
        }

        return isValid
    }

    private fun showError(error: Int) {
        Toast.makeText(
            context,
            error,
            Toast.LENGTH_SHORT
        ).show()
    }

    private fun setUiState(registerUiState: RegisterUiState) {
        when (registerUiState) {
            is RegisterUiState.Loading -> setLoadingState()
            is RegisterUiState.Success -> navigateNextScreen()
            is RegisterUiState.Error -> setErrorState()
        }
    }

    private fun setLoadingState() {
        progress.visible()
        registerButton.disable()
    }

    private fun navigateNextScreen() {
        context?.let {
            MainActivity.start(it)
        }
    }

    private fun setErrorState() {
        registerButton.enable()
        progress.gone()
        showError(R.string.authentication_register_general_error)
    }

    private fun buildUserInfoParams(): UserInfoParams =
        UserInfoParams(
            name = name.getTextAsString(),
            trainerNickname = trainerNickname.getTextAsString(),
            gender = getGenderValue(),
            height = height.asInt(),
            weight = weight.asInt(),
            fitnessLevel = getFitnessLevelValue(),
            goalCalories = goalCalories.asInt(),
            goalWater = goalWater.asInt()
        )


    private fun getGenderValue(): Boolean =
        when (genderGroup.checkedChipId) {
            R.id.genderMale -> false
            R.id.genderFemale -> true
            else -> false
        }

    private fun getFitnessLevelValue(): Int =
        when (fitnessLevelGroup.checkedChipId) {
            R.id.fitnessLevelLight -> LIGHT
            R.id.fitnessLevelMedium -> MEDIUM
            R.id.fitnessLevelHard -> HARD
            else -> MEDIUM
        }

}