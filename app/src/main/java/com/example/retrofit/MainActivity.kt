package com.example.retrofit

import android.content.Intent
import android.os.Bundle
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.retrofit.adapter.ProductAdapter
import com.example.retrofit.my_interface.ApiService
import com.example.retrofit.model.Product
import com.example.retrofit.model.User
import com.example.retrofit.my_interface.ClickItemListener
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivity : AppCompatActivity() {

    private lateinit var recProduct: RecyclerView
    private lateinit var mListProduct: MutableList<Product>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        recProduct = findViewById(R.id.rec_product)
        recProduct.layoutManager = GridLayoutManager(this, 2)

        mListProduct = ArrayList()

        val itemDecoration = DividerItemDecoration(this, DividerItemDecoration.VERTICAL)
        recProduct.addItemDecoration(itemDecoration)

        callApiGetProducts()


    }

    private fun callApiGetProducts() {
        ApiService.apiService.getListProducts(productId = 1).enqueue(object : Callback<List<Product>> {
            override fun onResponse(
                call: Call<List<Product>>,
                response: Response<List<Product>>
            ) {
                val mListProduct = response.body() ?: emptyList()
                val productAdapter = ProductAdapter(mListProduct as MutableList<Product>, object : ClickItemListener {
                    override fun onClickItem(product: Product) {
                        onClickGoToDetail(product)
                    }
                })
                recProduct.adapter = productAdapter
            }

            override fun onFailure(call: Call<List<Product>>, t: Throwable) {
                Toast.makeText(this@MainActivity, "onFailure", Toast.LENGTH_SHORT).show()
            }
        })
    }
    private fun onClickGoToDetail(product: Product?) {
        val intent = Intent(this, DetailActivity::class.java)
        val bundle = Bundle()
        bundle.putSerializable("object_product", product)
        intent.putExtras(bundle)
        this.startActivity(intent)
    }
}
