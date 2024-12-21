package com.example.justrecipestest.ui.components.recipecard

import android.content.res.Configuration
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.justrecipestest.R
import com.example.justrecipestest.data.model.Ingredient
import com.example.justrecipestest.data.model.Instruction
import com.example.justrecipestest.data.model.Recipe
import com.example.justrecipestest.ui.components.recipecard.header.Header
import com.example.justrecipestest.ui.components.recipecard.header.HeaderLandscape
import com.example.justrecipestest.ui.components.recipecard.ingredients.Ingredients
import com.example.justrecipestest.ui.components.recipecard.instructions.Instructions
import com.example.justrecipestest.ui.theme.JustRecipesTestTheme

@Composable
fun RecipeCardLandscape(
    recipe: Recipe,
    setIsFavorite: (Boolean) -> Unit,
    isFavorite: Boolean,
    ingredients: List<Ingredient>,
    onIngredientsCheckedChange: (Int, Boolean) -> Unit,
    instructions: List<Instruction>,
    onInstructionsCheckedChange: (Int, Boolean) -> Unit,
) {
    val header = Header(
        recipe.image,
        recipe.title,
        recipe.servings,
        recipe.prepTime,
        recipe.description
    )
    val configuration = LocalConfiguration.current
//    val (isHeaderExpanded, setIsHeaderExpanded) = remember { mutableStateOf(configuration.orientation == Configuration.ORIENTATION_PORTRAIT) }
    val (isHeaderExpanded, setIsHeaderExpanded) = remember { mutableStateOf(true) }

//    val (isHeaderExpanded, setIsHeaderExpanded) = remember { mutableStateOf(false) }
    val (isCollapsedIngredientsList, setIsCollapsedIngredientsList) = remember { mutableStateOf(false) }
    val ingredientsListWeight by animateFloatAsState(
        targetValue = if (isCollapsedIngredientsList) 0.1f else 0.5f,
        label = ""
    )
    val (isFullScreenIngredients, setIsFullScreenIngredients) = remember { mutableStateOf(false) }
    val (isCollapsedInstructionsList, setIsCollapsedInstructionsList) = remember { mutableStateOf(false) }
    val instructionsListWeight by animateFloatAsState(
        targetValue = if (isCollapsedInstructionsList) 0.1f else 0.5f,
        label = ""
    )
    val (isFullScreenInstructions, setIsFullScreenInstructions) = remember { mutableStateOf(false) }

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
        if (isHeaderExpanded) {
            Row {
                HeaderLandscape(
                    header = header,
                    isExpanded = true,
                    setIsExpanded = { setIsHeaderExpanded(false) },
                    isFavorite = isFavorite,
                    setIsFavorite = setIsFavorite,
                    modifier = Modifier
//                        .fillMaxWidth()
//                        .fillMaxSize()
                )
            }
        } else {
            Row {
                HeaderLandscape(
                    header = header,
                    isExpanded = false,
                    setIsExpanded = { setIsHeaderExpanded(true) },
                    isFavorite = isFavorite,
                    setIsFavorite = setIsFavorite,
                    modifier = Modifier
                        .fillMaxWidth()
                )
            }
            Row(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(top = 54.dp)
            ) {
                Column(
                    modifier = Modifier
                        .weight(0.5f)
                ) {
                    Row(
                        modifier = Modifier
                            .weight(1f)
                            .padding(12.dp)
                    ) {
                        Ingredients(
                            ingredients,
                            onCheckedChange = onIngredientsCheckedChange,
                            isExpanded = !isCollapsedIngredientsList,
                            onCollapseIngredientsList = { setIsCollapsedIngredientsList(!isCollapsedIngredientsList) },
                            isFullScreen = false,
                            onFullScreenIngredientsClicked = { setIsFullScreenIngredients(true) },
                            modifier = Modifier)
                    }
                }
                Column(
                    modifier = Modifier
                        .weight(0.5f)
                ) {
                    Row(
                        modifier = Modifier
                            .weight(1f)
                            .padding(12.dp)
                    ) {
                        Instructions(
                            instructions = instructions,
                            onCheckedChange = onInstructionsCheckedChange,
                            isExpanded = !isCollapsedInstructionsList,
                            onCollapseInstructionsList = { setIsCollapsedInstructionsList(!isCollapsedInstructionsList) },
                            isFullScreen = false,
                            onFullScreenInstructionsClicked = { setIsFullScreenInstructions(true) },
                            modifier = Modifier
                        )
                    }
                }
            }
        }
    }
}

@Preview("Light Theme", widthDp = 800, heightDp = 400)
@Preview("Dark Theme", widthDp = 800, heightDp = 400, uiMode = android.content.res.Configuration.UI_MODE_NIGHT_YES)
@Composable
private fun RecipeCardLandscapePreview() {
    JustRecipesTestTheme {
        RecipeCardLandscape(
            recipe = Recipe(
                title = "Roasted Meat and Vegetables",
                servings = 4,
                prepTime = 30,
                description = "A delicious and healthy meal that is easy to make.",
                isFavorite = false,
                ingredients = listOf(
                    Ingredient("Beef", false),
                    Ingredient("Potatoes", false),
                    Ingredient("Carrots", false),
                    Ingredient("Onions", false),
                    Ingredient("Garlic", false)
                ),
                instructions = listOf(
                    Instruction("Preheat oven to 400°F", false),
                    Instruction("Chop vegetables", false),
                    Instruction("Season meat and vegetables", false),
                    Instruction("Roast for 30 minutes", false),
                    Instruction("Enjoy!", false)
                ),
                image = R.drawable.header_03
            ),
            setIsFavorite = { },
            isFavorite = false,
            ingredients = listOf(
                Ingredient("Beef", false),
                Ingredient("Potatoes", false),
                Ingredient("Carrots", false),
                Ingredient("Onions", false),
                Ingredient("Garlic", false)
            ),
            onIngredientsCheckedChange = { _, _ -> },
            instructions = listOf(
                Instruction("Preheat oven to 400°F", false),
                Instruction("Chop vegetables", false),
                Instruction("Season meat and vegetables", false),
                Instruction("Roast for 30 minutes", false),
                Instruction("Enjoy!", false)
            ),
            onInstructionsCheckedChange = { _, _ -> }
        )
    }
}
