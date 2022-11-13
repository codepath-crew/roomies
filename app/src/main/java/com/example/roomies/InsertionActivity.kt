package com.example.roomies
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.roomies.databinding.ActivityMainBinding
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase

class InsertionActivity : AppCompatActivity () {

    private lateinit var binding : ActivityMainBinding
    private lateinit var productName: EditText
    private lateinit var productPrice: EditText
    private lateinit var productUrl: EditText
    private lateinit var productSaveData: Button

    private lateinit var dbRef: DatabaseReference

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(R.layout.fragment_amazon)

        productName = findViewById(R.id.editName)
        productPrice = findViewById(R.id.editPrice)
        productUrl = findViewById(R.id.editUrl)
        productSaveData = findViewById(R.id.button2)

        dbRef = FirebaseDatabase.getInstance().getReference("WishProducts")

        productSaveData.setOnClickListener {
            saveProductData()
        }
    }

    private fun saveProductData() {

        //getting values
        val proName = productName.text.toString()
        val proPrice = productPrice.text.toString()
        val proUrl = productUrl.text.toString()

        if (proName.isEmpty()) {
            productName.error = "Please enter name"
        }
        if (proPrice.isEmpty()) {
            productPrice.error = "Please enter price"
        }
        if (proUrl.isEmpty()) {
            productUrl.error = "Please enter url"
        }

       val proId = dbRef.push().key!!

        val product = ProductModel(proName, proPrice, proUrl)

        dbRef.child(proId).setValue(product)
            .addOnSuccessListener{
                Toast.makeText(this, "Data inserted successfully", Toast.LENGTH_LONG).show()

                productName.text.clear()
                productPrice.text.clear()
                productUrl.text.clear()


            }.addOnFailureListener { err ->
                Toast.makeText(this, "Error ${err.message}", Toast.LENGTH_LONG).show()
            }

    }

}


