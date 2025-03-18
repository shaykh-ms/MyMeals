package com.example.mymeals.data.remote

import com.example.mymeals.data.remote.dto.MealListingDto
import retrofit2.http.GET

interface MealApi {

    @GET("nosh-assignment")
    suspend fun getMealListings(): List<MealListingDto>

    companion object {
        const val BASE_URL = "https://fls8oe8xp7.execute-api.ap-south-1.amazonaws.com/dev/"
    }
}