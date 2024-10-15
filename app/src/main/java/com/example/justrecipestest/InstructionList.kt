package com.example.justrecipestest

import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember

@Composable
fun InstructionListStateful(instructions: List<Instruction>) {
    val instructionStates = remember { instructions.toMutableList() }

    InstructionList(
        instructions = instructionStates,
        onCheckedChange = { index, checked ->
            instructionStates[index].isChecked = checked
        }
    )
}

@Composable
fun InstructionList(
    instructions: List<Instruction>,
    onCheckedChange: (Int, Boolean) -> Unit
) {
    LazyColumn {
        itemsIndexed(instructions) { index, instruction ->
            InstructionStateless(
                checked = instruction.isChecked,
                onCheckedChange = { onCheckedChange(index, it) },
                instructionText = instruction.name
            )
        }
    }
}

data class Instruction(
    val name: String,
    var isChecked: Boolean
)
