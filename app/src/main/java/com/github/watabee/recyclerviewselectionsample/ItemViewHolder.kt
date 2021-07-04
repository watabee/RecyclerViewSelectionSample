package com.github.watabee.recyclerviewselectionsample

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class ItemViewHolder(parent: ViewGroup) : RecyclerView.ViewHolder(inflate(parent)) {

    private val idTextView: TextView = itemView.findViewById(R.id.id_text)
    private val titleTextView: TextView = itemView.findViewById(R.id.title_text)

    fun bind(isActivated: Boolean, item: Item) {
        itemView.isActivated = isActivated
        idTextView.text = item.id.toString()
        titleTextView.text = item.title
    }

    companion object {
        fun inflate(parent: ViewGroup): View {
            return LayoutInflater.from(parent.context).inflate(R.layout.list_item, parent, false)
        }
    }
}
