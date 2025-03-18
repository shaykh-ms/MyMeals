package com.example.mymeals.data.mapper

import com.example.mymeals.data.remote.dto.MealListingDto
import com.example.mymeals.domain.model.MealListing

fun MealListingDto.toMealListing(): MealListing {
    return MealListing(
        dishName = dishName ?: "",
        dishId = dishId ?: "",
        imageUrl = imageUrl ?: "",
        isPublished = isPublished ?: false
    )
}