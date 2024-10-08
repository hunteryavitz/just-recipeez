package com.example.justrecipestest

import android.content.res.Configuration
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Checkbox
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.pointer.PointerIcon.Companion.Text
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

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
        Image(
            painter = painterResource(id = R.drawable.header_01),
            contentDescription = "Header",
            modifier = Modifier
                .fillMaxWidth()
                .height(225.dp)
        )
        Text(
            text = "Grilled Steak with Potatoes",
            fontSize = 24.sp,
            modifier = Modifier.padding(top = 16.dp)
        )
        Row {
            Text(
                text = "Serves: 2",
                fontSize = 12.sp,
                modifier = Modifier.padding(4.dp)
            )
            Text(
                text = "Cook Time: 45 minutes",
                fontSize = 12.sp,
                modifier = Modifier.padding(4.dp)
            )
        }
        Text(
            text = "Ingredients",
            fontSize = 20.sp,
            modifier = Modifier.padding(16.dp)
        )

        Row(
            horizontalArrangement = Arrangement.Start,
            modifier = Modifier.fillMaxWidth()
        ) {
            Checkbox(
                checked = false,
                onCheckedChange = { /*TODO*/ },
//                modifier = Modifier.padding(4.dp)
            )
            Text(
                text = "6 oz. steak",
                fontSize = 16.sp,
//                modifier = Modifier.padding(4.dp)
            )
        }
        Row(
            verticalAlignment = Alignment.Start
        ) {
            Checkbox(
                checked = false,
                onCheckedChange = { /*TODO*/ },
//                modifier = Modifier.padding(4.dp)
            )
            Text(
                text = "2 cups diced potatoes",
                fontSize = 16.sp,
//                modifier = Modifier.padding(4.dp)
            )
        }
        Row(
            verticalAlignment = Alignment.Start
        ) {
            Checkbox(
                checked = false,
                onCheckedChange = { /*TODO*/ },
//                modifier = Modifier.padding(4.dp)
            )
            Text(
                text = "3 oz. broccoli",
                fontSize = 16.sp,
//                modifier = Modifier.padding(4.dp)
            )
        }
//        NavBar()
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
