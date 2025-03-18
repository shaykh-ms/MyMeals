package com.example.mymeals.presentation.meal_listing.sheet

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.mymeals.R

@Composable
fun CustomButtonItem(
    label: String,
    onClick: () -> Unit,
    isSolid: Boolean = false // Flag to determine if it's a solid button
) {
    if (isSolid) {
        Button(
            onClick = onClick,
            modifier = Modifier
                .padding(1.dp),
            colors = ButtonDefaults.buttonColors(
                containerColor = colorResource(R.color.orange_rating), // Solid background color
                contentColor = Color.White // Text color
            ),
            shape = RoundedCornerShape(8.dp)
        ) {
            Text(text = label)
        }
    } else {
        OutlinedButton(
            onClick = onClick,
            modifier = Modifier
                .padding(1.dp),
            border = BorderStroke(1.dp, color = colorResource(R.color.orange_rating)),
            colors = ButtonDefaults.outlinedButtonColors(
                contentColor = colorResource(R.color.orange_rating)
            ),
            shape = RoundedCornerShape(8.dp)
        ) {
            Text(text = label)
        }
    }
}


@Composable
@Preview(showBackground = true)
fun CustomButtonItemPreview() {
    CustomButtonItem(label = "Outline", isSolid = false, onClick = {})
}