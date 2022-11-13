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
import androidx.appcompat.app.AppCompatActivity
import com.example.roomies.databinding.ActivityMainBinding
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.*

class AmazonFragment : Fragment() {

    private lateinit var dbref : DatabaseReference
    private lateinit var dbRef: DatabaseReference
    private val items = mutableListOf<Item>()
    private lateinit var rvItems: RecyclerView
    private lateinit var rvAdapter: ItemAdapter
    private lateinit var proAdapter: ItemAdapter
    private var counter = 0;

    private lateinit var productArrayList : ArrayList<Product>


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
        productArrayList = arrayListOf<Product>()


        var addButton = view.findViewById<Button>(R.id.button2)

        var nameEditText = view.findViewById<EditText>(R.id.editName)
        var priceEditText = view.findViewById<EditText>(R.id.editPrice)
        var urlEditText = view.findViewById<EditText>(R.id.editUrl)


        dbRef = FirebaseDatabase.getInstance().getReference("WishProducts")
//        dbref = FirebaseDatabase.getInstance().getReference("WishProducts")

        dbRef.addValueEventListener(object : ValueEventListener{

            override fun onDataChange(snapshot: DataSnapshot) {

                if (snapshot.exists() && counter == 0){

                    for (productSnapshot in snapshot.children){


                        val product = productSnapshot.getValue(Product::class.java)
                        if (product != null) {
                            items.add(Item(product.proName.toString(), product.proPrice.toString(), product.proUrl.toString()))

                            Log.d("TAG", product.proUrl.toString() + "message")
                            Log.d("TAGger", product.proPrice.toString() )

                        }

                    }
                    rvAdapter = ItemAdapter( items)
                    rvItems.adapter = rvAdapter
                    counter = 1
//                    val curSize1 = proAdapter.itemCount
//                    //rvItems.adapter?.notifyDataSetChanged()
//                    proAdapter.notifyItemRangeInserted(curSize1, 1)

//                    rvItems.adapter = ItemAdapter(productArrayList)

                }

            }

            override fun onCancelled(error: DatabaseError) {
                TODO("Not yet implemented")
            }


        })


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

            val proName = nameEditText.text.toString()
            val proPrice = priceEditText.text.toString()
            val proUrl = urlEditText.text.toString()

            if (proName.isEmpty()) {
                nameEditText.error = "Please enter name"
            }
            if (proPrice.isEmpty()) {
                priceEditText.error = "Please enter price"
            }
            if (proUrl.isEmpty()) {
                urlEditText.error = "Please enter url"
            }

            val proId = dbRef.push().key!!

            val product = ProductModel(proName, proPrice, proUrl)

            dbRef.child(proId).setValue(product)
                .addOnSuccessListener{
                    Toast.makeText(requireActivity(), "Data inserted successfully", Toast.LENGTH_LONG).show()

                    nameEditText.text.clear()
                    priceEditText.text.clear()
                    urlEditText.text.clear()


                }.addOnFailureListener { err ->
                    Toast.makeText(requireActivity(), "Error ${err.message}", Toast.LENGTH_LONG).show()
                }


        }





    }


    companion object {
        fun newInstance(): AmazonFragment {
            return AmazonFragment()
        }

    }





}

