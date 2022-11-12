package com.example.roomies


import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class PersonAdapter (private val people: List<Person>) : RecyclerView.Adapter<PersonAdapter.ViewHolder>()
{
    // Provide a direct reference to each of the views within a data item
    // Used to cache the views within the item layout for fast access
    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        // Your holder should contain and initialize a member variable
        // for any view that will be set as you render a row
        val nameTextView = itemView.findViewById<TextView>(R.id.itemName1)
        val phoneTextView = itemView.findViewById<TextView>(R.id.itemPhone1)

        val k1 = itemView.findViewById<EditText>(R.id.exitTName)
        val k2 = itemView.findViewById<EditText>(R.id.exitTPhone)


    }

    // ... constructor and member variables
    // Usually involves inflating a layout from XML and returning the holder
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PersonAdapter.ViewHolder {
        val context = parent.context
        val inflater = LayoutInflater.from(context)
        // Inflate the custom layout
        val contactView = inflater.inflate(R.layout.item_home_look, parent, false)
        // Return a new holder instance
        return ViewHolder(contactView)
    }

    // Involves populating data into the item through holder
    override fun onBindViewHolder(viewHolder: PersonAdapter.ViewHolder, position: Int) {
        // Get the data model based on position
        val person: Person = people.get(position)


        viewHolder.nameTextView.text = person.name
        viewHolder.phoneTextView.text = person.phone



    }

    // Returns the total count of items in the list
    override fun getItemCount(): Int {
        return people.size
    }
}