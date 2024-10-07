package com.developer.edra.project_sleep_tracker_6.sleeptracker

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.developer.edra.project_sleep_tracker_6.R
import com.developer.edra.project_sleep_tracker_6.TextItemViewHolder
import com.developer.edra.project_sleep_tracker_6.database.SleepNight

class SleepNightAdapter : RecyclerView.Adapter<TextItemViewHolder>() {

    var data = listOf<SleepNight>()
        set(value) {
            field = value
            notifyDataSetChanged()
        }

    override fun getItemCount() = data.size

    override fun onBindViewHolder(holder: TextItemViewHolder, position: Int) {
        val item = data[position]
        holder.textView.text = item.sleepQuality.toString()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TextItemViewHolder {

        val layoutInflater = LayoutInflater.from(parent.context)
        val view = layoutInflater
            .inflate(R.layout.text_item_view, parent, false) as TextView

        return TextItemViewHolder(view)
    }
}