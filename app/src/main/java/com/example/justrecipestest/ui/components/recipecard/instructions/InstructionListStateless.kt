package com.example.justrecipestest.ui.components.recipecard.instructions

import android.content.res.Configuration
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.justrecipestest.data.model.Instruction

@Composable
fun InstructionListStateless(
    instructions: List<Instruction>,
    onCheckedChange: (Int, Boolean) -> Unit
) {
    LazyColumn(
        contentPadding = PaddingValues(vertical = 8.dp),
        content = {
            itemsIndexed(instructions) { index, instruction ->
                InstructionStateless(
                    checked = instruction.isChecked,
                    instructionText = instruction.name,
                    onCheckedChange = { onCheckedChange(index, it) }
                )
            }
        }
    )
}

@Preview
@Preview(showBackground = true, name = "Recipe Card Dark", uiMode = Configuration.UI_MODE_NIGHT_YES)
@Composable
private fun InstructionListStatelessPreview() {
    InstructionListStateless(
        instructions = listOf(
            Instruction("Preheat oven to 350°F", false),
            Instruction("Mix flour and sugar", false),
            Instruction("Bake for 30 minutes", false),
        ),
        onCheckedChange = { _, _ -> }
    )
}
