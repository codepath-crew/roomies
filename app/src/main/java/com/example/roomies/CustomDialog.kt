package com.example.roomies

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import androidx.fragment.app.DialogFragment
import com.example.roomies.R


class CustomDialog(private val rentAdapter: RentAdapter, var roommates: MutableList<Roommate>) : DialogFragment() {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.custom_dialog, container, false)

        // get views
        val name = view.findViewById<EditText>(R.id.add_newName)
        val rentPaid = view.findViewById<EditText>(R.id.add_rent_paid)
        val rent = view.findViewById<EditText>(R.id.add_rent)
        val cancelBtn = view.findViewById<Button>(R.id.cancelButton)
        val addBtn = view.findViewById<Button>(R.id.addButton)


        cancelBtn.setOnClickListener {
            dismiss()
        }

        addBtn.setOnClickListener {
            // Log.v("whyamihere", "adding someone?")

            // If all of the input fields are filled in, add the new roommate
            if (name.text.toString() != "" && rentPaid.text.toString() != "" && rent.text.toString() != "") {
                roommates.add(Roommate(name.text.toString(), rentPaid.text.toString().toInt(), rent.text.toString().toInt()))
                for (r in roommates) {
                    if (r.name == "Extra") {
                        val difference = r.rent - rent.text.toString().toInt()
                        if (difference <= 0) {
                            r.rent = 0
                        } else {
                            r.rent = difference
                        }
                    }
                }
                rentAdapter.notifyDataSetChanged()
                dismiss()
            } else {
                //Log.v("whyamihere", "empty parameter")
                // TODO: toast to say empty spot?
            }

        }

        return view
    }
}