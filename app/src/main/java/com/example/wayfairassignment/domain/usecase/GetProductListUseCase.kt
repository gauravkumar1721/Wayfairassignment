package com.example.wayfairassignment.domain.usecase

import com.example.wayfairassignment.api.ProductList
import com.example.wayfairassignment.domain.ProductListRepository
import com.example.wayfairassignment.domain.common.IFailure
import com.example.wayfairassignment.domain.common.Result
import com.example.wayfairassignment.domain.common.UseCase
import javax.inject.Inject

class GetProductListUseCase@Inject constructor(private val productListRepository: ProductListRepository): UseCase<Unit, List<ProductList>>() {
    override suspend fun run(params: Unit): Result<IFailure, List<ProductList>> {
        return productListRepository.getProductList()
    }
}