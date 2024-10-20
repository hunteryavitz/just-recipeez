package com.example.justrecipestest

import android.content.res.Configuration
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.spring
import androidx.compose.animation.expandVertically
import androidx.compose.animation.shrinkVertically
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.listSaver
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

data class Header(
    val image: Int,
    val title: String,
    val servings: Int,
    val prepTime: Int,
    val description: String
)

@Composable
private fun ImageHeader(image: Int) {
    Image(
        painter = painterResource(image),
        contentDescription = "Header",
        contentScale = ContentScale.Crop,
        modifier = Modifier
            .size(196.dp)
            .clip(RoundedCornerShape(10.dp))
    )
}

@Composable
private fun Title(title: String) {
    Text(
        text = title,
        fontSize = 16.sp,
        modifier = Modifier.padding(top = 8.dp)
    )
}

@Composable
private fun ServingsInfoSubtitle(servings: Int) {
    Text(
        text = "Serves: $servings",
        fontSize = 12.sp,
        modifier = Modifier.padding(4.dp)
    )
}

@Composable
private fun CookTimeInfoSubtitle(prepTime: Int) {
    Text(
        text = "Prep Time: $prepTime minutes",
        fontSize = 12.sp,
        modifier = Modifier.padding(4.dp)
    )
}

@Composable
private fun Subtitle(servings: Int, prepTime: Int) {
    Row(
        verticalAlignment = Alignment.CenterVertically
    ) {
        ServingsInfoSubtitle(servings)
        Text(text = " | ", fontSize = 24.sp)
        CookTimeInfoSubtitle(prepTime)
    }
}

@Composable
private fun Description(description: String) {
    Text(
        text = description,
        fontSize = 10.sp,
        modifier = Modifier.padding(4.dp)
    )
}

@Composable
private fun Header(header: Header, modifier: Modifier = Modifier) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = modifier
    ) {
        ImageHeader(header.image)
        Title(header.title)
        Subtitle(header.servings, header.prepTime)
        Description(header.description)
    }
}

@Composable
private fun IngredientsHeader(
    modifier: Modifier = Modifier,
    onHeaderClicked: () -> Unit
) {
    Text(
        text = "Ingredients",
        fontSize = 20.sp,
        modifier = modifier
            .clickable(onClick = onHeaderClicked)
            .padding(4.dp)
    )
}

@Composable
private fun Ingredients(
    ingredients: List<Ingredient>,
    onCheckedChange: (Int, Boolean) -> Unit,
    modifier: Modifier = Modifier
) {
    val (isExpanded, setIsExpanded) = remember { mutableStateOf(true) }

    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = modifier.padding(4.dp)
    ) {
        IngredientsHeader(
            onHeaderClicked = { setIsExpanded(!isExpanded) },
            modifier = Modifier
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

//@Composable
//private fun Ingredients(ingredients: List<Ingredient>, modifier: Modifier) {
//    val (isExpanded, setIsExpanded) = remember { mutableStateOf(true) }
//
//    Column(
//        horizontalAlignment = Alignment.CenterHorizontally,
//        modifier = Modifier.padding(16.dp)
//    ) {
//        IngredientsHeader(
//            modifier = Modifier,
//            onHeaderClicked = { setIsExpanded(!isExpanded) }
//        )
//        AnimatedVisibility(
//            visible = isExpanded,
//            enter = expandVertically(animationSpec = spring()),
//            exit = shrinkVertically(animationSpec = spring())
//        ) {
//            IngredientListStateful(ingredients)
//        }
//    }
//}
//

@Composable
private fun InstructionsHeader(
    modifier: Modifier = Modifier,
    onHeaderClicked: () -> Unit
) {
    Text(
        text = "Instructions",
        fontSize = 20.sp,
        modifier = modifier
            .clickable(onClick = onHeaderClicked)
            .padding(4.dp)
    )
}

@Composable
private fun Instructions(
    instructions: List<Instruction>,
    onCheckedChange: (Int, Boolean) -> Unit,
    modifier: Modifier = Modifier
) {
    val (isExpanded, setIsExpanded) = remember { mutableStateOf(true) }

    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = modifier.padding(4.dp)
    ) {
        InstructionsHeader(
            onHeaderClicked = { setIsExpanded(!isExpanded) },
            modifier = Modifier
        )
        AnimatedVisibility(
            visible = isExpanded,
            enter = expandVertically(animationSpec = spring()),
            exit = shrinkVertically(animationSpec = spring())
        ) {
            InstructionListStateless(
                instructions = instructions,
                onCheckedChange = onCheckedChange
            )
        }
    }
}

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
                modifier = Modifier,
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
                modifier = Modifier,
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
            modifier = Modifier,
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

@Composable
fun RecipeCardPortrait(
    recipe: Recipe,
    ingredients: List<Ingredient>,
    onIngredientsCheckedChange: (Int, Boolean) -> Unit,
    instructions: List<Instruction>,
    onInstructionsCheckedChange: (Int, Boolean) -> Unit,
    modifier: Modifier = Modifier
) {
    val header = Header(
        recipe.image,
        recipe.title,
        recipe.servings,
        recipe.prepTime,
        recipe.description
    )

    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Top,
        modifier = modifier
            .fillMaxSize()
            .padding(2.dp)
    ) {
        Header(header = header, modifier = modifier)
        Spacer(modifier = modifier.size(4.dp))
        Ingredients(
            ingredients = ingredients,
            onCheckedChange = onIngredientsCheckedChange,
            modifier = modifier.fillMaxWidth()
        )

        Spacer(modifier = modifier.size(16.dp))
        Instructions(
            instructions = instructions,
            onCheckedChange = onInstructionsCheckedChange,
            modifier = modifier.fillMaxWidth()
        )
    }
}

@Composable
fun RecipeCardLandscape(
    recipe: Recipe,
    ingredients: List<Ingredient>,
    onIngredientsCheckedChange: (Int, Boolean) -> Unit,
    instructions: List<Instruction>,
    onInstructionsCheckedChange: (Int, Boolean) -> Unit,
    modifier: Modifier = Modifier
) {
    val header = Header(recipe.image, recipe.title, recipe.servings, recipe.prepTime, recipe.description)

    Row {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = modifier
        ) {
            Header(
                header = header,
                modifier = modifier
            )
        }
        Column(
            modifier = modifier
        ) {
            Ingredients(
                ingredients,
                onCheckedChange = onIngredientsCheckedChange,
                modifier = modifier,
            )
        }
        Column(
            modifier = modifier
        ) {
            Instructions(
                instructions,
                onCheckedChange = onInstructionsCheckedChange,
                modifier = modifier
            )
        }
    }
}

//@Preview(showBackground = true, name = "Recipe Card Portrait", widthDp = 400, heightDp = 800)
//@Composable
//fun RecipeCardPreviewPortrait() {
//    RecipeCard(PaddingValues(0.dp), recipe)
//}

//@Preview(showBackground = true, name = "Recipe Card Landscape", widthDp = 800, heightDp = 400)
//@Composable
//fun RecipeCardPreviewLandscape() {
//    RecipeCard(PaddingValues(0.dp), recipe)
//}

//@Preview(showBackground = true, name = "Recipe Card Dark", uiMode = Configuration.UI_MODE_NIGHT_YES)
//@Composable
//fun RecipeCardPreviewDark() {
//    RecipeCard(PaddingValues(0.dp), recipe)
//}
