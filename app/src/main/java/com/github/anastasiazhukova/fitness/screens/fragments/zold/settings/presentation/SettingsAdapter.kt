package com.github.anastasiazhukova.fitness.screens.fragments.zold.settings.presentation

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.github.anastasiazhukova.fitness.R
import com.github.anastasiazhukova.fitness.screens.fragments.zold.settings.domain.SettingModel

class SettingsAdapter : RecyclerView.Adapter<SettingsAdapter.SettingsViewHolder>() {

    var items: List<SettingModel> = emptyList()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SettingsViewHolder {
        val view = LayoutInflater
            .from(parent.context)
            .inflate(R.layout.view_adapter_setting, parent, false)

        return SettingsViewHolder(view)
    }

    override fun getItemCount(): Int = items.count()

    override fun onBindViewHolder(holder: SettingsViewHolder, position: Int) {
        holder.bind(items[position])
    }


    inner class SettingsViewHolder(private val view: View) : RecyclerView.ViewHolder(view) {

        fun bind(model: SettingModel) {

        }

    }
}