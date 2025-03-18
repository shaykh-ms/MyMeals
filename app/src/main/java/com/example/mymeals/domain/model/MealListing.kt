package com.example.mymeals.domain.model

data class MealListing(
    val dishName: String? = null,
    val dishId: String? = null,
    val imageUrl: String? = null,
    val isPublished: Boolean = false
)
