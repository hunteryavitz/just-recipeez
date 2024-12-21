package com.example.justrecipestest.ui.components.recipecard.header

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.spring
import androidx.compose.animation.expandVertically
import androidx.compose.animation.shrinkVertically
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
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
    isExpanded: Boolean,
    setIsExpanded: (Boolean) -> Unit,
    isFavorite: Boolean,
    setIsFavorite: (Boolean) -> Unit,
    modifier: Modifier
) {

    Column(
        modifier = modifier
            .fillMaxWidth(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Row(
            modifier = modifier
                .fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            HeaderTitleBar(
                header = header,
                isExpanded = isExpanded,
                setIsExpanded = setIsExpanded,
                isFavorite = isFavorite,
                setIsFavorite = setIsFavorite,
                modifier = modifier
            )
        }
        AnimatedVisibility(
            visible = isExpanded,
            enter = expandVertically(animationSpec = spring()),
            exit = shrinkVertically(animationSpec = spring())
        ) {
            Column {
                Row(
                    modifier = modifier
                        .fillMaxWidth(),
                    horizontalArrangement = Arrangement.Center
                ) {
                    HeaderImage(header.image)
                }
                Row(
                    modifier = modifier
                        .fillMaxWidth(),
                    horizontalArrangement = Arrangement.Center
                ) {
                    HeaderDescription(header.description)
                }
                Row(
                    modifier = modifier
                        .fillMaxWidth(),
                    horizontalArrangement = Arrangement.Center
                ) {
                    HeaderSubtitle(header.servings, header.prepTime)
                }
            }
    }
//        Column(
//            modifier = modifier
//                .fillMaxWidth(),
//            horizontalAlignment = Alignment.CenterHorizontally
//        ) {
//            HeaderImage(header.image)
//            HeaderSubtitle(header.servings, header.prepTime)
//            HeaderDescription(header.description)
//        }
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
        isExpanded = true,
        setIsExpanded = {},
        isFavorite = false,
        setIsFavorite = {},
        modifier = Modifier
    )
}
