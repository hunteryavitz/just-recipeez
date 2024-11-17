package com.example.justrecipestest.ui.components.recipecard.header

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.sp

@Composable
fun SubtitleCookTimeInfo(prepTime: Int) {
    Text(
        text = "$prepTime Minutes",
        color = Color.Black,
        textAlign = TextAlign.Center,
        fontSize = 18.sp,
        fontFamily = fontFamilyDancingScript
    )
}
