package com.github.watabee.recyclerviewselectionsample

import android.view.MotionEvent
import androidx.recyclerview.selection.ItemDetailsLookup
import androidx.recyclerview.widget.RecyclerView

class MyItemDetailsLookup(private val recyclerView: RecyclerView) : ItemDetailsLookup<Long>() {
    override fun getItemDetails(e: MotionEvent): ItemDetails<Long>? {
        val view = recyclerView.findChildViewUnder(e.x, e.y) ?: return null
        val viewHolder = recyclerView.getChildViewHolder(view) as? ItemViewHolder ?: return null
        return object : ItemDetails<Long>() {
            override fun getPosition(): Int = viewHolder.bindingAdapterPosition

            override fun getSelectionKey(): Long = viewHolder.itemId
        }
    }
}
