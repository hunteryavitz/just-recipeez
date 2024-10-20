package com.example.justrecipestest

import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.saveable.listSaver
import androidx.compose.runtime.saveable.rememberSaveable

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
    LazyColumn {
        itemsIndexed(instructions) { index, instruction ->
            InstructionStateless(
                checked = instruction.isChecked,
                instructionText = instruction.name,
                onCheckedChange = { onCheckedChange(index, it) }
            )
        }
    }
}
