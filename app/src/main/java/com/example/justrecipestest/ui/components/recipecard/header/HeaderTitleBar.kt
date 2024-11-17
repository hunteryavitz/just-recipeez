package com.example.justrecipestest.ui.components.recipecard.header

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.KeyboardArrowUp
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@Composable
fun HeaderTitleBar(
    header: Header,
    isExpanded: Boolean,
    setIsExpanded: (Boolean) -> Unit,
    modifier: Modifier
) {
    Row(
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier
            .fillMaxWidth()
    ) {
        Column {
            IconButton(
                onClick = { }
            ) {
                Icon(
                    imageVector = Icons.Default.KeyboardArrowUp,
                    contentDescription = "Expand Card",
                    tint = Color.Black,
                    modifier = modifier
                        .size(32.dp)
                        .clickable(onClick = { setIsExpanded(!isExpanded) })
                )
            }
        }
        Column(
            modifier = modifier
                .weight(1f)
        ) {
            HeaderTitle(header.title)
        }
        Column {
            IconButton(
                onClick = { }
            ) {
                Icon(
                    imageVector = Icons.Default.Favorite,
                    contentDescription = "Expand Card",
                    tint = Color.Black,
                    modifier = modifier
                        .size(22.dp)
                        .clickable(onClick = { setIsExpanded(!isExpanded) })
                )
            }
        }
    }
}

@Preview
@Preview("Dark Theme", uiMode = android.content.res.Configuration.UI_MODE_NIGHT_YES)
@Composable
fun PreviewHeaderTitleBar() {
    HeaderTitleBar(
        header = Header(
            image = 0,
            title = "Chocolate Cake",
            servings = 8,
            prepTime = 60,
            description = "A delicious chocolate cake recipe."
        ),
        isExpanded = true,
        setIsExpanded = { },
        modifier = Modifier
    )
}
