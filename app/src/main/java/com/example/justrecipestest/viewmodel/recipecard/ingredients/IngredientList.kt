package com.example.justrecipestest.viewmodel.recipecard.ingredients

import android.content.res.Configuration
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.saveable.listSaver
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.tooling.preview.Preview
import com.example.justrecipestest.data.model.Ingredient
import com.example.justrecipestest.ui.components.recipecard.ingredients.IngredientListStateless

@Composable
fun IngredientList(ingredients: List<Ingredient>) {
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

@Preview
@Preview(showBackground = true, name = "Recipe Card Dark", uiMode = Configuration.UI_MODE_NIGHT_YES)
@Composable
private fun IngredientListPreview() {
    IngredientList(
        ingredients = listOf(
            Ingredient("1 cup Flour", false),
            Ingredient("1 cup Sugar", false),
            Ingredient("1 cup Butter", false),
        )
    )
}
