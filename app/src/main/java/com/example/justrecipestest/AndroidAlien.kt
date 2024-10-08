package com.example.justrecipestest

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun AndroidAlien(
    color: Color,
    modifier: Modifier = Modifier
) {
    Image(
        painter = painterResource(id = R.drawable.ic_launcher_foreground),
        contentDescription = "Android Alien",
        colorFilter = ColorFilter.tint(color),
        modifier = modifier
    )
}

@Composable
fun AndroidAliens() {
    AndroidAliensRow()
}

@Composable
fun AndroidAliensRow() {
    Row(
        modifier = Modifier.fillMaxSize(),
        horizontalArrangement = Arrangement.Center,
        verticalAlignment = Alignment.Top
    ) {
        AndroidAlien(
            color = Color.Red,
            modifier = Modifier
                .weight(1f)
                .padding(4.dp)
        )
        AndroidAlien(
            color = Color.Green,
            modifier = Modifier
                .weight(2f)
                .padding(4.dp)
        )
        AndroidAlien(
            color = Color.Blue,
            modifier = Modifier
                .weight(1f)
                .padding(4.dp)
        )
    }
}

@Composable
fun AndroidAliensColumn() {
    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Top,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        AndroidAliensRow()
        AndroidAliensRow()
        AndroidAliensRow()
    }
}

@Composable
fun AndroidAliensGameOverBox() {
    Box {
        AndroidAliensRow()
        Spacer(
            modifier = Modifier
                .matchParentSize()
                .background(Color.Gray.copy(alpha = 0.7f))
        )
        Text(
            text = "GAME OVER",
            fontSize = 50.sp,
            color = Color.White,
            modifier = Modifier.align(Alignment.Center)
        )
    }
}

@Composable
fun ComposeShipsRow() {
    Row(
        modifier = Modifier.fillMaxSize(),
        horizontalArrangement = Arrangement.Center,
        verticalAlignment = Alignment.Top
    ) {

    }
}

@Preview(showBackground = false)
@Composable
fun AndroidAlienRowPreview() {
    AndroidAliensRow()
}

@Preview(showBackground = false)
@Composable
fun AndroidAlienColumnPreview() {
    AndroidAliensColumn()
}

@Preview(showBackground = false)
@Composable
fun AndroidAlienGameOverBoxPreview() {
    AndroidAliensGameOverBox()
}
