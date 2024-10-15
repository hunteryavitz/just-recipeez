package com.example.justrecipestest

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.Checkbox
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.sp

@Composable
fun InstructionStateful(instructionText: String) {
    var isChecked by remember { mutableStateOf(false) }

    InstructionStateless(
        checked = isChecked,
        onCheckedChange = { isChecked = it },
        instructionText = instructionText
    )
}

@Composable
fun InstructionStateless(
    checked: Boolean,
    onCheckedChange: (Boolean) -> Unit,
    instructionText: String
) {
    Row(
        horizontalArrangement = Arrangement.Start,
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier.fillMaxWidth()
    ) {
        Column(
            horizontalAlignment = Alignment.End,
            verticalArrangement = Arrangement.Center,
            modifier = Modifier.weight(1f)
        ) {
            Checkbox(
                checked = checked,
                onCheckedChange = onCheckedChange,
            )
        }
        Column(
            horizontalAlignment = Alignment.Start,
            verticalArrangement = Arrangement.Center,
            modifier = Modifier.weight(2f)
        ) {
            Text(
                text = instructionText,
                fontSize = 12.sp,
            )
        }
    }
}
