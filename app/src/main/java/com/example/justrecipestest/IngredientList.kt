package com.example.justrecipestest

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.safeContentPadding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.saveable.listSaver
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

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
    onCheckedChange: (Int, Boolean) -> Unit,
    modifier: Modifier = Modifier
) {
    LazyColumn(modifier = Modifier.heightIn(max = 400.dp),
        contentPadding = PaddingValues(vertical = 16.dp),
        content = {
            itemsIndexed(ingredients) { index, ingredient ->
                IngredientStateless(
                    checked = ingredient.isChecked,
                    ingredientText = ingredient.name,
                    onCheckedChange = { onCheckedChange(index, it) }
                )
            }
        }
    )
}

@Preview
@Composable
fun IngredientListPreview() {
    IngredientListStateful(
        ingredients = listOf(
            Ingredient("1 cup flour", false),
            Ingredient("1/2 cup sugar", false),
            Ingredient("1/4 cup cocoa powder", false),
            Ingredient("1/2 tsp baking powder", false),
            Ingredient("1/4 tsp salt", false),
            Ingredient("1/2 cup milk", false),
            Ingredient("1/4 cup vegetable oil", false),
            Ingredient("1/2 tsp vanilla extract", false),
            Ingredient("1/2 cup chocolate chips", false),
            Ingredient("1/2 cup chopped nuts", false),
            Ingredient("1/2 cup shredded coconut", false),
            Ingredient("1/2 cup raisins", false),
            Ingredient("1/2 cup dried cranberries", false),
            Ingredient("1/2 cup dried cherries", false),
            Ingredient("1/2 cup dried apricots", false)
        )
    )
}
