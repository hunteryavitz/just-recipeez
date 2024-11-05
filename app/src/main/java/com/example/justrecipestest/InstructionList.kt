package com.example.justrecipestest

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.saveable.listSaver
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@Composable
fun InstructionListStateful(instructions: List<Instruction>) {
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
@Composable
fun InstructionListPreview() {
    InstructionListStateful(
        instructions = listOf(
            Instruction("Preheat oven to 350°F", false),
            Instruction("Mix dry ingredients", false),
            Instruction("Mix wet ingredients", false),
            Instruction("Combine wet and dry ingredients", false),
            Instruction("Pour into pan", false),
            Instruction("Bake for 30 minutes", false),
            Instruction("Cool and serve", false),
        )
    )
}
