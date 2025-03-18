package com.example.mymeals.util

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Devices
import androidx.compose.material.icons.filled.FavoriteBorder
import androidx.compose.material.icons.filled.Fireplace
import androidx.compose.material.icons.filled.FoodBank
import androidx.compose.material.icons.filled.PrecisionManufacturing
import androidx.compose.material.icons.filled.Settings
import androidx.compose.ui.graphics.vector.ImageVector

enum class Screen(val title: String, val icon: ImageVector) {
    COOK("Cook", Icons.Default.FoodBank),
    FAVOURITES("Favourites", Icons.Default.FavoriteBorder),
    MANUAL("Manual", Icons.Default.PrecisionManufacturing),
    DEVICE("Device", Icons.Default.Devices),
    PREFERENCES("Preferences", Icons.Default.Fireplace),
    SETTINGS("Settings", Icons.Default.Settings),
}