package com.example.retrofit

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.ImageButton
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.retrofit.adapter.OtherProductsAdapter
import com.example.retrofit.model.Product
import com.example.retrofit.my_interface.ApiService
import com.example.retrofit.my_interface.ClickItemListener
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class DetailActivity : AppCompatActivity() {

    private lateinit var recOtherProducts: RecyclerView
    private var currentQuantity = 1

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)

        val product = intent.getSerializableExtra("object_product") as? Product ?: run {
            finish()
            return
        }

        setupAddandRemoveQuantity()
        displayProductDetails(product)
        setupOtherProductsRecyclerView(product.id)
    }

    private fun setupAddandRemoveQuantity() {
        val btnDecrease = findViewById<ImageButton>(R.id.btnDecrease)
        val btnIncrease = findViewById<ImageButton>(R.id.btnIncrease)
        val tvQuantity = findViewById<TextView>(R.id.tvQuantity)
        // Giảm số lượng (tối thiểu = 1)
        btnDecrease.setOnClickListener {
            if (currentQuantity > 1) {
                currentQuantity--
                tvQuantity.text = currentQuantity.toString()
            }
        }

        // Tăng số lượng (tối đa = 10)
        btnIncrease.setOnClickListener {
            if (currentQuantity < 10) {
                currentQuantity++
                tvQuantity.text = currentQuantity.toString()
            }
        }

    }

    private fun displayProductDetails(product: Product) {
        findViewById<TextView>(R.id.title_detail).text = product.title
        findViewById<TextView>(R.id.price_detail).text = buildString {
        append("$")
        append(product.price)
    }

        findViewById<TextView>(R.id.description_detail).text = product.description
        Glide.with(this).load(product.image).into(findViewById(R.id.img_detail))
        // Xử lý nút Add to Cart
        findViewById<Button>(R.id.btnAddToCart).setOnClickListener {
            Toast.makeText(this, "Added to cart", Toast.LENGTH_SHORT).show()
        }
    }

    private fun setupOtherProductsRecyclerView(currentProductId: String?) {
        ApiService.apiService.getListProducts(productId = 1).enqueue(object : Callback<List<Product>> {
            override fun onResponse(call: Call<List<Product>>, response: Response<List<Product>>) {
                val allOtherProducts = response.body() ?: emptyList()

                // Lọc và lấy 5 sản phẩm ngẫu nhiên khác
                val otherRandomProducts = allOtherProducts
                    .filter { it.id != currentProductId }
                    .shuffled()
                    .take(5)

                setupRecyclerView(otherRandomProducts)
            }

            override fun onFailure(call: Call<List<Product>>, t: Throwable) {
                Toast.makeText(this@DetailActivity, "Error: ${t.message}", Toast.LENGTH_SHORT).show()
            }
        })
    }

    private fun setupRecyclerView(products: List<Product>) {
        recOtherProducts = findViewById(R.id.recOtherProducts)
        recOtherProducts.layoutManager = LinearLayoutManager(
            this,
            LinearLayoutManager.HORIZONTAL,
            false
        )

        recOtherProducts.adapter = OtherProductsAdapter(products, object : ClickItemListener {
            override fun onClickItem(product: Product) {
                val intent = Intent(this@DetailActivity, DetailActivity::class.java).apply {
                    putExtra("object_product", product)
                }
                startActivity(intent)
            }
        })
    }
}