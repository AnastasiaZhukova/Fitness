package com.github.anastasiazhukova.fitness.admin.screens.fragment.clients.presentation

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.github.anastasiazhukova.fitness.admin.R
import com.github.anastasiazhukova.fitness.admin.screens.fragment.clients.domain.ClientModel
import com.google.android.material.textview.MaterialTextView

interface IClientClickListener {
    fun onClick(model: ClientModel)
}

class ClientAdapter : RecyclerView.Adapter<ClientAdapter.ClientViewHolder>() {

    private var clientClickListener: IClientClickListener? = null

    var items: List<ClientModel> = emptyList()
        set(value) {
            field = value
            notifyDataSetChanged()
        }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ClientViewHolder {
        val view = LayoutInflater
            .from(parent.context)
            .inflate(R.layout.view_adapter_client, parent, false)

        return ClientViewHolder(view)
    }

    override fun getItemCount(): Int = items.count()

    override fun onBindViewHolder(holder: ClientViewHolder, position: Int) {
        holder.bind(items[position])
    }

    fun setClientClickListener(listener: IClientClickListener) {
        clientClickListener = listener
    }

    inner class ClientViewHolder(private val view: View) : RecyclerView.ViewHolder(view) {

        fun bind(model: ClientModel) {
            val text = view.findViewById<MaterialTextView>(R.id.clientName)
            text.text = model.name
            view.setOnClickListener {
                clientClickListener?.onClick(model)
            }
        }
    }
}

