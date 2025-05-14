package com.example.retrofit.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
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
        val user = mListProducts[position]
        holder.productId.text = user.id.toString()
        holder.productTitle.text = user.title
        holder.productPrice.text = user.price.toString()
        holder.layoutItem.setOnClickListener {
            // Xử lý khi item được click
            onClick.onClickItem(user)
        }
    }



    override fun getItemCount(): Int {
        return mListProducts.size
    }

    class ProductViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val productId: TextView = itemView.findViewById(R.id.product_id)
        val productTitle: TextView = itemView.findViewById(R.id.product_title)
        val productPrice: TextView = itemView.findViewById(R.id.product_price)
        val layoutItem: LinearLayout = itemView.findViewById(R.id.layout_item)
    }
}
