package com.example.justrecipestest.ui.components.recipecard.ingredients

import android.content.res.Configuration
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.justrecipestest.data.model.Ingredient

@Composable
fun IngredientListStateless(
    ingredients: List<Ingredient>,
    onCheckedChange: (Int, Boolean) -> Unit
) {
    LazyColumn(
        contentPadding = PaddingValues(vertical = 8.dp),
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
@Preview(showBackground = true, name = "Recipe Card Dark", uiMode = Configuration.UI_MODE_NIGHT_YES)
@Composable
private fun IngredientListStatelessPreview() {
    IngredientListStateless(
        ingredients = listOf(
            Ingredient("1 cup Flour", false),
            Ingredient("1 cup Sugar", false),
            Ingredient("1 cup Butter", false),
        ),
        onCheckedChange = { _, _ -> }
    )
}
