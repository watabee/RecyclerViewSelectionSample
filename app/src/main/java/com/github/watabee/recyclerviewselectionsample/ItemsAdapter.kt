package com.github.watabee.recyclerviewselectionsample

import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter

class ItemsAdapter(private val isSelected: (Long) -> Boolean) : ListAdapter<Item, ItemViewHolder>(createDiffCallback()) {

    init {
        setHasStableIds(true)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemViewHolder {
        return ItemViewHolder(parent)
    }

    override fun onBindViewHolder(holder: ItemViewHolder, position: Int) {
        val item = getItem(position)
        holder.bind(isActivated = isSelected(item.id), item = item)
    }

    override fun getItemId(position: Int): Long {
        return getItem(position).id
    }

    companion object {
        fun createDiffCallback() = object : DiffUtil.ItemCallback<Item>() {
            override fun areItemsTheSame(oldItem: Item, newItem: Item): Boolean = oldItem.id == newItem.id

            override fun areContentsTheSame(oldItem: Item, newItem: Item): Boolean = oldItem == newItem
        }
    }
}
