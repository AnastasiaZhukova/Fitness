package com.github.anastasiazhukova.fitness.chat.presentation

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.github.anastasiazhukova.fitness.chat.R
import com.github.anastasiazhukova.fitness.chat.domain.ChatMessageModel
import com.github.anastasiazhukova.fitness.utils.extensions.gone
import com.github.anastasiazhukova.fitness.utils.extensions.toReadableTimeAndDate
import com.google.android.material.textview.MaterialTextView

class ChatMessageAdapter(
    private val currentUserId: String
) : RecyclerView.Adapter<ChatMessageAdapter.MessageViewHolder>() {

    var items: List<ChatMessageModel> = emptyList()
        set(value) {
            field = value
            notifyDataSetChanged()
        }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MessageViewHolder {
        val holderLayoutId = if (viewType == VIEW_TYPE_OUTCOMING) {
            R.layout.view_adapter_outcoming_message
        } else {
            R.layout.view_adapter_incoming_message
        }

        val view = LayoutInflater
            .from(parent.context)
            .inflate(holderLayoutId, parent, false)

        return MessageViewHolder(view)
    }

    override fun getItemCount(): Int = items.count()

    override fun onBindViewHolder(holder: MessageViewHolder, position: Int) {
        holder.bind(items[position])
    }

    override fun getItemViewType(position: Int): Int {
        return if (position < items.size && items[position].messageSender == currentUserId) {
            VIEW_TYPE_OUTCOMING
        } else {
            VIEW_TYPE_INCOMING
        }
    }

    inner class MessageViewHolder(private val view: View) : RecyclerView.ViewHolder(view) {

        fun bind(model: ChatMessageModel) {
            val message = view.findViewById<MaterialTextView>(R.id.message)
            val textLabel = view.findViewById<MaterialTextView>(R.id.textLabel)

            message.text = model.messageText
            textLabel.text = model.messageTime.toReadableTimeAndDate()
        }
    }

    companion object {
        private const val VIEW_TYPE_INCOMING = 1
        private const val VIEW_TYPE_OUTCOMING = 2
    }
}

