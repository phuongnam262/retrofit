package com.example.retrofit.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.retrofit.model.Product
import com.example.retrofit.R

class OtherProductsAdapter(private val products: List<Product>) :
    RecyclerView.Adapter<OtherProductsAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_other_product, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val product = products[position]
        Glide.with(holder.itemView.context)
            .load(product.image)
            .into(holder.imgOther)

    }

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val imgOther: ImageView = itemView.findViewById(R.id.img_other)
    }

    override fun getItemCount() = products.size
}