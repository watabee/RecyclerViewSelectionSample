package com.github.watabee.recyclerviewselectionsample

import android.os.Bundle
import android.util.Log
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.selection.SelectionTracker
import androidx.recyclerview.selection.StableIdKeyProvider
import androidx.recyclerview.selection.StorageStrategy
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.RecyclerView

class MainActivity : AppCompatActivity() {

    private var selectionTracker: SelectionTracker<Long>? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        findViewById<Button>(R.id.show_button).setOnClickListener {
            Log.e("MainActivity", "selection: ${selectionTracker?.selection?.toList()}")
        }

        findViewById<Button>(R.id.clear_button).setOnClickListener {
            selectionTracker?.clearSelection()
        }

        val items = (0 until 500).map { Item(id = it.toLong(), title = "title $it") }
        val adapter = ItemsAdapter {
            selectionTracker?.isSelected(it) ?: false
        }
        adapter.submitList(items)

        val recyclerView: RecyclerView = findViewById(R.id.recycler_view)
        recyclerView.setHasFixedSize(true)
        recyclerView.addItemDecoration(DividerItemDecoration(this, RecyclerView.VERTICAL))
        recyclerView.adapter = adapter

        selectionTracker = SelectionTracker
            .Builder(
                "ItemsAdapterSelectionTracker",
                recyclerView,
                StableIdKeyProvider(recyclerView),
                MyItemDetailsLookup(recyclerView),
                StorageStrategy.createLongStorage()
            )
            .build()

        if (savedInstanceState != null) {
            selectionTracker?.onRestoreInstanceState(savedInstanceState)
        }
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        selectionTracker?.onSaveInstanceState(outState)
    }
}
