package com.example.justrecipestest.ui.components.recipecard.header

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.justrecipestest.R

@Composable
fun HeaderImage(
    image: Int,
    isLandscape: Boolean = false
) {
    Row(modifier = Modifier
        .padding(8.dp)) {
        Image(
            painter = painterResource(image),
            contentDescription = "Header",
            contentScale = ContentScale.Crop,
            modifier = Modifier
                .size(if (isLandscape) 256.dp else 128.dp)
                .clip(RoundedCornerShape(14.dp))
        )
    }
}

@Preview
@Preview("Dark Theme", uiMode = android.content.res.Configuration.UI_MODE_NIGHT_YES)
@Composable
fun PreviewImageHeader() {
    HeaderImage(image = R.drawable.chocolate_cake)
}
