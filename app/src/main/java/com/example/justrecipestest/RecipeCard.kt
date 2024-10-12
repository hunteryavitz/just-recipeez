package com.example.justrecipestest

import android.content.res.Configuration
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.spring
import androidx.compose.animation.expandVertically
import androidx.compose.animation.shrinkVertically
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
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
private fun ImageHeader() {
    Image(
        painter = painterResource(id = R.drawable.header_01),
        contentDescription = "Header",
        contentScale = ContentScale.Crop,
        modifier = Modifier
            .size(196.dp)
            .clip(RoundedCornerShape(10.dp))
    )
}

@Composable
private fun Title() {
    Text(
        text = "Grilled Steak with Potatoes",
        fontSize = 16.sp,
        modifier = Modifier.padding(top = 8.dp)
    )
}

@Composable
private fun ServingsInfoSubtitle() {
    Text(
        text = "Serves: 2",
        fontSize = 12.sp,
        modifier = Modifier.padding(4.dp)
    )
}

@Composable
private fun CookTimeInfoSubtitle() {
    Text(
        text = "Cook Time: 45 minutes",
        fontSize = 12.sp,
        modifier = Modifier.padding(4.dp)
    )
}

@Composable
private fun Subtitle() {
    Row(
        verticalAlignment = Alignment.CenterVertically
    ) {
        ServingsInfoSubtitle()
        Text(text = " | ", fontSize = 24.sp)
        CookTimeInfoSubtitle()
    }
}


@Composable
private fun Header() {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        ImageHeader()
        Title()
        Subtitle()
    }
}

@Composable
private fun IngredientsHeader(modifier: Modifier) {
    Text(
        text = "Ingredients",
        fontSize = 20.sp,
        modifier = Modifier.padding(16.dp)
    )
}

@Composable
private fun Ingredients() {
    val ingredients = listOf(
        Ingredient("6 oz. steak", false),
        Ingredient("2 tbsp. olive oil", false),
        Ingredient("1 clove garlic", false),
        Ingredient("1/2 tsp. salt", false),
        Ingredient("1/4 tsp. pepper", false),
    )
    val (isExpanded, setIsExpanded) = remember { mutableStateOf(true) }

    Column(
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        IngredientsHeader(modifier = Modifier.clickable { setIsExpanded(!isExpanded) })
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
private fun InstructionsHeader(modifier: Modifier) {
    Text(
        text = "Instructions",
        fontSize = 20.sp,
        modifier = Modifier.padding(16.dp)
    )
}

@Composable
private fun Instructions() {
    val instructions = listOf(
        Instruction("Preheat grill to medium-high heat.", false),
        Instruction("Season steak with salt and pepper.", false),
        Instruction("Grill steak for 5 minutes on each side.", false),
        Instruction("Let steak rest for 5 minutes before slicing.", false),
    )
    val (isExpanded, setIsExpanded) = remember { mutableStateOf(true) }

    Column(
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        InstructionsHeader(modifier = Modifier.clickable { setIsExpanded(!isExpanded) })
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
fun RecipeCard(modifier: Modifier = Modifier) {
    val configuration = LocalConfiguration.current
    when (configuration.orientation) {
        Configuration.ORIENTATION_PORTRAIT ->
            RecipeCardPortrait()
        Configuration.ORIENTATION_LANDSCAPE ->
            RecipeCardLandscape()
        else -> RecipeCardPortrait()
    }
}

@Composable
fun RecipeCardPortrait() {
    Column(
        modifier = Modifier.fillMaxWidth(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Header()
        Ingredients()
        Instructions()
    }
}

@Composable
fun RecipeCardLandscape() {
    Row {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier.weight(0.5f)
        ) {
            Header()
        }
        Column(
            modifier = Modifier.weight(0.8f)
        ) {
            Ingredients()
        }
        Column(
            modifier = Modifier.weight(0.8f)
        ) {
            Instructions()
        }
    }
}


@Preview(showBackground = true, name = "Recipe Card Portrait", widthDp = 400, heightDp = 800)
@Composable
fun RecipeCardPreviewPortrait() {
    RecipeCard()
}

@Preview(showBackground = true, name = "Recipe Card Landscape", widthDp = 800, heightDp = 400)
@Composable
fun RecipeCardPreviewLandscape() {
    RecipeCard()
}

@Preview(showBackground = true, name = "Recipe Card Dark", uiMode = Configuration.UI_MODE_NIGHT_YES)
@Composable
fun RecipeCardPreviewDark() {
    RecipeCard()
}
