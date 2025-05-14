package com.example.retrofit.my_interface

import com.example.retrofit.model.Product

interface ClickItemListener {
    fun onClickItem(product: Product)
}