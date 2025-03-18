package com.example.mymeals.presentation.meal_listing.sheet

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.mymeals.R

@Composable
fun BottomSheetCloseItem(
    modifier: Modifier = Modifier
) {

    Box(
        modifier = modifier
            .wrapContentSize()
            .padding(5.dp),
        contentAlignment = Alignment.Center
    ) {


        Image(
            painter = painterResource(id = R.drawable.ic_close),
            contentDescription = null,
            modifier = Modifier
                .size(40.dp)
                .shadow(elevation = 2.dp, shape = CircleShape)
                .background(color = colorResource(id = R.color.white))
        )
    }
}


@Composable
@Preview(showBackground = true)
fun BottomSheetCloseItemPreview() {
    BottomSheetCloseItem()
}