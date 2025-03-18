package com.example.mymeals.presentation.meal_listing.components

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Search
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.tooling.preview.Preview
import com.example.mymeals.R

@Composable
fun MealInputItem(
    modifier: Modifier = Modifier,
    text: String,
    onValueChange: (String) -> Unit
) {

    OutlinedTextField(
        value = text,
        onValueChange = { onValueChange(it) },
        placeholder = {
            Text(
                text = "Search for dish or ingredient",
                color = Color.LightGray
            )
        },
        leadingIcon = {
            Icon(
                imageVector = Icons.Rounded.Search,
                contentDescription = "Search Icon",
                tint = colorResource(R.color.blue_selected)
            )
        },
        modifier = modifier
            .fillMaxWidth()
            .wrapContentHeight(),
        shape = MaterialTheme.shapes.extraLarge,
        colors = OutlinedTextFieldDefaults.colors(
            focusedBorderColor = colorResource(R.color.blue_selected),
            unfocusedBorderColor = colorResource(R.color.blue_selected),
        )
    )
}


@Composable
@Preview(showBackground = true)
fun MealInputItemPreview() {
    MealInputItem(
        text = "hello",
        onValueChange = {}
    )
}