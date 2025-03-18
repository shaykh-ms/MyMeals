package com.example.mymeals.presentation.meal_listing

import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.snapshots.SnapshotStateList
import com.example.mymeals.domain.model.MealListing

data class MealListState(
    val recommendationTitle : String? = null,
    val recommendationsFiltered  : SnapshotStateList<MealListing>? = mutableStateListOf(),
    val recommendations  : SnapshotStateList<MealListing>? = mutableStateListOf(),
    val desiredTitle : String? = null,
    val desiredMeals : List<DesiredMeal>? = arrayListOf(),
    val clickedRecommendation : MealListing? = null,
    val queryRecommendation : String? = null
) {
    data class DesiredMeal(
        val image : String? = null,
        val title : String? = null
    )
}
