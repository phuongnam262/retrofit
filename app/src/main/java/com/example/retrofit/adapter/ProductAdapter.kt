package com.example.retrofit.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.retrofit.R
import com.example.retrofit.model.Product
import com.example.retrofit.my_interface.ClickItemListener


class ProductAdapter(
    private val mListProducts: MutableList<Product>,
    private val onClick: ClickItemListener
) : RecyclerView.Adapter<ProductAdapter.ProductViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProductViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_product, parent, false)
        return ProductViewHolder(view)
    }

    override fun onBindViewHolder(holder: ProductViewHolder, position: Int) {
        val product = mListProducts[position]

        holder.productTitle.text = product.title
        holder.productPrice.text = product.price.toString()
        Glide.with(holder.itemView.context)
            .load(product.image)
            .into(holder.productImg)
        holder.layoutItem.setOnClickListener {
            // Xử lý khi item được click
            onClick.onClickItem(product)
        }
    }



    override fun getItemCount(): Int {
        return mListProducts.size
    }

    class ProductViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val productTitle: TextView = itemView.findViewById(R.id.product_title)
        val productPrice: TextView = itemView.findViewById(R.id.product_price)
        val productImg: ImageView = itemView.findViewById(R.id.img_detail)
        val layoutItem: CardView = itemView.findViewById(R.id.layout_item)
    }
}
