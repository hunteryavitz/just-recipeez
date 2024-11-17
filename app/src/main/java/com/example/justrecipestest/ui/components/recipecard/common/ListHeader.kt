package com.example.justrecipestest.ui.components.recipecard.common

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material.icons.filled.KeyboardArrowUp
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.justrecipestest.R
import com.example.justrecipestest.ui.theme.JustRecipesTestTheme

val fontFamilyDancingScript = FontFamily(
    Font(R.font.dancing_script, FontWeight.Bold),
)

@Composable
fun ListHeader(
    title: String,
    onCollapseListClicked: () -> Unit,
    onFullScreenListClicked: () -> Unit
) {
    Row(
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier.fillMaxWidth()
    ) {
        Column {
            IconButton(
                onClick = { }
            ) {
                Icon(
                    imageVector = Icons.Default.KeyboardArrowUp,
                    contentDescription = "Expand Card",
                    tint = Color.Black,
                    modifier = Modifier
                        .size(32.dp)
                        .clickable(onClick = onCollapseListClicked)
                )
            }
        }
        Column {
            Text(
                text = title,
                color = Color.Black,
                fontSize = 24.sp,
                fontFamily = fontFamilyDancingScript
            )
        }
        Column {
            IconButton(
                onClick = { }
            ) {
                Icon(
                    imageVector = Icons.Default.KeyboardArrowDown,
                    contentDescription = "Expand Card",
                    tint = Color.Black,
                    modifier = Modifier
                        .size(32.dp)
                        .clickable(onClick = onFullScreenListClicked)
                )
            }
        }
    }
}

@Preview
@Preview(showBackground = true, backgroundColor = 0xFFFFFFFF)
@Composable
public fun ListHeaderPreview() {
    JustRecipesTestTheme {
        ListHeader(
            title = "List Header",
            onCollapseListClicked = { },
            onFullScreenListClicked = { }
        )
    }
}
