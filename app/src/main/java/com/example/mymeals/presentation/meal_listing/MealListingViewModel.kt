package com.example.mymeals.presentation.meal_listing

import android.util.Log
import androidx.compose.runtime.toMutableStateList
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.mymeals.domain.repository.MealRepository
import com.example.mymeals.util.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.async
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MealListingViewModel @Inject constructor(
    private val mealRepository: MealRepository
) : ViewModel() {

    private val _state = MutableStateFlow(MealListState())
    val state = _state.asStateFlow()

    private val _uiEvent = MutableSharedFlow<MealListUiEvent>()
    val uiEvent = _uiEvent.asSharedFlow()


    init {
        getRecommendedData()
    }


    private fun getRecommendedData() {
        viewModelScope.launch {
            val listing = async { mealRepository.getMealListing() }
            when (val result = listing.await()) {
                is Resource.Error -> {
                    Log.d("listingData", "Error")
                }

                is Resource.Loading -> {
                    Log.d("listingData", "Loading")
                }

                is Resource.Success -> {
                    _state.update {
                        it.copy(
                            recommendations = result.data?.toMutableStateList(),
                            recommendationsFiltered = result.data?.toMutableStateList(),
                        )
                    }
                    getDesiredData()
                    Log.d("listingData", result.data.toString())
                }
            }
        }
    }

    private fun getDesiredData() {
        val data: MutableList<String> = mutableListOf()
        data.add("Biryani")
        data.add("Hyderabadi")
        data.add("Biryani")
        data.add("Chicken Changezhi")
        data.add("Biryani")
        data.add("Biryani")
        data.add("Mutton Korma")
        data.add("Biryani")
        _state.update { state ->
            state.copy(
                desiredMeals = data.map {
                    MealListState.DesiredMeal(
                        image = state.recommendations.takeIf { it?.isNotEmpty() == true }
                            ?.first()?.imageUrl.orEmpty(),
                        title = it
                    )
                }
            )
        }
    }

    fun onEvent(event: MealListEvent) {
        when (event) {
            is MealListEvent.QueryRecommendation -> {
                _state.update {
                    it.copy(
                        queryRecommendation = event.query.lowercase(),
                        recommendationsFiltered = it.recommendations?.filter {
                            it.dishName?.lowercase()?.contains(event.query.lowercase()) == true
                        }?.toMutableStateList()
                    )
                }
            }

            is MealListEvent.RecommendationClick -> {
                _state.update {
                    it.copy(clickedRecommendation = event.meal)
                }
                onUiEvent(MealListUiEvent.ShowSheet)
            }

            MealListEvent.SheetDismiss -> {
                _state.update {
                    it.copy(clickedRecommendation = null)
                }
            }
        }
    }

    private fun onUiEvent(uiEvent: MealListUiEvent) {
        viewModelScope.launch {
            _uiEvent.emit(uiEvent)
        }
    }
}