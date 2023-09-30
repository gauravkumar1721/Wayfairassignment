package com.example.wayfairassignment.api

import com.google.gson.annotations.SerializedName

data class ProductList(

@SerializedName("name") val name    : String?,
@SerializedName("tagline") val tagline : String?,
@SerializedName("rating" ) val rating  : String?,
@SerializedName("date"   ) val date    : String?
)
