package com.example.mymeals.presentation.meal_listing.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.mymeals.R
import com.example.mymeals.presentation.meal_listing.MealListState

@Composable
fun MealOnMindItem(
    item: MealListState.DesiredMeal,
    modifier: Modifier = Modifier
) {

    Box(
        modifier = Modifier
            .padding(
                start = 2.dp,
                end = 15.dp,
                top = 2.dp,
                bottom = 2.dp
            )
    ) {

        Row(
            modifier = modifier
                .wrapContentHeight()
                .wrapContentWidth()
                .shadow(elevation = 2.dp, shape = RoundedCornerShape(100))
                .clip(RoundedCornerShape(100))
                .background(color = colorResource(id = R.color.white))
                .padding(5.dp),
            verticalAlignment = Alignment.CenterVertically

        ) {

            MealImage(
                url = item.image.orEmpty(),
                size = 34.dp,
            )

            Spacer(modifier = modifier.width(10.dp))
            Text(
                modifier = modifier
                    .wrapContentWidth(),
                style = TextStyle(
                    color = colorResource(id = R.color.blue_selected),
                    fontSize = 24.sp,
                ),
                text = item.title.toString(),
                textAlign = TextAlign.Start

            )
            Spacer(modifier = modifier.width(10.dp))
        }
    }
}

@Composable
@Preview(showBackground = true)
fun MealOnMindItemPreview() {
    MealOnMindItem(MealListState.DesiredMeal())
}