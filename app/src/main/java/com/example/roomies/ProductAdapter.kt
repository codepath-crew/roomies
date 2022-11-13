package com.example.roomies
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
class ProductAdapter(private val productList : ArrayList<Product>) : RecyclerView.Adapter<ProductAdapter.MyViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {

        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.fragment_amazon,
            parent,false)
        return MyViewHolder(itemView)

    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {

        val currentitem = productList[position]

        holder.proName.text = currentitem.proName
        holder.proPrice.text = currentitem.proPrice
        holder.proUrl.text = currentitem.proUrl

    }

    override fun getItemCount(): Int {

        return productList.size
    }


    class MyViewHolder(itemView : View) : RecyclerView.ViewHolder(itemView){

        val proName : TextView = itemView.findViewById(R.id.editName)
        val proPrice : TextView = itemView.findViewById(R.id.editPrice)
        val proUrl : TextView = itemView.findViewById(R.id.editUrl)

    }
}