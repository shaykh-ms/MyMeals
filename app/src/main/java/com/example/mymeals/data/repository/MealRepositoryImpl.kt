package com.example.mymeals.data.repository

import com.example.mymeals.data.mapper.toMealListing
import com.example.mymeals.data.remote.MealApi
import com.example.mymeals.domain.model.MealListing
import com.example.mymeals.domain.repository.MealRepository
import com.example.mymeals.util.Resource
import java.io.IOException
import javax.inject.Singleton

@Singleton
class MealRepositoryImpl(
    private val mealApi: MealApi
) : MealRepository {
    override suspend fun getMealListing(): Resource<List<MealListing>> {
        return try {
            val result = mealApi.getMealListings()
            Resource.Success(result.map { it.toMealListing() })
        } catch (e: IOException) {
            e.printStackTrace()
            Resource.Error(message = "Error")
        }
    }
}