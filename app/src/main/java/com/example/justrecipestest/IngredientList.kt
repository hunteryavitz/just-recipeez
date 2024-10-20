package com.example.justrecipestest

import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.saveable.listSaver
import androidx.compose.runtime.saveable.rememberSaveable

@Composable
fun IngredientListStateful(ingredients: List<Ingredient>) {
    val ingredientStates = rememberSaveable(
        saver = listSaver(
            save = { stateList -> stateList.map { listOf(it.name, it.isChecked) } },
            restore = { savedList ->
                mutableStateListOf(*savedList.map { Ingredient(it[0] as String, it[1] as Boolean) }.toTypedArray())
            }
        )
    ) { mutableStateListOf(*ingredients.toTypedArray()) }

    IngredientListStateless (
        ingredients = ingredientStates,
        onCheckedChange = { index, checked ->
            ingredientStates[index] = ingredientStates[index].copy(isChecked = checked)
        }
    )
}

@Composable
fun IngredientListStateless(
    ingredients: List<Ingredient>,
    onCheckedChange: (Int, Boolean) -> Unit
) {
    LazyColumn {
        itemsIndexed(ingredients) { index, ingredient ->
            IngredientStateless(
                checked = ingredient.isChecked,
                ingredientText = ingredient.name,
                onCheckedChange = { onCheckedChange(index, it) }
            )
        }
    }
}
