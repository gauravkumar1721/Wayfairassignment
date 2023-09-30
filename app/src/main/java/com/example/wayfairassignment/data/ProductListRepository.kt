package com.example.wayfairassignment.data

import com.example.wayfairassignment.api.ProductList
import com.example.wayfairassignment.data.network.ProductListApi
import com.example.wayfairassignment.data.network.apiCall
import com.example.wayfairassignment.domain.common.IFailure
import com.example.wayfairassignment.domain.common.Result
import javax.inject.Inject

class ProductListRepository @Inject constructor(private val productService: ProductListApi): com.example.wayfairassignment.domain.ProductListRepository {
    override suspend fun getProductList(): Result<IFailure, List<ProductList>> {
        return apiCall(
            apiCall = {productService.getProductList()},
            successTransform = {it},
        )
    }


}