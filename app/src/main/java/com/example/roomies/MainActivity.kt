package com.example.roomies

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import com.google.android.material.bottomnavigation.BottomNavigationView
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.example.roomies.databinding.ActivityMainBinding
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val fragmentManager: FragmentManager = supportFragmentManager
        //val fragment1: Fragment = HomeFragment() //TODO
        //val fragment2: Fragment = ChoresFragment() //TODO
        //val fragment3: Fragment = RentFragment //TODO
        val fragment4: Fragment = AmazonFragment()



        val bottomNavigationView: BottomNavigationView = findViewById(R.id.bottom_navigation)
        bottomNavigationView.setOnItemSelectedListener { item ->
            lateinit var fragment: Fragment
            when (item.itemId) {
                //R.id.homeButton -> fragment = fragment1
                //R.id.choresButton -> fragment = fragment2
                //R.id.rentButton -> fragment = fragment3
                 R.id.amazonButton -> fragment = fragment4
            }
            fragmentManager.beginTransaction().replace(R.id.rlContainer, fragment).commit()
            true
        }
        bottomNavigationView.selectedItemId = R.id.amazonButton


    }
}

