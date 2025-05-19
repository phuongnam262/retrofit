package com.example.retrofit.my_interface

import com.example.retrofit.model.User
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET

interface AuthApi {
    @GET("users")
    fun getUsers(): Call<UserResponse>

    companion object {
        private const val BASE_URL = "https://dummyjson.com/"

        val apiService: AuthApi by lazy {
            Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build()
                .create(AuthApi::class.java)
        }
    }

    data class UserResponse(
        val users: List<User>
    )
}
