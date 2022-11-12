package com.example.roomies

import android.os.Build
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView



val chores = mutableListOf<Chore>()
private lateinit var rvItems: RecyclerView
private lateinit var rvAdapter: ChoreAdapter


class ChoresFragment : Fragment() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_chores, container, false)
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        //  val view =  inflater.inflate(R.layout.fragment_amazon, container, false)
        val layoutManager = LinearLayoutManager(context)

        rvItems = view.findViewById(R.id.recyclerView2)
        rvItems.layoutManager = layoutManager
        rvItems.setHasFixedSize(true)
        rvAdapter = ChoreAdapter(chores)
        rvItems.adapter = rvAdapter

        var addButton = view.findViewById<Button>(R.id.button2)

        var editChore = view.findViewById<EditText>(R.id.editTask)
        var editAssigned = view.findViewById<EditText>(R.id.editMembers)

        addButton.setOnClickListener {

            val curSize = rvAdapter.itemCount
            var tempChore = editChore.text.toString()
            var tempAssigned = editAssigned.text.toString()

            chores.add(Chore(tempChore, tempAssigned))
            rvAdapter.notifyItemRangeInserted(curSize, 1)


        }

    }

    companion object {
        fun newInstance(): ChoresFragment {
            return ChoresFragment()
        }
    }
}