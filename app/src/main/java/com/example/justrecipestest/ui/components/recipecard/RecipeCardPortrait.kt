package com.example.justrecipestest.ui.components.recipecard

import android.content.res.Configuration
import androidx.compose.animation.animateContentSize
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
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

@Composable
fun RecipeCardPortrait(
    recipe: Recipe,
    setIsFavorite: (Boolean) -> Unit,
    isFavorite: Boolean,
    ingredients: List<Ingredient>,
    onIngredientsCheckedChange: (Int, Boolean) -> Unit,
    instructions: List<Instruction>,
    onInstructionsCheckedChange: (Int, Boolean) -> Unit
) {
    val header = Header(
        recipe.image,
        recipe.title,
        recipe.servings,
        recipe.prepTime,
        recipe.description
    )

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
        Column(
            modifier = Modifier.padding(top = 28.dp)
        ) {
            if (!isFullScreenIngredients && !isFullScreenInstructions) {
                Header(
                    header = header,
                    setIsFavorite = setIsFavorite,
                    isFavorite = isFavorite,
                    modifier = Modifier
                        .fillMaxWidth()
                )
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .weight(1f)
                ) {
                    Ingredients(
                        ingredients = ingredients,
                        onCheckedChange = onIngredientsCheckedChange,
                        isExpanded = !isCollapsedIngredientsList,
                        onCollapseIngredientsList = { setIsCollapsedIngredientsList(!isCollapsedIngredientsList) },
                        onFullScreenIngredientsClicked = { setIsFullScreenIngredients(true) },
                        modifier = Modifier
                            .fillMaxWidth()
                            .weight(ingredientsListWeight)
                            .animateContentSize(animationSpec = tween(durationMillis = 300))
                    )
                    Spacer(modifier = Modifier.size(4.dp))
                    Instructions(
                        instructions = instructions,
                        onCheckedChange = onInstructionsCheckedChange,
                        isExpanded = !isCollapsedInstructionsList,
                        onCollapseInstructionsList = { setIsCollapsedInstructionsList(!isCollapsedInstructionsList) },
                        onFullScreenInstructionsClicked = { setIsFullScreenInstructions(true) },
                        modifier = Modifier
                            .fillMaxWidth()
                            .weight(instructionsListWeight)
                            .animateContentSize(animationSpec = tween(durationMillis = 300))
                    )
                    Spacer(modifier = Modifier
                        .size(4.dp)
                        .weight(
                            if (isCollapsedIngredientsList && isCollapsedInstructionsList) 0.8f else 0.1f
                        )
                    )
                }
            } else if (isFullScreenIngredients) {
                Ingredients(
                    ingredients = ingredients,
                    onCheckedChange = onIngredientsCheckedChange,
                    isExpanded = !isCollapsedIngredientsList,
                    onCollapseIngredientsList = { setIsCollapsedIngredientsList(false) },
                    onFullScreenIngredientsClicked = { setIsFullScreenIngredients(false) },
                    modifier = Modifier
                        .fillMaxWidth()
                        .fillMaxSize()
                )
            } else {
                Instructions(
                    instructions = instructions,
                    onCheckedChange = onInstructionsCheckedChange,
                    isExpanded = !isCollapsedInstructionsList,
                    onCollapseInstructionsList = { setIsCollapsedInstructionsList(false) },
                    onFullScreenInstructionsClicked = { setIsFullScreenInstructions(false) },
                    modifier = Modifier
                        .fillMaxWidth()
                        .fillMaxSize()
                )
            }
        }
    }
}

@Preview
@Preview("Dark Theme", uiMode = Configuration.UI_MODE_NIGHT_YES)
@Composable
fun PreviewRecipeCardPortrait() {
    RecipeCardPortrait(
        recipe = Recipe(
            image = R.drawable.header_02,
            title = "Chocolate Cake",
            servings = 4,
            prepTime = 30,
            description = "A delicious chocolate cake recipe.",
            isFavorite = false,
            ingredients = listOf(
                Ingredient("Flour", false),
                Ingredient("Sugar", false),
                Ingredient("Cocoa Powder", false),
                Ingredient("Baking Powder", false),
                Ingredient("Baking Soda", false),
            ),
            instructions = listOf(
                Instruction("Preheat oven to 350°F.", false),
                Instruction("Grease and flour two 9-inch round baking pans.", false),
                Instruction("Mix together flour, sugar, cocoa, baking powder, baking soda, and salt in a large bowl.", false),
                Instruction("Add eggs, milk, oil, and vanilla; beat on medium speed of mixer 2 minutes.", false),
                Instruction("Stir in boiling water (batter will be thin). Pour batter into prepared pans.", false),
            )
        ),
        setIsFavorite = {},
        isFavorite = false,
        ingredients = listOf(
            Ingredient("Flour", false),
            Ingredient("Sugar", false),
            Ingredient("Cocoa Powder", false),
            Ingredient("Baking Powder", false),
            Ingredient("Baking Soda", false),
        ),
        onIngredientsCheckedChange = { _, _ -> },
        instructions = listOf(
            Instruction("Preheat oven to 350°F.", false),
            Instruction("Grease and flour two 9-inch round baking pans.", false),
            Instruction("Mix together flour, sugar, cocoa, baking powder, baking soda, and salt in a large bowl.", false),
            Instruction("Add eggs, milk, oil, and vanilla; beat on medium speed of mixer 2 minutes.", false),
            Instruction("Stir in boiling water (batter will be thin). Pour batter into prepared pans.", false),
        ),
        onInstructionsCheckedChange = { _, _ -> }
        )
}
