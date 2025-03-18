package com.example.mymeals.presentation.meal_listing.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.widthIn
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
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.mymeals.R

@Composable
fun MealScheduledItem(
    modifier: Modifier = Modifier,
    imageUrl: String,
) {

    Row(
        modifier = modifier
            .wrapContentHeight()
            .wrapContentWidth()
            .shadow(elevation = 2.dp, shape = RoundedCornerShape(100))
            .clip(
                RoundedCornerShape(100)
            )

            .background(color = colorResource(id = R.color.blue_scheduled))
            .padding(5.dp),
        verticalAlignment = Alignment.CenterVertically

    ) {


        MealImage(
            url = imageUrl,
            size = 50.dp
        )

        Spacer(modifier = modifier.width(10.dp))

        Column(

        ) {
            Text(
                modifier = modifier
                    .wrapContentWidth()
                    .widthIn(max = 200.dp),
                style = TextStyle(
                    color = colorResource(id = R.color.white),
                    fontSize = 24.sp,
                ),
                text = "Biryani with Muradabadi tarka",
                textAlign = TextAlign.Start,
                maxLines = 1,
                overflow = TextOverflow.Ellipsis

            )

            Text(
                modifier = modifier
                    .wrapContentWidth(),
                style = TextStyle(
                    color = colorResource(id = R.color.white),
                    fontSize = 14.sp,
                ),
                text = "Scheduled 6:30 AM",
                textAlign = TextAlign.Start

            )
        }


        Spacer(modifier = modifier.width(10.dp))

    }
}

@Composable
@Preview(showBackground = true)
fun MealScheduledItemPreview() {
    MealScheduledItem(imageUrl = "")
}