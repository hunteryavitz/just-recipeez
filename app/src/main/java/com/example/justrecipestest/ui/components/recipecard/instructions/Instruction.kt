package com.example.justrecipestest.ui.components.recipecard.instructions

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Checkbox
import androidx.compose.material3.CheckboxDefaults
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
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import com.example.justrecipestest.R
import com.example.justrecipestest.data.model.Instruction

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
    onCheckedChange: (Boolean) -> Unit,
    modifier: Modifier = Modifier
) {
    val fontFamilyDancingScript = FontFamily(
        Font(R.font.dancing_script, FontWeight.Bold),
    )

    Row(
        horizontalArrangement = Arrangement.Start,
        verticalAlignment = Alignment.CenterVertically,
        modifier = modifier
            .fillMaxWidth()
    ) {
        Column(
            horizontalAlignment = Alignment.End,
            modifier = Modifier
                .weight(1f)
        ) {
            Checkbox(
                checked = checked,
                onCheckedChange = onCheckedChange,
                colors = CheckboxDefaults.colors(
                    checkmarkColor = Color.Black,
                    checkedColor = Color.Black,
                    uncheckedColor = Color.Black
                )
            )
        }
        Column(
            horizontalAlignment = Alignment.Start,
            modifier = Modifier
                .weight(4f)
                .padding(8.dp)
        ) {
            Text(
                text = instructionText,
                color = Color.Black,
                fontSize = 22.sp,
                fontFamily = fontFamilyDancingScript,
            )
        }
    }
}

@Preview
@Composable
fun InstructionPreview() {
    InstructionStateful(
        instruction = Instruction("Preheat oven to 350°F and do a bunch of other stuff while you're waiting, then keep doing more stuff and don't stop doing things so this gets really big.", false),
        onInstructionCheckedChanged = {}
    )
}
