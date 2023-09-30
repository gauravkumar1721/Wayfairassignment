package com.example.wayfairassignment.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.wayfairassignment.api.ProductList
import com.example.wayfairassignment.domain.ApiState
import com.example.wayfairassignment.domain.usecase.GetProductListUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
internal class MainActivityViewModel@Inject constructor(
    private val getProductListUseCase: GetProductListUseCase
): ViewModel() {

    private val mProductList = MutableLiveData<ApiState<List<ProductList>>>()
    val productList: LiveData<ApiState<List<ProductList>>> get() = mProductList

    fun getProductsList() {
        getProductListUseCase.invoke(
            scope = viewModelScope,
            params = Unit,
            onSuccess = {
                mProductList.value = ApiState.Success(it)
            },
            onFailure = {
                mProductList.value = ApiState.Failure(it)
            }
        )
    }
}