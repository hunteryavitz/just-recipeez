package com.example.justrecipestest.ui.components.recipecard.instructions

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.justrecipestest.ui.components.recipecard.common.ListHeader
import com.example.justrecipestest.ui.theme.JustRecipesTestTheme

@Composable
fun InstructionsHeader(
    onCollapseInstructionsHeaderClicked: () -> Unit,
    onFullScreenInstructionsClicked: () -> Unit,
    modifier: Modifier = Modifier
) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = modifier
            .padding(12.dp)
            .fillMaxWidth()
    ) {
        ListHeader(
            title = "Instructions",
            onCollapseListClicked = onCollapseInstructionsHeaderClicked,
            onFullScreenListClicked = onFullScreenInstructionsClicked
        )
    }
}

@Preview
@Preview(showBackground = true, backgroundColor = 0xFFFFFFFF)
@Composable
private fun InstructionsHeaderPreview() {
    JustRecipesTestTheme {
        InstructionsHeader(
            onCollapseInstructionsHeaderClicked = { },
            onFullScreenInstructionsClicked = { }
        )
    }
}
