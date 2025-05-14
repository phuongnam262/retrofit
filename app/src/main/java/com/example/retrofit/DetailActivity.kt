package com.example.retrofit

import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide
import com.example.retrofit.model.Product

class DetailActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)

        val bundle = intent.extras
        if (bundle == null) return

        val product = bundle.get("object_user") as? Product ?: return
        val id=findViewById<TextView>(R.id.id_detail)
        id.text=product.id.toString()
        val title=findViewById<TextView>(R.id.title_detail)
        title.text=product.title
        val price=findViewById<TextView>(R.id.price_detail)
        price.text=product.price.toString()
        val img=findViewById<ImageView>(R.id.product_img)
        Glide.with(this)
            .load(product.image)
            .into(img)
    }
}