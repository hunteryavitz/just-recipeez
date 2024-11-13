package com.example.justrecipestest

import android.content.res.Configuration
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.spring
import androidx.compose.animation.expandVertically
import androidx.compose.animation.shrinkVertically
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material.icons.filled.KeyboardArrowUp
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
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
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

data class Header(
    val image: Int,
    val title: String,
    val servings: Int,
    val prepTime: Int,
    val description: String
)

val fontFamilyDancingScript = FontFamily(
    Font(R.font.dancing_script, FontWeight.Bold),
)

@Composable
private fun ImageHeader(image: Int) {
    Row(modifier = Modifier
        .padding(8.dp)) {
        Image(
            painter = painterResource(image),
            contentDescription = "Header",
            contentScale = ContentScale.Crop,
            modifier = Modifier
                .size(96.dp)
                .clip(RoundedCornerShape(14.dp))
        )
    }
}

@Composable
private fun Title(title: String) {
    Row(modifier = Modifier
        .padding(8.dp)
        .fillMaxWidth()
    ) {
        Text(
            text = title,
            color = Color.Black,
            textAlign = TextAlign.Center,
            fontSize = 28.sp,
            fontFamily = fontFamilyDancingScript,
            modifier = Modifier
                .fillMaxWidth()
        )
    }
}

@Composable
private fun ServingsInfoSubtitle(servings: Int) {
    Text(
        text = "Serves $servings",
        color = Color.Black,
        textAlign = TextAlign.Center,
        fontSize = 18.sp,
        fontFamily = fontFamilyDancingScript
    )
}

@Composable
private fun CookTimeInfoSubtitle(prepTime: Int) {
    Text(
        text = "$prepTime Minutes",
        color = Color.Black,
        textAlign = TextAlign.Center,
        fontSize = 18.sp,
        fontFamily = fontFamilyDancingScript
    )
}

@Composable
private fun Subtitle(servings: Int, prepTime: Int) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier
            .padding(4.dp)
    ) {
        Column(modifier = Modifier
            .weight(1f),
            horizontalAlignment = Alignment.End
        ) {
            ServingsInfoSubtitle(servings)
        }
        Column(modifier = Modifier) {
            Text(
                text = " | ",
                color = Color.Black,
                fontSize = 24.sp
            )
        }
        Column(modifier = Modifier
            .weight(1f),
            horizontalAlignment = Alignment.Start
        ) {
            CookTimeInfoSubtitle(prepTime)
        }
    }
}

