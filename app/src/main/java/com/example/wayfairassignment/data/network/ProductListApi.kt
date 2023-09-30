package com.example.wayfairassignment.data.network

import com.example.wayfairassignment.api.ApiConstants.GET_PRODUCT_LIST
import com.example.wayfairassignment.api.ProductList
import retrofit2.http.GET

interface ProductListApi {
    @GET(GET_PRODUCT_LIST)
    fun getProductList(): List<ProductList>
}