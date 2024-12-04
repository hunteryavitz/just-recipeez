package com.example.justrecipestest.ui.components.recipecard.header

import android.content.res.Configuration
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.spring
import androidx.compose.animation.expandVertically
import androidx.compose.animation.shrinkVertically
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.tooling.preview.Preview
import com.example.justrecipestest.R

data class Header(
    val image: Int,
    val title: String,
    val servings: Int,
    val prepTime: Int,
    val description: String
)

@Composable
fun Header(
    header: Header,
    setIsFavorite: (Boolean) -> Unit,
    isFavorite: Boolean,
    modifier: Modifier
) {
    val configuration = LocalConfiguration.current
    val (isExpanded, setIsExpanded) = remember { mutableStateOf(configuration.orientation == Configuration.ORIENTATION_PORTRAIT) }

    HeaderTitleBar(
        header = header,
        isExpanded = isExpanded,
        setIsExpanded = setIsExpanded,
        isFavorite = isFavorite,
        setIsFavorite = setIsFavorite,
        modifier = modifier
    )
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
            HeaderImage(header.image)
            HeaderSubtitle(header.servings, header.prepTime)
            HeaderDescription(header.description)
        }
    }
}

@Preview
@Preview("Dark Theme", uiMode = android.content.res.Configuration.UI_MODE_NIGHT_YES)
@Composable
fun PreviewHeader() {
    Header(
        header = Header(
            image = R.drawable.header_02,
            title = "Chocolate Cake",
            servings = 4,
            prepTime = 30,
            description = "This is a delicious chocolate cake recipe that is perfect for any occasion."
        ),
        setIsFavorite = {},
        isFavorite = false,
        modifier = Modifier
    )
}
