package com.example.retrofit.my_interface

import com.example.retrofit.model.User
import com.google.gson.GsonBuilder
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query

interface AuthApi {
    @GET("users")
    fun getListUsers(@Query("userId") userId: Int): Call<List<User>>

    companion object {
        private const val BASE_URL =  "https://dummyjson.com/"

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
