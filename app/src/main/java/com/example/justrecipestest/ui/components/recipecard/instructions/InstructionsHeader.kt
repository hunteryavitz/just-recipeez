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
    isExpanded: Boolean,
    onCollapseInstructionsHeaderClicked: () -> Unit,
    isFullScreen: Boolean,
    onFullScreenInstructionsClicked: () -> Unit,
    modifier: Modifier = Modifier
) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = modifier
            .padding(8.dp)
            .fillMaxWidth()
    ) {
        ListHeader(
            title = "Instructions",
            isExpanded = isExpanded,
            onCollapseListClicked = onCollapseInstructionsHeaderClicked,
            isFullScreen = isFullScreen,
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
            isExpanded = false,
            onCollapseInstructionsHeaderClicked = { },
            isFullScreen = false,
            onFullScreenInstructionsClicked = { }
        )
    }
}
