package com.example.mymeals

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.adaptive.currentWindowAdaptiveInfo
import androidx.compose.material3.adaptive.navigationsuite.NavigationSuiteScaffold
import androidx.compose.material3.adaptive.navigationsuite.NavigationSuiteScaffoldDefaults
import androidx.compose.material3.adaptive.navigationsuite.NavigationSuiteType
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.window.core.layout.WindowWidthSizeClass
import com.example.mymeals.presentation.meal_listing.MealListingScreen
import com.example.mymeals.presentation.meal_listing.MealListingViewModel
import com.example.mymeals.ui.theme.MyMealsTheme
import com.example.mymeals.util.Screen
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            MyMealsTheme {
                Scaffold(
                    modifier = Modifier
                        .fillMaxSize()
                        .shadow(elevation = 8.dp, shape = RoundedCornerShape(10.dp))
                ) { innerPadding ->
                    val viewModel: MealListingViewModel = hiltViewModel()
                    var selectedItemIndex by remember {
                        mutableIntStateOf(0)
                    }
                    val windowWidthClass =
                        currentWindowAdaptiveInfo().windowSizeClass.windowWidthSizeClass

                    NavigationSuiteScaffold(
                        navigationSuiteItems = {
                            Screen.entries.forEachIndexed { index, screen ->
                                item(
                                    selected = index == selectedItemIndex,
                                    onClick = {
                                        selectedItemIndex = index
                                    },
                                    icon = {
                                        Icon(
                                            imageVector = screen.icon,
                                            contentDescription = screen.title,
                                            tint = if (index == selectedItemIndex) {
                                                colorResource(R.color.orange_rating)
                                            } else {
                                                colorResource(R.color.blue_selected)
                                            }
                                        )
                                    },
                                    label = {
                                        Text(
                                            text = screen.title,
                                            color = if (index == selectedItemIndex) {
                                                colorResource(R.color.orange_rating)
                                            } else {
                                                colorResource(R.color.blue_selected)
                                            },
                                            style = TextStyle(
                                                fontSize = 16.sp,
                                                fontWeight = if (index == selectedItemIndex) FontWeight.SemiBold else FontWeight.Normal,
                                                lineHeight = 24.sp
                                            ),
                                            modifier = Modifier
                                                .padding(horizontal = 20.dp)
                                        )

                                    }
                                )

                            }

                        },
                        layoutType = if (windowWidthClass == WindowWidthSizeClass.COMPACT) {
                            NavigationSuiteType.NavigationRail
                        } else {
                            NavigationSuiteScaffoldDefaults.calculateFromAdaptiveInfo(
                                currentWindowAdaptiveInfo()
                            )
                        }


                    ) {

                        val state by viewModel.state.collectAsStateWithLifecycle()

                        Box(
                            modifier = Modifier
                                .fillMaxSize()
                                .shadow(
                                    elevation = 2.dp,
                                    shape = RoundedCornerShape(0.dp),
                                    clip = true
                                ),
                            contentAlignment = Alignment.Center
                        ) {
                            when (selectedItemIndex) {
                                0 -> {
                                    MealListingScreen(
                                        state = state,
                                        event = viewModel.uiEvent,
                                        onEvent = viewModel::onEvent,
                                        windowWidthClass = windowWidthClass,
                                    )
                                }

                                1 -> {
                                    Text(text = "Favourites")
                                }

                                2 -> {
                                    Text(text = "Manual")
                                }

                                3 -> {
                                    Text(text = "Device")
                                }

                                4 -> {
                                    Text(text = "Preferences")
                                }

                                5 -> {
                                    Text(text = "Settings")
                                }
                            }
                        }
                    }
                }
            }
        }
    }
}