@Composable
private fun Description(description: String) {
    Row(modifier = Modifier
        .padding(8.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.Center
    ) {
        Text(
            text = description,
            color = Color.Black,
            textAlign = TextAlign.Center,
            fontSize = 16.sp,
            fontFamily = fontFamilyDancingScript
        )
    }
}

@Composable
private fun Header(
    header: Header,
    modifier: Modifier
) {
    val (isExpanded, setIsExpanded) = remember { mutableStateOf(true) }
    Row(
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically,
        modifier = modifier
            .fillMaxWidth()
    ) {
        Column {
            IconButton(
                onClick = { }
            ) {
                Icon(
                    imageVector = Icons.Default.KeyboardArrowUp,
                    contentDescription = "Expand Card",
                    tint = Color.Black,
                    modifier = Modifier
                        .size(32.dp)
                        .clickable(onClick = { setIsExpanded(!isExpanded) })
                )
            }
        }
        Column(
            modifier = modifier
                .weight(1f)
        ) {
            Title(header.title)
        }
        Column {
            IconButton(
                onClick = { }
            ) {
                Icon(
                    imageVector = Icons.Default.Favorite,
                    contentDescription = "Expand Card",
                    tint = Color.Black,
                    modifier = Modifier
                        .size(22.dp)
                        .clickable(onClick = { setIsExpanded(!isExpanded) })
                )
            }
        }
    }
    AnimatedVisibility(
        visible = isExpanded,
        enter = expandVertically(animationSpec = spring()),
        exit = shrinkVertically(animationSpec = spring())
    ) {
        Column(
            modifier = modifier
                .fillMaxWidth(),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            ImageHeader(header.image)
            Subtitle(header.servings, header.prepTime)
            Description(header.description)
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
    onInstructionsCheckedChange: (Int, Boolean) -> Unit
) {
    val header = Header(
        recipe.image,
        recipe.title,
        recipe.servings,
        recipe.prepTime,
        recipe.description
    )

    val (isCollapsedIngredientsList, setIsCollapsedIngredientsList) = remember { mutableStateOf(true) }
    val (isFullScreenIngredients, setIsFullScreenIngredients) = remember { mutableStateOf(false) }
    val (isCollapsedInstructionsList, setIsCollapsedInstructionsList) = remember { mutableStateOf(false) }
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
            modifier = Modifier.padding(top = 24.dp)
        ) {
            if (!isFullScreenIngredients && !isFullScreenInstructions) {
                Header(
                    header = header,
                    modifier = Modifier
                        .fillMaxWidth()
                )
                if (isCollapsedIngredientsList) {
                    IngredientsHeader(
                        onCollapseIngredientsListClicked = { setIsCollapsedIngredientsList(false) },
                        onFullScreenIngredientsClicked = { setIsFullScreenIngredients(true) }
                    )
                } else {
                    Ingredients(
                        ingredients = ingredients,
                        onCheckedChange = onIngredientsCheckedChange,
                        onCollapseIngredientsListClicked = { setIsCollapsedIngredientsList(true) },
                        onFullScreenIngredientsClicked = { setIsFullScreenIngredients(true) },
                        modifier = Modifier
                            .fillMaxWidth()
                    )
                }
                Spacer(modifier = Modifier.size(4.dp))
                if (isCollapsedInstructionsList) {
                    InstructionsHeader(
                        onCollapseInstructionsHeaderClicked = { setIsCollapsedInstructionsList(false) },
                        onFullScreenInstructionsClicked = { setIsFullScreenInstructions(true) }
                    )
                } else {
                    Instructions(
                        instructions = instructions,
                        onCheckedChange = onInstructionsCheckedChange,
                        onCollapseInstructionsHeaderClicked = { setIsCollapsedInstructionsList(true) },
                        onFullScreenInstructionsClicked = { setIsFullScreenInstructions(true) },
                        modifier = Modifier
                            .fillMaxWidth()
                    )
                }
            } else if (isFullScreenIngredients) {
                Ingredients(
                    ingredients = ingredients,
                    onCheckedChange = onIngredientsCheckedChange,
                    onCollapseIngredientsListClicked = { setIsCollapsedIngredientsList(false) },
                    onFullScreenIngredientsClicked = { setIsFullScreenIngredients(false) },
                    modifier = Modifier
                        .fillMaxWidth()
                        .fillMaxSize()
                )
            } else {
                Instructions(
                    instructions = instructions,
                    onCheckedChange = onInstructionsCheckedChange,
                    onCollapseInstructionsHeaderClicked = { setIsCollapsedInstructionsList(false) },
                    onFullScreenInstructionsClicked = { setIsFullScreenInstructions(false) },
                    modifier = Modifier
                        .fillMaxWidth()
                        .fillMaxSize()
                )
            }
        }
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
            modifier = modifier
                .fillMaxSize()
                .padding(top = 24.dp, start = 24.dp)
        ) {
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                modifier = modifier
                    .weight(.5f)
            ) {
                Row(
                    modifier = modifier
                        .padding(4.dp)
                ) {
                    Header(
                        header = header,
                        modifier = modifier
                    )
                }
            }
            Column(
                modifier = modifier
                    .weight(1f)
            ) {
                Row(
                    modifier = modifier
                        .weight(1f)
                        .padding(12.dp)
                ) {
                    Ingredients(
                        ingredients,
                        onCheckedChange = onIngredientsCheckedChange,
                        onCollapseIngredientsListClicked = { },
                        onFullScreenIngredientsClicked = { },
                        modifier = modifier)
                }
            }
            Column(
                modifier = modifier
                    .weight(1f)
            ) {
                Row(
                    modifier = modifier
                        .weight(1f)
                        .padding(12.dp)
                ) {
                    Instructions(
                        instructions,
                        onCheckedChange = onInstructionsCheckedChange,
                        onCollapseInstructionsHeaderClicked = { },
                        onFullScreenInstructionsClicked = { },
                        modifier = modifier
                    )
                }
            }
        }
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
