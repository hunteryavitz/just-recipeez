package com.example.justrecipestest

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Checkbox
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun InstructionStateful(
    instruction: Instruction,
    onInstructionCheckedChanged: (Instruction) -> Unit
) {
    var isChecked by remember { mutableStateOf(instruction.isChecked) }

    InstructionStateless(
        checked = isChecked,
        instructionText = instruction.name,
        onCheckedChange = {
            isChecked = it
            onInstructionCheckedChanged(instruction.copy(isChecked = it))
        }
    )
}

@Composable
fun InstructionStateless(
    checked: Boolean,
    instructionText: String,
    onCheckedChange: (Boolean) -> Unit
) {
    Row(
        horizontalArrangement = Arrangement.Start,
        modifier = Modifier
            .fillMaxWidth()
    ) {
        Column(
            horizontalAlignment = Alignment.End,
            modifier = Modifier
                .weight(1f)
        ) {
            Checkbox(
                checked = checked,
                onCheckedChange = onCheckedChange
            )
        }
        Column(
            horizontalAlignment = Alignment.Start,
            modifier = Modifier
                .weight(4f)
                .padding(12.dp)
        ) {
            Text(
                text = instructionText,
                fontSize = 16.sp
            )
        }
    }
}

data class Instruction(
    val name: String,
    val isChecked: Boolean
)

@Preview
@Composable
fun InstructionPreview() {
    InstructionStateful(
        instruction = Instruction("Preheat oven to 350°F and do a bunch of other stuff while you're waiting, then keep doing more stuff and don't stop doing things so this gets really big.", false),
        onInstructionCheckedChanged = {}
    )
}
