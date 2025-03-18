package com.example.mymeals.presentation.meal_listing

import com.example.mymeals.domain.model.MealListing

sealed interface MealListEvent {
    data class QueryRecommendation(val query : String) : MealListEvent
    data class RecommendationClick(val meal : MealListing) : MealListEvent
    data object SheetDismiss: MealListEvent
}