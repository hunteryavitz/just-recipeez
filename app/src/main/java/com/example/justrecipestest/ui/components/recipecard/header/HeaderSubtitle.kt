package com.example.justrecipestest.ui.components.recipecard.header

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun HeaderSubtitle(servings: Int, prepTime: Int) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier
            .padding(4.dp)
    ) {
        Column(modifier = Modifier
            .weight(1f),
            horizontalAlignment = Alignment.End
        ) {
            SubtitleServingsInfo(servings)
        }
        Column(modifier = Modifier) {
            Text(
                text = " | ",
                color = Color.Black,
                fontSize = 24.sp
            )
        }
        Column(modifier = Modifier
            .weight(1f),
            horizontalAlignment = Alignment.Start
        ) {
            SubtitleCookTimeInfo(prepTime)
        }
    }
}

@Preview
@Preview("Dark Theme", uiMode = android.content.res.Configuration.UI_MODE_NIGHT_YES)
@Composable
fun PreviewHeaderSubtitle() {
    HeaderSubtitle(servings = 4, prepTime = 30)
}
