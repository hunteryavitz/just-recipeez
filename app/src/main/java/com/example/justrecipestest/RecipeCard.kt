package com.example.justrecipestest

import android.content.res.Configuration
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Create
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Notifications
import androidx.compose.material3.Checkbox
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
private fun RecipeCardImageHeader() {
    Image(
        painter = painterResource(id = R.drawable.header_01),
        contentDescription = "Header",
        modifier = Modifier
            .fillMaxWidth()
            .height(225.dp)
    )
}

@Composable
private fun RecipeCardTitle() {
    Text(
        text = "Grilled Steak with Potatoes",
        fontSize = 24.sp,
        modifier = Modifier.padding(top = 16.dp)
    )
}

@Composable
private fun RecipeCardServingsInfo() {
    Text(
        text = "Serves: 2",
        fontSize = 12.sp,
        modifier = Modifier.padding(4.dp)
    )
}

@Composable
private fun RecipeCardCookTimeInfo() {
    Text(
        text = "Cook Time: 45 minutes",
        fontSize = 12.sp,
        modifier = Modifier.padding(4.dp)
    )
}

@Composable
private fun RecipeCardSubtitle() {
    Row {
        RecipeCardServingsInfo()
        RecipeCardCookTimeInfo()
    }
}

@Composable
private fun RecipeCardMainHeader() {
    RecipeCardImageHeader()
    RecipeCardTitle()
    RecipeCardSubtitle()
}

@Composable
private fun RecipeCardIngredientsHeader() {
    Text(
        text = "Ingredients",
        fontSize = 20.sp,
        modifier = Modifier.padding(16.dp)
    )
}

@Composable
private fun RecipeCardIngredients() {
    val ingredients = listOf(
        Ingredient("6 oz. steak", false),
        Ingredient("2 tbsp. olive oil", false),
        Ingredient("1 clove garlic", false),
        Ingredient("1/2 tsp. salt", false),
        Ingredient("1/4 tsp. pepper", false),
    )
    RecipeCardIngredientsHeader()
    IngredientListStateful(ingredients)
}

@Composable
private fun RecipeCardInstructionsHeader() {
    Text(
        text = "Instructions",
        fontSize = 20.sp,
        modifier = Modifier.padding(16.dp)
    )
}

@Composable
private fun RecipeCardInstruction() {
    Row(
        horizontalArrangement = Arrangement.Start,
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier.fillMaxWidth()
    ) {
        Column(
            horizontalAlignment = Alignment.End,
            verticalArrangement = Arrangement.Center,
            modifier = Modifier.weight(1f)
        ) {
            Checkbox(
                checked = false,
                onCheckedChange = { /*TODO*/ },
            )
        }
        Column(
            horizontalAlignment = Alignment.Start,
            verticalArrangement = Arrangement.Center,
            modifier = Modifier.weight(2f) // Adjust the weight to center the text
        ) {
            Text(
                text = "Grill steak for 5 minutes on each side",
                fontSize = 12.sp,
            )
        }
    }
}

@Composable
private fun RecipeCardInstructions() {
    RecipeCardInstructionsHeader()
    RecipeCardInstruction()
    RecipeCardInstruction()
    RecipeCardInstruction()
}

//@Composable
//private fun RecipeCardBottomNavigation(modifier: Modifier = Modifier) {
//    NavigationBar(
//        containerColor = MaterialTheme.colorScheme.surfaceVariant,
//        modifier = modifier
//    ) {
//        NavigationBarItem(
//            icon = {
//                Icon(
//                    imageVector = Icons.Default.Home,
//                    contentDescription = null
//                )
//            },
//            label = {
//                Text(
//                    text = stringResource(R.string.bottom_navigation_home)
//                )
//            },
//            selected = true,
//            onClick = { /* TODO */ }
//        )
//        NavigationBarItem(
//            icon = {
//                Icon(
//                    imageVector = Icons.Default.Notifications,
//                    contentDescription = null
//                )
//            },
//            label = {
//                Text(
//                    text = stringResource(R.string.bottom_navigation_timer)
//                )
//            },
//            selected = false,
//            onClick = { /* TODO */ }
//        )
//        NavigationBarItem(
//            icon = {
//                Icon(
//                    imageVector = Icons.Default.Create,
//                    contentDescription = null
//                )
//            },
//            label = {
//                Text(
//                    text = stringResource(R.string.bottom_navigation_edit)
//                )
//            },
//            selected = false,
//            onClick = { /* TODO */ }
//        )
//    }
//}

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
        RecipeCardMainHeader()
        RecipeCardIngredients()
        RecipeCardInstructions()
//        RecipeCardBottomNavigation()
    }
}

@Composable
fun RecipeCardLandscape() {
    Row {
//        NavBar()
        Column {
            Row {
//            Header()
//            Image()
            }
            Row {
//            Body()
            }
        }
    }
}

@Preview(showBackground = true, name = "Recipe Card Portrait")
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
