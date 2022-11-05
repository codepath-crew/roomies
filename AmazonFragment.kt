package com.example.roomies

import android.app.Activity
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.text.Html
import android.text.method.LinkMovementMethod
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.launch

class AmazonFragment : Fragment() {

    val items = mutableListOf<Item>()
    private lateinit var rvItems: RecyclerView
    private lateinit var rvAdapter: ItemAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment

        val view =  inflater.inflate(R.layout.fragment_amazon, container, false)
        /*
        val layoutManager = LinearLayoutManager(context)

        rvItems =  view.findViewById(R.id.recyclerView)
        rvItems.layoutManager = layoutManager
        rvItems.setHasFixedSize(true)
        rvAdapter = ItemAdapter( items)
        rvItems.adapter = rvAdapter
        */
        return view
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
      //  val view =  inflater.inflate(R.layout.fragment_amazon, container, false)
        val layoutManager = LinearLayoutManager(context)

        rvItems =  view.findViewById(R.id.recyclerView)
        rvItems.layoutManager = layoutManager
        rvItems.setHasFixedSize(true)
        rvAdapter = ItemAdapter( items)
        rvItems.adapter = rvAdapter

        var addButton = view.findViewById<Button>(R.id.button2)

        var nameEditText = view.findViewById<EditText>(R.id.editName)
        var priceEditText = view.findViewById<EditText>(R.id.editPrice)
        var urlEditText = view.findViewById<EditText>(R.id.editUrl)

        var linkText = view.findViewById<EditText>(R.id.priceView)
        addButton.setOnClickListener {
           // var nameEditText = view.findViewById<EditText>(R.id.editName)
           // var priceEditText = view.findViewById<EditText>(R.id.editPrice)
           // var urlEditText = view.findViewById<EditText>(R.id.editUrl)

            val curSize = rvAdapter.itemCount
            var tempName = nameEditText.text.toString()
            var tempPrice = priceEditText.text.toString()
            var tempLink = urlEditText.text.toString()

            items.add(Item(tempName, tempPrice, tempLink))
            //rvItems.adapter?.notifyDataSetChanged()
            rvAdapter.notifyItemRangeInserted(curSize, 1)
            /*
            val text = "Hello toast!"
            val duration = Toast.LENGTH_SHORT

            val toast = Toast.makeText(view.context, text, duration)
            toast.show()
            */

        }



    }


    companion object {
        fun newInstance(): AmazonFragment {
            return AmazonFragment()
        }

    }



}