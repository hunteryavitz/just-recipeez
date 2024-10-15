package com.example.justrecipestest

import android.content.res.Configuration
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.spring
import androidx.compose.animation.expandVertically
import androidx.compose.animation.shrinkVertically
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

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
        text = "Cook Time: $prepTime minutes",
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
        fontSize = 14.sp,
        modifier = Modifier.padding(8.dp)
    )
}

data class Header(
    val image: Int,
    val title: String,
    val servings: Int,
    val prepTime: Int,
    val description: String
)

@Composable
private fun Header(header: Header) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        ImageHeader(header.image)
        Title(header.title)
        Subtitle(header.servings, header.prepTime)
        Description(header.description)
    }
}

@Composable
private fun IngredientsHeader(
    modifier: Modifier,
    onHeaderClicked: () -> Unit
) {
    Text(
        text = "Ingredients",
        fontSize = 20.sp,
        modifier = modifier
            .clickable(onClick = { onHeaderClicked() })
            .padding(16.dp),
    )
}

@Composable
private fun Ingredients(ingredients: List<Ingredient>) {
    val (isExpanded, setIsExpanded) = remember { mutableStateOf(true) }

    Column(
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        IngredientsHeader(
            modifier = Modifier,
            onHeaderClicked = { setIsExpanded(!isExpanded) }
        )
        AnimatedVisibility(
            visible = isExpanded,
            enter = expandVertically(animationSpec = spring()),
            exit = shrinkVertically(animationSpec = spring())
        ) {
            IngredientListStateful(ingredients)
        }
    }
}

@Composable
private fun InstructionsHeader(
    modifier: Modifier,
    onHeaderClicked: () -> Unit
) {
    Text(
        text = "Instructions",
        fontSize = 20.sp,
        modifier = modifier
            .clickable(onClick = { onHeaderClicked() })
            .padding(16.dp)
    )
}

@Composable
private fun Instructions(instructions: List<Instruction>) {
    val (isExpanded, setIsExpanded) = remember { mutableStateOf(true) }

    Column(
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        InstructionsHeader(
            modifier = Modifier,
            onHeaderClicked = { setIsExpanded(!isExpanded) }
        )
        AnimatedVisibility(
            visible = isExpanded,
            enter = expandVertically(animationSpec = spring()),
            exit = shrinkVertically(animationSpec = spring())
        ) {
            InstructionListStateful(instructions)
        }
    }
}

@Composable
fun RecipeCard(modifier: PaddingValues, recipe: Recipe) {
    val configuration = LocalConfiguration.current
    when (configuration.orientation) {
        Configuration.ORIENTATION_PORTRAIT ->
            RecipeCardPortrait(recipe)
        Configuration.ORIENTATION_LANDSCAPE ->
            RecipeCardLandscape(recipe)
        else -> RecipeCardPortrait(recipe)
    }
}

@Composable
fun RecipeCardPortrait(recipe: Recipe) {
    val header = Header(recipe.image, recipe.title, recipe.servings, recipe.prepTime, recipe.description)

    Column(
        modifier = Modifier.fillMaxWidth(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Header(header)
        Ingredients(recipe.ingredients)
        Instructions(recipe.instructions)
    }
}

@Composable
fun RecipeCardLandscape(recipe: Recipe) {
    val header = Header(recipe.image, recipe.title, recipe.servings, recipe.prepTime, recipe.description)

    Row {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier.weight(0.5f)
        ) {
            Header(header)
        }
        Column(
            modifier = Modifier.weight(0.8f)
        ) {
            Ingredients(recipe.ingredients)
        }
        Column(
            modifier = Modifier.weight(0.8f)
        ) {
            Instructions(recipe.instructions)
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
