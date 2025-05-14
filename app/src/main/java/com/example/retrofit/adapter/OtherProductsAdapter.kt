package com.example.retrofit.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.retrofit.model.Product
import com.example.retrofit.R

class OtherProductsAdapter(private val products: List<Product>) :
    RecyclerView.Adapter<OtherProductsAdapter.ViewHolder>() {

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val otherTitle: TextView = itemView.findViewById(R.id.title)
        val otherPrice: TextView = itemView.findViewById(R.id.price)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_other_product, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val product = products[position]
        holder.otherTitle.text = product.title
        holder.otherPrice.text = product.price
    }

    override fun getItemCount() = products.size
}