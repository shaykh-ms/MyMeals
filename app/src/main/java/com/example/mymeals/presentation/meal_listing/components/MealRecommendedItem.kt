package com.example.mymeals.presentation.meal_listing.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.constraintlayout.compose.Dimension
import com.example.mymeals.R
import com.example.mymeals.domain.model.MealListing

@Composable
fun MealRecommendedItem(
    meal: MealListing,
    isSelected: Boolean,
    modifier: Modifier = Modifier
) {


    Box(
        modifier = Modifier
            .padding(end = 15.dp)
    ) {

        val cornerRadius = 24.dp


        ConstraintLayout(
            modifier = Modifier
                .wrapContentHeight()
                .wrapContentWidth()
                .shadow(elevation = 2.dp, shape = RoundedCornerShape(cornerRadius))
                .clip(RoundedCornerShape(cornerRadius))
                .background(color = colorResource(id = if (isSelected) R.color.blue_selected else R.color.white))
                .padding(
                    start = 8.dp,
                    end = 8.dp,
                    top = 8.dp,
                    bottom = 2.dp,
                )
        ) {
            val (box, rating, column, mark) = createRefs()

            val innerBoxRadius = 16.dp
            val paddingHor = 20.dp
            val paddingVer = 25.dp
            Box(
                modifier = modifier
                    .wrapContentWidth()
                    .wrapContentHeight()
                    .clip(RoundedCornerShape(innerBoxRadius))
                    .background(color = colorResource(id = R.color.grey_background))
                    .padding(
                        start = paddingVer,
                        end = paddingVer,
                        top = paddingHor,
                        bottom = paddingHor,
                    )
                    .constrainAs(ref = box, constrainBlock = {})


            ) {
                MealImage(
                    url = meal.imageUrl.orEmpty(),
                    size = 150.dp,
                    modifier = Modifier
                        .clip(CircleShape)
                        .size(150.dp)
                        .fillMaxSize(0.8f)
                        .align(Alignment.Center),

                    )
            }

            Image(
                painter = painterResource(id = R.drawable.ic_v_mark),
                contentDescription = "Image at the top-right",
                modifier = Modifier
                    .size(30.dp)
                    .padding(top = 10.dp, end = 10.dp)
                    .constrainAs(ref = mark, constrainBlock = {
                        top.linkTo(parent.top)
                        end.linkTo(parent.end)
                    }),
                contentScale = ContentScale.Crop

            )


            Row(
                modifier = modifier
                    .width(70.dp)
                    .height(25.dp)
                    .padding(start = 5.dp, end = 5.dp)
                    .clip(RoundedCornerShape(innerBoxRadius))
                    .background(color = colorResource(id = R.color.orange_rating))
                    .constrainAs(ref = rating, constrainBlock = {
                        top.linkTo(box.bottom)
                        bottom.linkTo(box.bottom)
                        start.linkTo(parent.start)
                        end.linkTo(parent.end)
                    }),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.Center


            ) {

                Image(
                    painter = painterResource(id = R.drawable.ic_star),
                    contentDescription = null,
                    modifier = Modifier
                        .size(20.dp)
                        .padding(end = 5.dp)
                )

                Text(
                    modifier = modifier
                        .wrapContentWidth(),
                    style = TextStyle(
                        color = colorResource(id = R.color.white),
                        fontSize = 14.sp,
                    ),
                    text = "4.5",
                    textAlign = TextAlign.Start

                )

            }

            Column(
                modifier = modifier
                    .constrainAs(ref = column, constrainBlock = {
                        top.linkTo(rating.bottom)
                        start.linkTo(parent.start)
                        end.linkTo(parent.end)
                        width = Dimension.fillToConstraints
                    })
                    .padding(
                        horizontal = 10.dp
                    ),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {

                Spacer(modifier = modifier.height(10.dp))

                Text(
                    modifier = modifier
                        .wrapContentWidth()
                        .align(Alignment.CenterHorizontally),
                    style = TextStyle(
                        color = colorResource(id = if (isSelected) R.color.white else R.color.blue_selected),
                        fontSize = 24.sp,
                    ),
                    text = meal.dishName.orEmpty(),
                    textAlign = TextAlign.Center,
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis

                )

                Spacer(modifier = modifier.height(10.dp))

                Row(
                    modifier = modifier,
                    verticalAlignment = Alignment.CenterVertically

                ) {


                    Image(
                        painter = painterResource(id = R.drawable.ic_food_bank),
                        contentDescription = null,
                        modifier = Modifier
                            .size(20.dp)
                            .padding(end = 5.dp),
                        colorFilter = ColorFilter.tint(colorResource(id = if (isSelected) R.color.white_alpha_73 else R.color.grey_description))
                    )

                    Text(
                        modifier = modifier
                            .wrapContentWidth(),
                        style = TextStyle(
                            color = colorResource(id = if (isSelected) R.color.white_alpha_73 else R.color.grey_description),
                            fontSize = 14.sp,
                        ),
                        text = "30 min",
                        textAlign = TextAlign.Start

                    )
                    Spacer(modifier = Modifier.weight(1f))

                    Text(
                        modifier = modifier
                            .wrapContentWidth(),
                        style = TextStyle(
                            color = colorResource(id = if (isSelected) R.color.white_alpha_73 else R.color.grey_description),
                            fontSize = 14.sp,
                        ),
                        text = "\u2022 Medium Prep.",
                        textAlign = TextAlign.End

                    )
                }
            }
        }
    }
}


@Composable
@Preview(showBackground = true)
fun MealRecommendedItemPreview() {
    val meal = MealListing(dishName = "Biryani", dishId = "123", imageUrl = "", isPublished = true)
    MealRecommendedItem(meal, true)
}