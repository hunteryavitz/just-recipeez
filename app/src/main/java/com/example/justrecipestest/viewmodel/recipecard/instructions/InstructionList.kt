package com.example.justrecipestest.viewmodel.recipecard.instructions

import android.content.res.Configuration
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.saveable.listSaver
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.tooling.preview.Preview
import com.example.justrecipestest.data.model.Instruction
import com.example.justrecipestest.ui.components.recipecard.instructions.InstructionListStateless

@Composable
fun InstructionList(instructions: List<Instruction>) {
    val instructionStates = rememberSaveable(
        saver = listSaver(
            save = { stateList -> stateList.map { listOf(it.name, it.isChecked) } },
            restore = { savedList ->
                mutableStateListOf(*savedList.map { Instruction(it[0] as String, it[1] as Boolean) }.toTypedArray())
            }
        )
    ) { mutableStateListOf(*instructions.map { it.copy() }.toTypedArray()) }

    InstructionListStateless (
        instructions = instructionStates,
        onCheckedChange = { index, checked ->
            instructionStates[index] = instructionStates[index].copy(isChecked = checked)
        }
    )
}

@Preview
@Preview(showBackground = true, name = "Recipe Card Dark", uiMode = Configuration.UI_MODE_NIGHT_YES)
@Composable
private fun InstructionListPreview() {
    InstructionList(
        instructions = listOf(
            Instruction("Preheat oven to 350°F", false),
            Instruction("Mix flour and sugar", false),
            Instruction("Bake for 30 minutes", false),
        )
    )
}
