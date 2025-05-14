package com.example.retrofit.my_interface

import com.example.retrofit.model.Product
import com.google.gson.GsonBuilder
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {
    @GET("products")
    fun getListUsers(@Query("userId") userId: Int): Call<List<Product>>

    companion object {
        private const val BASE_URL =  "https://fakestoreapi.com/"

        val apiService: ApiService by lazy {
            val gson = GsonBuilder().create()

            Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build()
                .create(ApiService::class.java)
        }
    }
}