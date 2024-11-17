package com.example.justrecipestest.ui.components.recipecard.header

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun HeaderTitle(title: String) {
    Row(modifier = Modifier
        .padding(8.dp)
        .fillMaxWidth()
    ) {
        Text(
            text = title,
            color = Color.Black,
            textAlign = TextAlign.Center,
            fontSize = 28.sp,
            fontFamily = fontFamilyDancingScript,
            modifier = Modifier
                .fillMaxWidth()
        )
    }
}

@Preview
@Preview("Dark Theme", uiMode = android.content.res.Configuration.UI_MODE_NIGHT_YES)
@Composable
fun PreviewTitle() {
    HeaderTitle(title = "Chocolate Cake")
}
