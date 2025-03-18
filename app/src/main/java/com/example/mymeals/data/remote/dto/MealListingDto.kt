package com.example.mymeals.data.remote.dto

import com.google.gson.annotations.SerializedName

data class MealListingDto(
    @SerializedName("dishName")
    val dishName: String?,
    @SerializedName("dishId")
    val dishId: String?,
    @SerializedName("imageUrl")
    val imageUrl: String?,
    @SerializedName("isPublished")
    val isPublished: Boolean?
)
