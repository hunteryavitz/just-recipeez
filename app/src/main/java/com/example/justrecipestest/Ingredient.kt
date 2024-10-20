package com.example.justrecipestest

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.Checkbox
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.sp

@Composable
fun IngredientStateful(
    ingredient: Ingredient,
    onIngredientCheckedChanged: (Ingredient) -> Unit
) {
    var isChecked by rememberSaveable { mutableStateOf(ingredient.isChecked) }

    IngredientStateless(
        checked = isChecked,
        ingredientText = ingredient.name,
        onCheckedChange = {
            isChecked = it
            onIngredientCheckedChanged(ingredient.copy(isChecked = it))
        }
    )
}

@Composable
fun IngredientStateless(
    checked: Boolean,
    ingredientText: String,
    onCheckedChange: (Boolean) -> Unit
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
                text = ingredientText,
                fontSize = 12.sp,
            )
        }
    }
}

data class Ingredient(
    val name: String,
    val isChecked: Boolean
)
