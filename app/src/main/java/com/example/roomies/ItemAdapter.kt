package com.example.roomies


import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class ItemAdapter (private val items: List<Item>) : RecyclerView.Adapter<ItemAdapter.ViewHolder>()
{
    // Provide a direct reference to each of the views within a data item
    // Used to cache the views within the item layout for fast access
    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        // Your holder should contain and initialize a member variable
        // for any view that will be set as you render a row
        val nameTextView = itemView.findViewById<TextView>(R.id.nameView)
        val priceTextView = itemView.findViewById<TextView>(R.id.priceView)
        val linkTextView = itemView.findViewById<TextView>(R.id.urlView)

        val k1 = itemView.findViewById<EditText>(R.id.editName)
        val k2 = itemView.findViewById<EditText>(R.id.editPrice)
        val k3 = itemView.findViewById<EditText>(R.id.editUrl)


    }

    // ... constructor and member variables
    // Usually involves inflating a layout from XML and returning the holder
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemAdapter.ViewHolder {
        val context = parent.context
        val inflater = LayoutInflater.from(context)
        // Inflate the custom layout
        val contactView = inflater.inflate(R.layout.item_look, parent, false)
        // Return a new holder instance
        return ViewHolder(contactView)
    }

    // Involves populating data into the item through holder
    override fun onBindViewHolder(viewHolder: ItemAdapter.ViewHolder, position: Int) {
        // Get the data model based on position
        val item: Item = items.get(position)

        // Set item views based on your views and data model
        //val nameTextView = viewHolder.nameTextView
        //nameTextView.setText(item.name)

        viewHolder.nameTextView.text = item.name
        viewHolder.priceTextView.text = item.price
        viewHolder.linkTextView.text = item.url


    }

    // Returns the total count of items in the list
    override fun getItemCount(): Int {
        return items.size
    }
}