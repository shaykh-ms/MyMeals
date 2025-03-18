package com.example.mymeals.presentation.meal_listing

sealed interface MealListUiEvent {
    data object ShowSheet : MealListUiEvent
}