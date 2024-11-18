package com.example.justrecipestest.ui.components.recipecard

import android.content.res.Configuration
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.saveable.listSaver
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.justrecipestest.R
import com.example.justrecipestest.data.model.Ingredient
import com.example.justrecipestest.data.model.Instruction
import com.example.justrecipestest.data.model.Recipe

val recipe = Recipe(
    image = R.drawable.header_02,
    title = "Chocolate Cake",
    servings = 8,
    prepTime = 60,
    description = "A delicious chocolate cake for all occasions",
    ingredients = listOf(
        Ingredient("Flour", false),
        Ingredient("Sugar", false),
        Ingredient("Cocoa Powder", false),
        Ingredient("Baking Powder", false),
        Ingredient("Baking Soda", false),
        Ingredient("Salt", false),
        Ingredient("Eggs", false),
        Ingredient("Milk", false),
    ),
    instructions = listOf(
        Instruction("Preheat oven to 350°F (180°C). Grease and flour two 9-inch round baking pans.", false),
        Instruction("Stir together sugar, flour, cocoa, baking powder, baking soda, and salt in large bowl.", false),
        Instruction("Add eggs, milk, oil, and vanilla; beat on medium speed of mixer 2 minutes.", false),
        Instruction("Stir in boiling water (batter will be thin). Pour batter into prepared pans.", false),
        Instruction("Bake 30 to 35 minutes or until wooden pick inserted in center comes out clean.", false),
        Instruction("Cool 10 minutes; remove from pans to wire racks. Cool completely.", false),
    )
)

@Composable
fun RecipeCard(modifier: PaddingValues, recipe: Recipe) {
    val configuration = LocalConfiguration.current

    val ingredientStates = rememberSaveable(
        saver = listSaver(
            save = { it.map { ingredient -> listOf(ingredient.name, ingredient.isChecked) } },
            restore = { savedList ->
                mutableStateListOf(*savedList.map { Ingredient(it[0] as String, it[1] as Boolean) }.toTypedArray())
            }
        )
    ) { mutableStateListOf(*recipe.ingredients.toTypedArray()) }

    val instructionStates = rememberSaveable(
        saver = listSaver(
            save = { it.map { instruction -> listOf(instruction.name, instruction.isChecked) } },
            restore = { savedList ->
                mutableStateListOf(*savedList.map { Instruction(it[0] as String, it[1] as Boolean) }.toTypedArray())
            }
        )
    ) { mutableStateListOf(*recipe.instructions.toTypedArray()) }

    when (configuration.orientation) {
        Configuration.ORIENTATION_PORTRAIT ->
            RecipeCardPortrait(
                recipe = recipe,
                ingredients = ingredientStates,
                onIngredientsCheckedChange = { index, checked ->
                    ingredientStates[index] = ingredientStates[index].copy(isChecked = checked)
                },
                instructions = instructionStates,
                onInstructionsCheckedChange = { index, checked ->
                    instructionStates[index] = instructionStates[index].copy(isChecked = checked)
                }
            )
        Configuration.ORIENTATION_LANDSCAPE ->
            RecipeCardLandscape(
                recipe,
                ingredients = ingredientStates,
                onIngredientsCheckedChange = { index, checked ->
                    ingredientStates[index] = ingredientStates[index].copy(isChecked = checked)
                },
                instructions = instructionStates,
                onInstructionsCheckedChange = { index, checked ->
                    instructionStates[index] = instructionStates[index].copy(isChecked = checked)
                }
            )
        else -> RecipeCardPortrait(
            recipe,
            ingredients = ingredientStates,
            onIngredientsCheckedChange = { index, checked ->
                ingredientStates[index] = ingredientStates[index].copy(isChecked = checked)
            },
            instructions = instructionStates,
            onInstructionsCheckedChange = { index, checked ->
                instructionStates[index] = instructionStates[index].copy(isChecked = checked)
            }
        )
    }
}

@Preview(showBackground = true, name = "Recipe Card Portrait", widthDp = 400, heightDp = 800)
@Composable
fun RecipeCardPreviewPortrait() {
    RecipeCard(PaddingValues(0.dp), recipe)
}

@Preview(showBackground = true, name = "Recipe Card Landscape", widthDp = 800, heightDp = 400)
@Composable
fun RecipeCardPreviewLandscape() {
    RecipeCard(PaddingValues(0.dp), recipe)
}

@Preview(showBackground = true, name = "Recipe Card Dark", uiMode = Configuration.UI_MODE_NIGHT_YES)
@Composable
fun RecipeCardPreviewDark() {
    RecipeCard(PaddingValues(0.dp), recipe)
}
