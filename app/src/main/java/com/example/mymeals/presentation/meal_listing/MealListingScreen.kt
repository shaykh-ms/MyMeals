package com.example.mymeals.presentation.meal_listing

import android.content.res.Configuration
import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.safeContentPadding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.window.core.layout.WindowWidthSizeClass
import com.example.mymeals.R
import com.example.mymeals.presentation.meal_listing.components.MealBannerItem
import com.example.mymeals.presentation.meal_listing.components.MealInputItem
import com.example.mymeals.presentation.meal_listing.components.MealOnMindItem
import com.example.mymeals.presentation.meal_listing.components.MealRecommendedItem
import com.example.mymeals.presentation.meal_listing.components.MealScheduledItem
import com.example.mymeals.presentation.meal_listing.sheet.TimePickerBottomSheet
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MealListingScreen(
    state: MealListState,
    event: SharedFlow<MealListUiEvent>,
    onEvent: (MealListEvent) -> Unit,
    windowWidthClass: WindowWidthSizeClass
) {

    val sheetState = rememberModalBottomSheetState()
    val coroutineScope = rememberCoroutineScope()
    var isSheetOpen by rememberSaveable { mutableStateOf(false) }

    LaunchedEffect(Unit) {
        event.collectLatest {
            when (it) {
                MealListUiEvent.ShowSheet -> {
                    isSheetOpen = true
                    sheetState.show()
                }
            }
        }
    }


    Box(
        modifier = Modifier
            .fillMaxSize()
            .safeContentPadding()
    ) {


        LazyColumn(
            modifier = Modifier
                .wrapContentHeight()
                .fillMaxHeight()
                .padding(horizontal = 24.dp),
            verticalArrangement = Arrangement.Top,
        ) {

            item {
                @Composable
                fun TopBarSearchContent(modifier: Modifier) {
                    MealInputItem(
                        modifier = modifier,
                        text = state.queryRecommendation.orEmpty(),
                        onValueChange = {
                            onEvent(MealListEvent.QueryRecommendation(it))
                        }
                    )
                }

                @Composable
                fun TopBarActionContent() {
                    Row(
                        modifier = Modifier
                            .wrapContentHeight()
                            .wrapContentWidth(),
                        horizontalArrangement = Arrangement.spacedBy(16.dp),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        MealScheduledItem(
                            imageUrl = state.recommendations.takeIf { it?.isNotEmpty() == true }
                                ?.first()?.imageUrl.orEmpty()
                        )

                        IconButton(
                            onClick = {}
                        ) {
                            Image(
                                painter = painterResource(id = R.drawable.ic_bell),
                                contentDescription = null,
                                modifier = Modifier
                                    .size(40.dp),
                                contentScale = ContentScale.Fit
                            )
                        }

                        IconButton(
                            onClick = {}
                        ) {
                            Image(
                                painter = painterResource(id = R.drawable.ic_power_off),
                                contentDescription = null,
                                modifier = Modifier
                                    .size(40.dp),
                                contentScale = ContentScale.Fit
                            )
                        }
                    }
                }


                if (windowWidthClass == WindowWidthSizeClass.EXPANDED) {
                    Row(
                        modifier = Modifier
                            .wrapContentHeight()
                            .fillMaxWidth(),
                        horizontalArrangement = Arrangement.spacedBy(16.dp),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        TopBarSearchContent(Modifier.weight(1f))
                        TopBarActionContent()
                    }
                } else {
                    Column(
                        modifier = Modifier
                            .wrapContentHeight()
                            .fillMaxWidth(),
                        verticalArrangement = Arrangement.spacedBy(32.dp),
                        horizontalAlignment = Alignment.End
                    ) {
                        TopBarActionContent()
                        TopBarSearchContent(Modifier.fillMaxWidth())
                    }
                }

            }


            item {
                Spacer(modifier = Modifier.height(24.dp))
                Text(
                    modifier = Modifier
                        .wrapContentWidth()
                        .padding(start = 20.dp),
                    style = TextStyle(
                        color = colorResource(id = R.color.blue_selected),
                        fontSize = 24.sp,
                    ),
                    text = "What's on your mind?",
                    textAlign = TextAlign.End
                )

                Spacer(modifier = Modifier.height(10.dp))
                LazyRow(
                    modifier = Modifier
                        .padding(start = 20.dp),
                ) {
                    items(state.desiredMeals.orEmpty()) { item ->
                        MealOnMindItem(item)
                    }
                }
            }


            item {
                Spacer(modifier = Modifier.height(24.dp))
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    modifier = Modifier
                        .padding(start = 20.dp),
                ) {
                    Text(
                        modifier = Modifier
                            .wrapContentWidth(),
                        style = TextStyle(
                            color = colorResource(id = R.color.blue_selected),
                            fontSize = 24.sp,
                        ),
                        text = "Recommendations",
                        textAlign = TextAlign.Start
                    )

                    Spacer(modifier = Modifier.weight(1f))

                    Text(
                        modifier = Modifier
                            .wrapContentWidth(),
                        style = TextStyle(
                            color = colorResource(id = R.color.blue_selected),
                            fontSize = 14.sp,
                        ),
                        text = "Show all",
                        textAlign = TextAlign.End
                    )
                }

                Spacer(modifier = Modifier.height(16.dp))

                LazyRow(
                    modifier = Modifier
                        .padding(start = 20.dp),
                ) {
                    items(state.recommendationsFiltered.orEmpty()) { item ->
                        MealRecommendedItem(
                            item,
                            state.clickedRecommendation?.dishName==item.dishName,
                            modifier = Modifier
                                .clickable {
                                    onEvent(MealListEvent.RecommendationClick(item))
                                    Log.d("MealRecommendedItem", "show")

                                }
                        )
                    }
                }
            }


            item {
                Spacer(modifier = Modifier.height(24.dp))

                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(
                            start = 50.dp,
                            end = 50.dp,
                            bottom = 75.dp
                        ),
                    horizontalArrangement = Arrangement.SpaceEvenly // Or use Arrangement.fillMaxWidth and weight
                ) {
                    val popupRadius = 10.dp

                    MealBannerItem(
                        imageUrl = state.recommendations.takeIf { it?.isNotEmpty() == true }
                            ?.first()?.imageUrl.orEmpty(),
                        modifier = Modifier
                            .weight(1f) // Distributes available width equally
                            .height(75.dp)
                            .clip(RoundedCornerShape(popupRadius))
                    )
                    Spacer(modifier = Modifier.width(20.dp))

                    MealBannerItem(
                        imageUrl = state.recommendations.takeIf { it?.isNotEmpty() == true }
                            ?.first()?.imageUrl.orEmpty(),
                        modifier = Modifier
                            .weight(1f) // Distributes available width equally
                            .height(75.dp)
                            .clip(RoundedCornerShape(popupRadius))
                    )

                }

            }

        }


    }

    if (isSheetOpen) {
        TimePickerBottomSheet(
            sheetState = sheetState,
            onDismiss = {
                coroutineScope.launch {
                    sheetState.hide()
                    isSheetOpen = false
                    Log.d("MealRecommendedItem", "hide")
                    onEvent(MealListEvent.SheetDismiss)
                }
            }

        )
    }


}


@Composable
@Preview(
    showBackground = true,
    widthDp = 1280,
    heightDp = 1000,
    uiMode = Configuration.UI_MODE_TYPE_NORMAL,
)
fun MealListingScreenPreview() {
    MealListingScreen(
        state = MealListState(),
        event = MutableSharedFlow<MealListUiEvent>(),
        onEvent = {

        },
        windowWidthClass = WindowWidthSizeClass.COMPACT
    )
}