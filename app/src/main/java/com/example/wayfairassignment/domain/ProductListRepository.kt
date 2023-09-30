package com.example.wayfairassignment.domain

import com.example.wayfairassignment.api.ProductList
import com.example.wayfairassignment.domain.common.IFailure

interface ProductListRepository {
    suspend fun getProductList(): com.example.wayfairassignment.domain.common.Result<IFailure, List<ProductList>>
}