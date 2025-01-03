package com.example.justrecipestest.ui.components.recipecard.ingredients

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.spring
import androidx.compose.animation.expandVertically
import androidx.compose.animation.shrinkVertically
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.justrecipestest.data.model.Ingredient
import com.example.justrecipestest.ui.theme.JustRecipesTestTheme

@Composable
fun Ingredients(
    ingredients: List<Ingredient>,
    onCheckedChange: (Int, Boolean) -> Unit,
    isExpanded: Boolean,
    onCollapseIngredientsList: () -> Unit,
    isFullScreen: Boolean,
    onFullScreenIngredientsClicked: () -> Unit,
    modifier: Modifier = Modifier
) {

    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = modifier
            .border(1.dp, Color.Black.copy(alpha = 0.5f), RoundedCornerShape(8.dp))
    ) {
        IngredientsHeader(
            isExpanded = isExpanded,
            onCollapseIngredientsListClicked = { onCollapseIngredientsList() },
            isFullScreen = isFullScreen,
            onFullScreenIngredientsClicked = onFullScreenIngredientsClicked
        )
        AnimatedVisibility(
            visible = isExpanded,
            enter = expandVertically(animationSpec = spring()),
            exit = shrinkVertically(animationSpec = spring())
        ) {
            IngredientListStateless(
                ingredients = ingredients,
                onCheckedChange = onCheckedChange
            )
        }
    }
}

@Preview
@Preview("Dark Theme", uiMode = android.content.res.Configuration.UI_MODE_NIGHT_YES)
@Composable
private fun IngredientsPreview() {
    JustRecipesTestTheme {
        Ingredients(
            ingredients = listOf(
                Ingredient("1 cup Flour", false),
                Ingredient("1 cup Sugar", false),
                Ingredient("1 cup Butter", false),
            ),
            onCheckedChange = { _, _ -> },
            isExpanded = true,
            onCollapseIngredientsList = { },
            isFullScreen = false,
            onFullScreenIngredientsClicked = { }
        )
    }
}
