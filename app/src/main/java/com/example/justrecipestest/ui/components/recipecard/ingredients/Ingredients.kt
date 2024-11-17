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
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.justrecipestest.data.model.Ingredient
import com.example.justrecipestest.ui.theme.JustRecipesTestTheme

@Composable
fun Ingredients(
    ingredients: List<Ingredient>,
    onCheckedChange: (Int, Boolean) -> Unit,
    onCollapseIngredientsList: () -> Unit,
    onFullScreenIngredientsClicked: () -> Unit,
    modifier: Modifier = Modifier
) {
    val (isExpanded, setIsExpanded) = remember { mutableStateOf(true) }


    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = modifier
            .border(3.dp, MaterialTheme.colorScheme.onSurface.copy(alpha = 0.5f), RoundedCornerShape(8.dp))
    ) {
        IngredientsHeader(
            onCollapseIngredientsListClicked = {
                setIsExpanded(!isExpanded)
                onCollapseIngredientsList()
                                               },
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
@Preview(showBackground = true, backgroundColor = 0xFFFFFFFF)
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
            onCollapseIngredientsList = { },
            onFullScreenIngredientsClicked = { }
        )
    }
}
