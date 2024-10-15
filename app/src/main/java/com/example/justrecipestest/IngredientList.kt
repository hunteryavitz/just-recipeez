package com.example.justrecipestest

import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.remember

@Composable
fun IngredientListStateful(ingredients: List<Ingredient>) {
    val ingredientStates = remember { mutableStateListOf(*ingredients.toTypedArray()) }

    IngredientList(
        ingredients = ingredientStates,
        onCheckedChange = { index, checked ->
            val updatedIngredient = ingredientStates[index].copy(isChecked = checked)
            ingredientStates[index] = updatedIngredient
        }
    )
}

@Composable
fun IngredientList(
    ingredients: List<Ingredient>,
    onCheckedChange: (Int, Boolean) -> Unit
) {
    LazyColumn {
        itemsIndexed(ingredients) { index, ingredient ->
            IngredientStateless(
                checked = ingredient.isChecked,
                onCheckedChange = { onCheckedChange(index, it) },
                ingredientText = ingredient.name
            )
        }
    }
}

data class Ingredient(
    val name: String,
    val isChecked: Boolean
)
