package com.github.anastasiazhukova.fitness.admin.screens.activity.clientDetails.presentation

import android.content.Context
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.github.anastasiazhukova.fitness.admin.R
import com.github.anastasiazhukova.fitness.admin.screens.activity.workoutPlan.presentation.WorkoutPlanActivity
import com.github.anastasiazhukova.fitness.admin.screens.common.clientDetails.ClientDetailsParams
import com.github.anastasiazhukova.fitness.chat.domain.ChatParams
import com.github.anastasiazhukova.fitness.chat.presentation.ChatActivity
import com.github.anastasiazhukova.fitness.chat.utils.getChatId
import com.github.anastasiazhukova.fitness.utils.extensions.getActivityExtra
import com.github.anastasiazhukova.fitness.utils.extensions.startActivity
import kotlinx.android.synthetic.main.activity_client_details.*

class ClientDetailsActivity : AppCompatActivity(R.layout.activity_client_details) {

    private var clientDetailsParams: ClientDetailsParams? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        intent.getActivityExtra<ClientDetailsParams>()?.let { clientDetailsParams ->
            this.clientDetailsParams = clientDetailsParams
            initViews(clientDetailsParams)
        }
    }

    private fun initViews(clientDetailsParams: ClientDetailsParams) {
        clientName.text = clientDetailsParams.name
        initClientGoals(clientDetailsParams)

        chatButton.setOnClickListener {
            startChatActivity()
        }

        manageWorkoutPlanButton.setOnClickListener {
            startWorkoutPlanActivity()
        }
    }

    private fun initClientGoals(clientDetailsParams: ClientDetailsParams) {
        val noDataString = resources.getString(R.string.client_details_no_data)

        val gender = if (clientDetailsParams.gender) {
            resources.getString(R.string.female)
        } else {
            resources.getString(R.string.male)
        }

        val height = if (clientDetailsParams.height != null && clientDetailsParams.height > 0) {
            "${clientDetailsParams.height} ${resources.getString(R.string.cm)}"
        } else {
            noDataString
        }

        val weight = if (clientDetailsParams.weight != null && clientDetailsParams.weight > 0) {
            "${clientDetailsParams.weight} ${resources.getString(R.string.kg)}"
        } else {
            noDataString
        }

        genderText.text = String.format(
            resources.getString(R.string.client_details_gender),
            gender
        )

        heightText.text = String.format(
            resources.getString(R.string.client_details_height),
            height
        )

        weightText.text = String.format(
            resources.getString(R.string.client_details_weight),
            weight
        )
    }

    private fun startChatActivity() {
        val trainerId = clientDetailsParams?.trainerNickname
        val clientId = clientDetailsParams?.id
        val clientName = clientDetailsParams?.name

        if (!trainerId.isNullOrEmpty() && !clientId.isNullOrEmpty() && !clientName.isNullOrEmpty()) {
            val chatId = getChatId(
                trainerId = trainerId,
                userId = clientId
            )

            ChatActivity.start(
                ChatParams(
                    chatId = chatId,
                    chatOpponent = clientName
                ), this
            )
        }
    }

    private fun startWorkoutPlanActivity() {
        clientDetailsParams?.let {
            WorkoutPlanActivity.start(it, this)
        }
    }

    companion object Companion {
        fun start(clientDetailsParams: ClientDetailsParams, context: Context) {
            context.startActivity(ClientDetailsActivity::class.java, clientDetailsParams)
        }
    }
}