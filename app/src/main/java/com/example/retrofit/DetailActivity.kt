package com.example.retrofit

import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.retrofit.adapter.OtherProductsAdapter
import com.example.retrofit.model.Product

class DetailActivity : AppCompatActivity() {

    private lateinit var recOtherProducts: RecyclerView
    private lateinit var mListProduct: MutableList<Product>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)

        val bundle = intent.extras
        if (bundle == null) return

        val product = bundle.get("object_product") as? Product ?: return

        val title=findViewById<TextView>(R.id.title_detail)
        title.text=product.title
        val price=findViewById<TextView>(R.id.price_detail)
        price.text=product.price.toString()
        val img=findViewById<ImageView>(R.id.img_detail)
        Glide.with(this)
            .load(product.image)
            .into(img)

        val description=findViewById<TextView>(R.id.description_detail)
        description.text=product.description

        recOtherProducts = findViewById(R.id.recOtherProducts)
        setupOtherProductsRecyclerView()
    }

    private fun setupOtherProductsRecyclerView() {
        // 1. Tạo dữ liệu mẫu (thay bằng dữ liệu thực tế)
        val otherProducts = listOf(
            Product("Banana", "$1.50 /kg", "Description..."),
            Product("Orange", "$2.50 /kg", "Description...")
        )

        // 2. Thiết lập LayoutManager (ngang)
        recOtherProducts.layoutManager = LinearLayoutManager(
            this,
            LinearLayoutManager.HORIZONTAL,
            false
        )

        // 3. Gán Adapter
        recOtherProducts.adapter = OtherProductsAdapter(otherProducts)
    }
}