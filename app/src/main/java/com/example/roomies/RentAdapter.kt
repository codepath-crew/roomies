package com.example.roomies

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ProgressBar
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.roomies.R

class RentAdapter(private val context: Context, private val roommates: List<Roommate>) :
    RecyclerView.Adapter<RentAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.item_rent, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val roommate = roommates[position]
        holder.bind(roommate)
    }

    override fun getItemCount() = roommates.size

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView),
        View.OnClickListener {

        // name & progress
        private val name = itemView.findViewById<TextView>(R.id.name)
        private val progressBar = itemView.findViewById<ProgressBar>(R.id.progress_bar)
        private val progressText = itemView.findViewById<TextView>(R.id.progress_text)

        init {
            itemView.setOnClickListener(this)
        }

        // Write a helper method to help set up the onBindViewHolder method
        fun bind(roommate: Roommate) {
            name.text = roommate.name
            progressText.text = "$"+roommate.rent_paid.toString() + " out of " + "$"+roommate.rent.toString()
            if (roommate.rent == 0) {
                progressBar.progress = 100
            } else{
                progressBar.progress = roommate.rent_paid / roommate.rent * 100
            }
        }
        override fun onClick(v: View?) {

        }
    }
}