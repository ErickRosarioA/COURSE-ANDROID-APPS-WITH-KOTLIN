package com.developer.edra.project_sleep_tracker_6.sleeptracker

import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.developer.edra.project_sleep_tracker_6.TextItemViewHolder
import com.developer.edra.project_sleep_tracker_6.database.SleepNight

class SleepNightAdapter: RecyclerView.Adapter<TextItemViewHolder>() {
    var data =  listOf<SleepNight>()

    override fun getItemCount() = data.size

    override fun onBindViewHolder(holder: TextItemViewHolder, position: Int) {
        val item = data[position]
        holder.textView.text = item.sleepQuality.toString()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TextItemViewHolder {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }
}