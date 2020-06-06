package com.github.anastasiazhukova.fitness.admin.screens.fragment.clients.presentation

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.github.anastasiazhukova.fitness.admin.R
import com.github.anastasiazhukova.fitness.admin.screens.activity.clientDetails.presentation.ClientDetailsActivity
import com.github.anastasiazhukova.fitness.admin.screens.fragment.clients.domain.ClientModel
import com.github.anastasiazhukova.fitness.admin.screens.fragment.clients.domain.toClientDetailsParams
import com.github.anastasiazhukova.fitness.admin.screens.fragment.clients.viewmodel.ClientsViewModel
import com.github.anastasiazhukova.fitness.utils.extensions.gone
import com.github.anastasiazhukova.fitness.utils.extensions.visible
import kotlinx.android.synthetic.main.fragment_clients.*
import org.koin.androidx.scope.lifecycleScope
import org.koin.androidx.viewmodel.scope.viewModel

class ClientsFragment : Fragment(R.layout.fragment_clients), IClientClickListener {

    private val clientsViewModel: ClientsViewModel by lifecycleScope.viewModel(this)
    private val clientsAdapter = ClientAdapter()
    private val clientsStateObserver = Observer<ClientsUiState> {
        setClientsUiState(it)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        clientsAdapter.setClientClickListener(this)

        clientsList?.apply {
            adapter = clientsAdapter
            layoutManager = LinearLayoutManager(context, RecyclerView.VERTICAL, false)
        }

        clientsViewModel.clientsLiveData.observe(
            viewLifecycleOwner,
            clientsStateObserver
        )

        clientsViewModel.load()
    }

    override fun onClick(model: ClientModel) {
        context?.let { context ->
            ClientDetailsActivity.start(
                clientDetailsParams = model.toClientDetailsParams(),
                context = context
            )
        }
    }

    private fun setClientsUiState(uiState: ClientsUiState) {
        when (uiState) {
            is ClientsUiState.Loading -> setLoadingState()
            is ClientsUiState.Success -> setModel(uiState.model)
            is ClientsUiState.Error -> setError(uiState.e)
        }
    }

    private fun setLoadingState() {
        progress.visible()
        clientsAdapter.items = emptyList()
    }

    private fun setModel(model: List<ClientModel>) {
        progress.gone()
        clientsAdapter.items = model
    }

    private fun setError(e: Throwable) {
        progress.gone()
        Toast.makeText(context, "Error happened. ${e.message}", Toast.LENGTH_SHORT).show()
    }
}