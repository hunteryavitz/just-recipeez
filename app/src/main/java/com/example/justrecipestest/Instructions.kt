package com.example.justrecipestest

import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.justrecipestest.ui.theme.JustRecipesTestTheme

@Composable
fun Instructions(
    instructions: List<Instruction>,
    onCheckedChange: (Int, Boolean) -> Unit,
    onCollapseInstructionsHeaderClicked: () -> Unit,
    onFullScreenInstructionsClicked: () -> Unit,
    modifier: Modifier = Modifier
) {

    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = modifier
            .border(3.dp, MaterialTheme.colorScheme.onSurface.copy(alpha = 0.5f), RoundedCornerShape(8.dp))
    ) {
        InstructionsHeader(
            onCollapseInstructionsHeaderClicked = { onCollapseInstructionsHeaderClicked() },
            onFullScreenInstructionsClicked = onFullScreenInstructionsClicked
        )
        InstructionListStateless(
            instructions = instructions,
            onCheckedChange = onCheckedChange
        )
//        AnimatedVisibility(
//            visible = isExpanded,
//            enter = expandVertically(animationSpec = spring()),
//            exit = shrinkVertically(animationSpec = spring())
//        ) {
//        }
    }
}

@Preview
@Preview(showBackground = true, backgroundColor = 0xFFFFFFFF)
@Composable
private fun InstructionsPreview() {
    JustRecipesTestTheme {
        Instructions(
            instructions = listOf(
                Instruction("Preheat oven to 350°F", false),
                Instruction("Mix flour, sugar, and butter", false),
                Instruction("Add milk and chocolate", false),
                Instruction("Bake for 30 minutes", false),
                Instruction("Enjoy!", false)
            ),
            onCheckedChange = { _, _ -> },
            onCollapseInstructionsHeaderClicked = { },
            onFullScreenInstructionsClicked = { }
        )
    }
}
