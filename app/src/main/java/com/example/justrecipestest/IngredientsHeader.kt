package com.example.justrecipestest

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.justrecipestest.ui.theme.JustRecipesTestTheme

@Composable
fun IngredientsHeader(
    onCollapseIngredientsListClicked: () -> Unit,
    onFullScreenIngredientsClicked: () -> Unit,
    modifier: Modifier = Modifier
) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = modifier
            .padding(8.dp)
            .fillMaxWidth()
    ) {
        ListHeader(
            title = "Ingredients",
            onCollapseListClicked = onCollapseIngredientsListClicked,
            onFullScreenListClicked = onFullScreenIngredientsClicked
        )
    }
}

@Preview
@Preview(showBackground = true, backgroundColor = 0xFFFFFFFF)
@Composable
private fun IngredientsHeaderPreview() {
    JustRecipesTestTheme {
        IngredientsHeader(
            onCollapseIngredientsListClicked = { },
            onFullScreenIngredientsClicked = { }
        )
    }
}