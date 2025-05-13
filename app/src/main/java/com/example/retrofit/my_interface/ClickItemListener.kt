package com.example.retrofit.my_interface

import com.example.retrofit.model.User

interface ClickItemListener {
    fun onClickItem(user: User)
}