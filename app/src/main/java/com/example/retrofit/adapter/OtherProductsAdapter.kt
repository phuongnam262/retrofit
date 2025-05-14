package com.example.retrofit.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.retrofit.model.Product
import com.example.retrofit.R
import com.example.retrofit.my_interface.ClickItemListener

class OtherProductsAdapter(
    private val products: List<Product>,
    private val onClick: ClickItemListener
) :
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
        holder.otherItem.setOnClickListener {
            onClick.onClickItem(product)
        }

    }

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val imgOther: ImageView = itemView.findViewById(R.id.img_other)
        val otherItem: CardView = itemView.findViewById(R.id.other_item)
    }

    override fun getItemCount() = products.size
}