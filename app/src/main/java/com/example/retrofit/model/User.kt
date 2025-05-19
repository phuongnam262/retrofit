package com.example.retrofit.model

import java.io.Serializable

data class User(
    val id: String ? =null,
    val username: String? =null,
    val password: String? =null,
):Serializable