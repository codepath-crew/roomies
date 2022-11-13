package com.example.roomies

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.firebase.database.*

class ProductListActivity : AppCompatActivity() {

    private lateinit var dbref : DatabaseReference
    private lateinit var productRecyclerview : RecyclerView
    private lateinit var productArrayList : ArrayList<Product>


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.fragment_amazon)

        productRecyclerview = findViewById(R.id.recyclerView)
        productRecyclerview.layoutManager = LinearLayoutManager(this)
        productRecyclerview.setHasFixedSize(true)

        productArrayList = arrayListOf<Product>()
        getProductData()

    }

    private fun getProductData() {

        dbref = FirebaseDatabase.getInstance().getReference("WishProducts")

        dbref.addValueEventListener(object : ValueEventListener{

            override fun onDataChange(snapshot: DataSnapshot) {

                if (snapshot.exists()){

                    for (productSnapshot in snapshot.children){


                        val product = productSnapshot.getValue(Product::class.java)
                        productArrayList.add(product!!)

                    }

                    productRecyclerview.adapter = ProductAdapter(productArrayList)


                }

            }

            override fun onCancelled(error: DatabaseError) {
                TODO("Not yet implemented")
            }


        })

    }
}