package com.example.roomies

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.roomies.R

private const val TAG = "RentFragment"

class RentFragment() : Fragment() {
    // properties
    private val roommates = mutableListOf<Roommate>()
    private var totalRent: String = ""
    private lateinit var rentRecyclerView: RecyclerView
    private lateinit var rentAdapter: RentAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_rent, container, false)

        val layoutManager = LinearLayoutManager(context)
        rentRecyclerView = view.findViewById(R.id.rent_recycler_view)
        rentRecyclerView.layoutManager = layoutManager
        rentAdapter = RentAdapter(view.context, roommates)
        rentRecyclerView.adapter = rentAdapter


        // changing total rent
        val totalRentView = view.findViewById<EditText>(R.id.totalRentEditText)
        val updateTotalRentBtn = view.findViewById<Button>(R.id.updateTotalRentBtn)

        updateTotalRentBtn.setOnClickListener {
            // Get a string containing the total rent
            totalRent = totalRentView.text.toString()

            // If the value is invalid, set totalRent to 0
            if (totalRent.isNullOrEmpty() || !totalRent.all{Character.isDigit(it)} || totalRent.toInt() < 0) {
                totalRentView.setText("0")
                totalRent = totalRentView.text.toString()
                val toast = Toast.makeText(view.context, "Must be a Positive Int", Toast.LENGTH_SHORT)
                toast.show()
            }

            if (roommates.size == 0) {
                // if the value of totalRent is valid and there's no roommates yet, add the Extra
                val extra = Roommate("Extra", 0, totalRent.toInt())
                roommates.add(extra)
            } else if (roommates.size == 1) {
                // if there's only one roommate, it means it's the Extra
                roommates[0].rent = totalRent.toInt()
            } else {
                // otherwise,
                var rentAccountedFor = 0
                for (r in roommates) {
                    if (r.name != "Extra") {
                        rentAccountedFor += r.rent
                    }
                }
                val excess = totalRent.toInt() - rentAccountedFor
                if (excess <= 0) {
                    roommates[0].rent = 0
                }
                else {
                    roommates[0].rent = excess
                }
            }
            rentAdapter.notifyDataSetChanged()
        }



        // Add a Roommate to pay rent
        val addRoommateBtn = view.findViewById<Button>(R.id.addBtn)
        addRoommateBtn.setOnClickListener{
            if (totalRent == "") {
                // TODO: make toast saying to enter total rent
            } else {
                var dialog = CustomDialog(rentAdapter, roommates)
                dialog.show(childFragmentManager, "customDialog")
            }
        }


        // The next Month has arrived, so reset all payments
        val nextMonthBtn = view.findViewById<Button>(R.id.nextMonth)
        nextMonthBtn.setOnClickListener{
            for (r in roommates) {
                if (r.rent_paid > r.rent) {
                    r.rent_paid -= r.rent_paid
                }
                else {
                    r.rent_paid = 0
                }
            }
            Log.v("whyamihere", "here2")
            rentAdapter.notifyDataSetChanged()

        }

        return view
    }

    companion object {
        fun newInstance(): RentFragment {
            return RentFragment()
        }
    }

}