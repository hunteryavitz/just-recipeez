package com.example.justrecipestest.ui.components.recipecard.header

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.spring
import androidx.compose.animation.expandVertically
import androidx.compose.animation.shrinkVertically
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.example.justrecipestest.R

@Composable
fun HeaderLandscape(
    header: Header,
    isExpanded: Boolean,
    setIsExpanded: (Boolean) -> Unit,
    isFavorite: Boolean,
    setIsFavorite: (Boolean) -> Unit,
    isLandscape: Boolean = false,
    modifier: Modifier
) {

    Column(
        modifier = modifier
            .fillMaxWidth(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Row(
            modifier = modifier
                .fillMaxWidth()
                .background(color = androidx.compose.ui.graphics.Color.Gray),
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
            Row(modifier = modifier
                .fillMaxSize()
                .weight(1f)
                .background(color = androidx.compose.ui.graphics.Color.LightGray),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceAround
            ) {
                Column(
                    modifier = modifier
                        .weight(0.5f),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    HeaderImage(header.image, isLandscape)
                }
                Column(
                    modifier = modifier
                        .weight(0.5f),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Row(
                        horizontalArrangement = Arrangement.SpaceAround,
                        modifier = modifier
                    ) {
                        HeaderDescription(header.description, isLandscape)
                    }
                    Row {
                        HeaderSubtitle(header.servings, header.prepTime, isLandscape)
                    }
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

@Preview(widthDp = 800, heightDp = 400)
@Preview("Dark Theme", uiMode = android.content.res.Configuration.UI_MODE_NIGHT_YES)
@Composable
fun PreviewHeaderLandscape() {
    HeaderLandscape(
        header = Header(
            image = R.drawable.header_02,
            title = "Chocolate Cake",
            servings = 4,
            prepTime = 30,
            description = "A delicious chocolate cake for all occasions."
        ),
        isExpanded = true,
        setIsExpanded = {},
        isFavorite = false,
        setIsFavorite = {},
        modifier = Modifier
    )
}
