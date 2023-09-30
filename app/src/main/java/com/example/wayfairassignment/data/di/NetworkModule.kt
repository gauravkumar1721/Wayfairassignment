package com.example.wayfairassignment.data.di

import com.example.wayfairassignment.api.ApiConstants
import com.example.wayfairassignment.data.network.ProductListApi
import com.example.wayfairassignment.domain.ProductListRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

@Module
@InstallIn(SingletonComponent::class)
class NetworkModule {

    @Provides
    fun providesRetrofit(): Retrofit {
        return Retrofit.Builder()
            .baseUrl(ApiConstants.API_BASE_URl)
            .client(OkHttpClient.Builder().build())
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    @Provides
    fun provideProductListRepository(apiService: ProductListApi): ProductListRepository {
        return com.example.wayfairassignment.data.ProductListRepository(apiService)
    }

    @Provides
    fun provideService(retrofit: Retrofit): ProductListApi {
        return retrofit.create(ProductListApi::class.java)
    }
}