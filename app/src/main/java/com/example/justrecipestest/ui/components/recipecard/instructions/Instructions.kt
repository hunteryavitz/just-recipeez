package com.example.justrecipestest.ui.components.recipecard.instructions

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.spring
import androidx.compose.animation.expandVertically
import androidx.compose.animation.shrinkVertically
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.justrecipestest.data.model.Instruction
import com.example.justrecipestest.ui.theme.JustRecipesTestTheme

@Composable
fun Instructions(
    instructions: List<Instruction>,
    onCheckedChange: (Int, Boolean) -> Unit,
    isExpanded: Boolean,
    onCollapseInstructionsList: () -> Unit,
    isFullScreen: Boolean,
    onFullScreenInstructionsClicked: () -> Unit,
    modifier: Modifier = Modifier
) {

    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = modifier
            .border(1.dp, Color.Black.copy(0.5f), RoundedCornerShape(8.dp))
    ) {
        InstructionsHeader(
            isExpanded = isExpanded,
            onCollapseInstructionsHeaderClicked = { onCollapseInstructionsList() },
            isFullScreen = isFullScreen,
            onFullScreenInstructionsClicked = onFullScreenInstructionsClicked
        )
        AnimatedVisibility(
            visible = isExpanded,
            enter = expandVertically(animationSpec = spring()),
            exit = shrinkVertically(animationSpec = spring())
        ) {
            InstructionListStateless(
                instructions = instructions,
                onCheckedChange = onCheckedChange
            )
        }
    }
}

@Preview
@Preview("Dark Theme", uiMode = android.content.res.Configuration.UI_MODE_NIGHT_YES)
@Composable
private fun InstructionsPreview() {
    JustRecipesTestTheme {
        Instructions(
            instructions = listOf(
                Instruction("Preheat oven to 350°F", false),
                Instruction("Mix flour, sugar, and butter", false),
                Instruction("Add milk and chocolate", false),
            ),
            onCheckedChange = { _, _ -> },
            isExpanded = true,
            onCollapseInstructionsList = { },
            isFullScreen = true,
            onFullScreenInstructionsClicked = { }
        )
    }
}
