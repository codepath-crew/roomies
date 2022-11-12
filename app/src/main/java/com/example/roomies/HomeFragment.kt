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
import android.telephony.SmsManager
import android.telephony.SmsMessage


val people = mutableListOf<Person>()
private lateinit var rvItems: RecyclerView
private lateinit var rvAdapter: PersonAdapter


class HomeFragment : Fragment() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_home, container, false)
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        //  val view =  inflater.inflate(R.layout.fragment_amazon, container, false)
        val layoutManager = LinearLayoutManager(context)

        rvItems = view.findViewById(R.id.rvHome)
        rvItems.layoutManager = layoutManager
        rvItems.setHasFixedSize(true)
        rvAdapter = PersonAdapter(people)
        rvItems.adapter = rvAdapter
        var addButton = view.findViewById<Button>(R.id.addMemberButton)

        var msgButton = view.findViewById<Button>(R.id.notifButton)
        var nameEditText = view.findViewById<EditText>(R.id.exitTName)
        var phoneEditText = view.findViewById<EditText>(R.id.exitTPhone)

        addButton.setOnClickListener {

            val curSize = rvAdapter.itemCount
            var tempName = nameEditText.text.toString()
            var tempPhone = phoneEditText.text.toString()

            people.add(Person(tempName, tempPhone))
            rvAdapter.notifyItemRangeInserted(curSize, 1)

            val text = "Added New Member!"
            val duration = Toast.LENGTH_SHORT

            val toast = Toast.makeText(view.context, text, duration)
            toast.show()


        }
        msgButton.setOnClickListener {
            //go through list of phones, and send sms
            val smsManager  = SmsManager.getDefault()

            var msgEdit = view.findViewById<EditText>(R.id.textViewMsg)
            var phoneNum = ""
            smsManager.sendTextMessage(phoneNum,null, msgEdit.text.toString(), null, null)
            val text = "Sent!"
            val duration = Toast.LENGTH_SHORT

            val toast = Toast.makeText(view.context, text, duration)
            toast.show()
            //for(x in people){
            //    smsManager.sendTextMessage(x.phone,null, msgEdit.text.toString(), null, null)
            //}


        }
    }

    companion object {
        fun newInstance(): HomeFragment {
            return HomeFragment()
        }
    }
}