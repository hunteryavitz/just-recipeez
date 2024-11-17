package com.example.justrecipestest.ui.components.recipecard.header

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.justrecipestest.R

val fontFamilyDancingScript = FontFamily(
    Font(R.font.dancing_script, FontWeight.Bold),
)

@Composable
fun HeaderDescription(description: String) {
    Row(
        modifier = Modifier
            .padding(8.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.Center
    ) {
        Text(
            text = description,
            color = Color.Black,
            textAlign = TextAlign.Center,
            fontSize = 16.sp,
            fontFamily = fontFamilyDancingScript
        )
    }
}

@Preview
@Preview("Dark Theme", uiMode = android.content.res.Configuration.UI_MODE_NIGHT_YES)
@Composable
fun PreviewHeaderDescription() {
    HeaderDescription(description = "This is a delicious chocolate cake recipe that is perfect for any occasion.")
}
