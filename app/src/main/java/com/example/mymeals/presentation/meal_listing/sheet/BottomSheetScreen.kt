package com.example.mymeals.presentation.meal_listing.sheet

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.DpSize
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.commandiron.wheel_picker_compose.WheelTimePicker
import com.commandiron.wheel_picker_compose.core.TimeFormat
import com.commandiron.wheel_picker_compose.core.WheelPickerDefaults
import com.example.mymeals.R

@Composable
fun BottomSheetScreen(
    modifier: Modifier = Modifier
) {


    Box(
        modifier
            .padding(horizontal = 20.dp)
    ) {

        Column {

            Row(
                modifier = Modifier
                    .fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween

            ) {

                Text(
                    modifier = Modifier
                        .wrapContentWidth(),
                    style = TextStyle(
                        color = colorResource(id = R.color.blue_selected),
                        fontSize = 24.sp,
                        fontWeight = FontWeight.SemiBold
                    ),
                    text = "Schedule cooking time",
                    textAlign = TextAlign.Start,
                )


                Spacer(modifier = Modifier.weight(1f))

                BottomSheetCloseItem()


            }

            Spacer(modifier = Modifier.height(32.dp))

            Row(
                modifier = Modifier.align(Alignment.CenterHorizontally),
                horizontalArrangement = Arrangement.spacedBy(24.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {

                WheelTimePicker(
                    modifier = Modifier
                        .clip(
                            RoundedCornerShape(
                                5
                            )
                        )
                        .background(colorResource(R.color.blue_f4f6fd)),
                    timeFormat = TimeFormat.HOUR_24,
                    textStyle = MaterialTheme.typography.h4,
                    size = DpSize(200.dp, 200.dp),
                    textColor = colorResource(id = R.color.blue_selected),
                    selectorProperties = WheelPickerDefaults.selectorProperties(
                        enabled = false,
                    )
                ) { snappedTime ->

                }


                Column(
                    modifier = Modifier.wrapContentSize(),
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.spacedBy(10.dp)
                ) {

                    Text(
                        modifier = Modifier
                            .clip(RoundedCornerShape(15))
                            .background(colorResource(R.color.blue_selected))
                            .padding(6.dp),
                        style = TextStyle(
                            color = colorResource(id = R.color.white),
                            fontSize = 20.sp,
                        ),
                        text = "AM",
                    )

                    Text(
                        modifier = Modifier
                            .clip(RoundedCornerShape(15))
                            .background(colorResource(R.color.blue_f4f6fd))
                            .padding(6.dp),
                        style = TextStyle(
                            color = colorResource(id = R.color.blue_selected),
                            fontSize = 20.sp,
                        ),
                        text = "PM",
                    )


                }

            }




            Spacer(modifier = Modifier.height(32.dp))


            Row(
                modifier = Modifier
                    .align(Alignment.End)
                    .fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically

            ) {

                Text(
                    modifier = Modifier
                        .wrapContentWidth(),
                    style = TextStyle(
                        color = colorResource(id = R.color.red),
                        fontSize = 20.sp,
                        textDecoration = TextDecoration.Underline,
                    ),
                    text = "Delete",
                )


                Row(
                    modifier = Modifier,
                    horizontalArrangement = Arrangement.spacedBy(16.dp),
                    verticalAlignment = Alignment.CenterVertically

                ) {
                    CustomButtonItem(label = "Re-schedule", isSolid = false, onClick = {})
                    CustomButtonItem(label = "Cook Now", isSolid = true, onClick = {})
                }


            }


        }


    }


}


@Composable
@Preview(showBackground = true)
fun BottomSheetScreenPreview() {
    BottomSheetScreen()
}