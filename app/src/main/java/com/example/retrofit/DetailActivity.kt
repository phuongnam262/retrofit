package com.example.retrofit

import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.retrofit.adapter.OtherProductsAdapter
import com.example.retrofit.model.Product
import retrofit2.Callback
import com.example.retrofit.my_interface.ApiService
import retrofit2.Call
import retrofit2.Response

class DetailActivity : AppCompatActivity() {

    private lateinit var recOtherProducts: RecyclerView

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
        setupOtherProductsRecyclerView(product.id)
    }

    private fun setupOtherProductsRecyclerView(currentProductId: String?) {
        ApiService.apiService.getListProducts(productId = 1).enqueue(object : Callback<List<Product>> {
            override fun onResponse(call: Call<List<Product>>, response: Response<List<Product>>) {
                val allProducts = response.body() ?: emptyList()

                // Lọc ra các sản phẩm khác (không phải sản phẩm hiện tại)
                val otherProducts = allProducts.filter { it.id != currentProductId }

                // Lấy ngẫu nhiên 5 sản phẩm (hoặc ít hơn nếu không đủ)
                val randomOtherProducts = otherProducts.shuffled().take(5)

                // Thiết lập RecyclerView
                recOtherProducts.layoutManager = LinearLayoutManager(
                    this@DetailActivity,
                    LinearLayoutManager.HORIZONTAL,
                    false
                )

                recOtherProducts.adapter = OtherProductsAdapter(randomOtherProducts)
            }

            override fun onFailure(call: Call<List<Product>>, t: Throwable) {
                Toast.makeText(this@DetailActivity, "Failed to load other products", Toast.LENGTH_SHORT).show()
            }
        })
    }
}