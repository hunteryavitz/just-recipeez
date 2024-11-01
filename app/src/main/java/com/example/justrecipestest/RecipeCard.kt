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
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material.icons.filled.KeyboardArrowUp
import androidx.compose.material.icons.filled.PlayArrow
import androidx.compose.material3.Button
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
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign

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
        .padding(8.dp)) {
        Text(
            text = title,
            color = Color.Black,
            textAlign = TextAlign.Center,
            fontSize = 28.sp,
            fontFamily = fontFamilyDancingScript
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
            .padding(4.dp)
            .weight(1f),
            horizontalAlignment = Alignment.End
        ) {
            ServingsInfoSubtitle(servings)
        }
        Column(modifier = Modifier
            .padding(4.dp)
        ) {
            Text(
                text = " | ",
                color = Color.Black,
                fontSize = 24.sp
            )
        }
        Column(modifier = Modifier
            .padding(4.dp)
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
private fun Header(header: Header, modifier: Modifier = Modifier) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = modifier
    ) {
        Title(header.title)
        ImageHeader(header.image)
        Subtitle(header.servings, header.prepTime)
        Description(header.description)
    }
}

@Composable
private fun IngredientsHeader(
    modifier: Modifier = Modifier,
    onHeaderClicked: () -> Unit
) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = modifier
            .padding(12.dp)
            .fillMaxWidth()
    ) {
        Row(
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier.fillMaxWidth()
        ) {
            IconButton(
                onClick = { }
            ) {
                Icon(
                    imageVector = Icons.Default.KeyboardArrowDown,
                    contentDescription = "Expand Card",
                    tint = Color.Black,
                    modifier = Modifier
                        .size(32.dp)
                        .clickable(onClick = onHeaderClicked)
                )
            }
            Text(
                text = "Ingredients",
                color = Color.Black,
                fontSize = 24.sp,
                fontFamily = fontFamilyDancingScript
            )
            IconButton(
                onClick = { }
            ) {
                Icon(
                    imageVector = Icons.Default.KeyboardArrowUp,
                    contentDescription = "Expand Card",
                    tint = Color.Black,
                    modifier = Modifier.size(32.dp)
                )
            }
        }
    }
}

@Composable
private fun Ingredients(
    ingredients: List<Ingredient>,
    onCheckedChange: (Int, Boolean) -> Unit,
    onHeaderClicked: () -> Unit,
    modifier: Modifier = Modifier
) {

    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = modifier
            .border(3.dp, MaterialTheme.colorScheme.onSurface.copy(alpha = 0.5f), RoundedCornerShape(8.dp))
    ) {
        IngredientsHeader(
            onHeaderClicked = onHeaderClicked,
            modifier = Modifier
        )
        IngredientListStateless(
            ingredients = ingredients,
            onCheckedChange = onCheckedChange,
            modifier = Modifier.padding(vertical = 4.dp)
        )
    }
}

@Composable
private fun InstructionsHeader(
    modifier: Modifier = Modifier,
    onHeaderClicked: () -> Unit
) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = modifier
            .clickable(onClick = onHeaderClicked)
            .padding(12.dp)
            .fillMaxWidth()
    ) {
        Text(
            text = "Instructions",
            color = Color.Black,
            fontSize = 24.sp,
            fontFamily = fontFamilyDancingScript
        )
    }
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
        modifier = modifier
            .border(3.dp, MaterialTheme.colorScheme.onSurface.copy(alpha = 0.5f), RoundedCornerShape(8.dp))
    ) {
        InstructionsHeader(
            onHeaderClicked = { setIsExpanded(!isExpanded) }
        )
        AnimatedVisibility(
            visible = isExpanded,
            enter = expandVertically(animationSpec = spring()),
            exit = shrinkVertically(animationSpec = spring())
        ) {
            InstructionListStateless(
                instructions = instructions,
                onCheckedChange = onCheckedChange,
                modifier = Modifier.padding(vertical = 4.dp)
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
            Header(
                header = header,
                modifier = Modifier
                    .heightIn(min = 200.dp)
                    .fillMaxWidth()
            )
            Spacer(modifier = Modifier.size(4.dp))

            Ingredients(
                ingredients = ingredients,
                onCheckedChange = onIngredientsCheckedChange,
                onHeaderClicked = { setIsExpanded(!isExpanded) },
                modifier = Modifier
                    .fillMaxWidth()
                    .weight(if (isExpanded) 0.5f else 0.2f)
            )
            Spacer(modifier = Modifier.size(4.dp))

            Instructions(
                instructions = instructions,
                onCheckedChange = onInstructionsCheckedChange,
                modifier = Modifier
                    .fillMaxWidth()
                    .weight(0.5f)
            )
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
                        onHeaderClicked = { },
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
                    Instructions(
                        instructions,
                        onCheckedChange = onInstructionsCheckedChange,
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
