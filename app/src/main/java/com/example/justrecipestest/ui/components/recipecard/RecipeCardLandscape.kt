package com.example.justrecipestest.ui.components.recipecard

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.justrecipestest.R
import com.example.justrecipestest.data.model.Ingredient
import com.example.justrecipestest.data.model.Instruction
import com.example.justrecipestest.data.model.Recipe
import com.example.justrecipestest.ui.components.recipecard.header.Header
import com.example.justrecipestest.ui.components.recipecard.ingredients.Ingredients
import com.example.justrecipestest.ui.components.recipecard.instructions.Instructions
import com.example.justrecipestest.ui.theme.JustRecipesTestTheme

@Composable
fun RecipeCardLandscape(
    recipe: Recipe,
    ingredients: List<Ingredient>,
    onIngredientsCheckedChange: (Int, Boolean) -> Unit,
    instructions: List<Instruction>,
    onInstructionsCheckedChange: (Int, Boolean) -> Unit,
) {
    val header =
        Header(recipe.image, recipe.title, recipe.servings, recipe.prepTime, recipe.description)

    Box(
        modifier = Modifier
            .fillMaxSize()
    ) {
        Image(
            painter = painterResource(id = R.drawable.background_image_01),
            contentDescription = null,
            contentScale = ContentScale.Crop,
            modifier = Modifier.matchParentSize()
        )
        Box(
            modifier = Modifier
                .matchParentSize()
                .background(Color.White.copy(alpha = 0.7f))
        )
        Row(
            modifier = Modifier
                .fillMaxSize()
                .padding(top = 24.dp, start = 24.dp)
        ) {
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                modifier = Modifier
                    .weight(.5f)
            ) {
                Row(
                    modifier = Modifier
                        .padding(4.dp)
                ) {
                    Header(
                        header = header,
                        setIsFavorite = {},
                        isFavorite = false,
                        modifier = Modifier
                    )
                }
            }
            Column(
                modifier = Modifier
                    .weight(1f)
            ) {
                Row(
                    modifier = Modifier
                        .weight(1f)
                        .padding(12.dp)
                ) {
                    Ingredients(
                        ingredients,
                        onCheckedChange = onIngredientsCheckedChange,
                        isExpanded = true,
                        onCollapseIngredientsList = { },
                        isFullScreen = false,
                        onFullScreenIngredientsClicked = { },
                        modifier = Modifier)
                }
            }
            Column(
                modifier = Modifier
                    .weight(1f)
            ) {
                Row(
                    modifier = Modifier
                        .weight(1f)
                        .padding(12.dp)
                ) {
                    Instructions(
                        instructions,
                        onCheckedChange = onInstructionsCheckedChange,
                        isExpanded = true,
                        onCollapseInstructionsList = { },
                        isFullScreen = false,
                        onFullScreenInstructionsClicked = { },
                        modifier = Modifier
                    )
                }
            }
        }
    }
}

@Preview
@Preview(showBackground = true, backgroundColor = 0xFFFFFFFF)
@Composable
private fun RecipeCardLandscapePreview() {
    JustRecipesTestTheme {
        RecipeCardLandscape(
            recipe = Recipe(
                title = "Recipe Title",
                servings = 4,
                prepTime = 30,
                description = "Recipe Description",
                isFavorite = false,
                ingredients = listOf(
                    Ingredient("Ingredient 1", false),
                    Ingredient("Ingredient 2", false),
                    Ingredient("Ingredient 3", false),
                    Ingredient("Ingredient 4", false),
                    Ingredient("Ingredient 5", false)
                ),
                instructions = listOf(
                    Instruction("Instruction 1", false),
                    Instruction("Instruction 2", false),
                    Instruction("Instruction 3", false),
                    Instruction("Instruction 4", false),
                    Instruction("Instruction 5", false)
                ),
                image = R.drawable.header_02
            ),
            ingredients = listOf(
                Ingredient("Ingredient 1", false),
                Ingredient("Ingredient 2", false),
                Ingredient("Ingredient 3", false),
                Ingredient("Ingredient 4", false),
                Ingredient("Ingredient 5", false)
            ),
            onIngredientsCheckedChange = { _, _ -> },
            instructions = listOf(
                Instruction("Instruction 1", false),
                Instruction("Instruction 2", false),
                Instruction("Instruction 3", false),
                Instruction("Instruction 4", false),
                Instruction("Instruction 5", false)
            ),
            onInstructionsCheckedChange = { _, _ -> }
        )
    }
}
