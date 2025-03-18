package com.example.mymeals.domain.repository

import com.example.mymeals.domain.model.MealListing
import com.example.mymeals.util.Resource

interface MealRepository {

    suspend fun getMealListing(): Resource<List<MealListing>>

}