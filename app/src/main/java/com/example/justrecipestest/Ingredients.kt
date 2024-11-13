package com.example.justrecipestest

import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.justrecipestest.ui.theme.JustRecipesTestTheme

@Composable
fun Ingredients(
    ingredients: List<Ingredient>,
    onCheckedChange: (Int, Boolean) -> Unit,
    onCollapseIngredientsListClicked: () -> Unit,
    onFullScreenIngredientsClicked: () -> Unit,
    modifier: Modifier = Modifier
) {

    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = modifier
            .border(3.dp, MaterialTheme.colorScheme.onSurface.copy(alpha = 0.5f), RoundedCornerShape(8.dp))
    ) {
        IngredientsHeader(
            onCollapseIngredientsListClicked = { onCollapseIngredientsListClicked() },
            onFullScreenIngredientsClicked = onFullScreenIngredientsClicked
        )
        IngredientListStateless(
            ingredients = ingredients,
            onCheckedChange = onCheckedChange
        )
    }
}

@Preview
@Preview(showBackground = true, backgroundColor = 0xFFFFFFFF)
@Composable
private fun IngredientsPreview() {
    JustRecipesTestTheme {
        Ingredients(
            ingredients = listOf(
                Ingredient("1 cup Flour", false),
                Ingredient("1 cup Sugar", false),
                Ingredient("1 cup Butter", false),
                Ingredient("1 cup Milk", false),
                Ingredient("1 cup Chocolate", false)
            ),
            onCheckedChange = { _, _ -> },
            onCollapseIngredientsListClicked = { },
            onFullScreenIngredientsClicked = { }
        )
    }
}
