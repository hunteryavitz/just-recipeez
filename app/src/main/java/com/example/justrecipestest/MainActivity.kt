package com.example.justrecipestest

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Scaffold
import androidx.compose.ui.Modifier
import com.example.justrecipestest.data.model.Recipe
import com.example.justrecipestest.data.model.RecipeStub
import com.example.justrecipestest.ui.components.recipecard.RecipeCard
import com.example.justrecipestest.ui.theme.JustRecipesTestTheme
import kotlinx.serialization.json.Json
import java.io.File

val recipeJson = File("java/com/example/justrecipestest/data/chocolate-cake.json")
val recipeContent = recipeJson.readText()
val mainRecipe: Recipe = Json.decodeFromString<RecipeStub>(recipeContent).toRecipe()

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            JustRecipesTestTheme {
//                val names = rememberSaveable(saver = SnapshotStateListSaver) {
//                    mutableStateListOf("Recipe 1")
//                }
                Scaffold(
                    modifier = Modifier.fillMaxSize()
//                    floatingActionButton = {
//                        FloatingActionButton(onClick = {
//                            addItemToList(names)
//                        }) {
//                            Icon(Icons.Filled.Add, contentDescription = "Add")
//                        }
//                    }
                ) { innerPadding ->
//                    Main(innerPadding, names)
                    RecipeCard(innerPadding, mainRecipe)
                }
            }
        }
    }

//    private fun addItemToList(names: SnapshotStateList<String>) {
//        val newItem = "Recipe ${names.size + 1}"
//        names.add(newItem)
//    }
}

//@Composable
//fun Main(innerPadding: PaddingValues, names: List<String>) {
//    var shouldShowBoarding by rememberSaveable { mutableStateOf(true) }
//
//    if (shouldShowBoarding) {
//        OnBoardingScreen(onContinueClicked = { shouldShowBoarding = false })
//    } else {
//        Greetings(names)
//    }
//}

//@Composable
//fun OnBoardingScreen(
//    onContinueClicked: () -> Unit,
//    modifier: Modifier = Modifier
//) {
//    Surface {
//        Column(
//            modifier = modifier.fillMaxSize().padding(16.dp),
//            verticalArrangement = Arrangement.Center,
//            horizontalAlignment = Alignment.CenterHorizontally
//        ) {
//            Text("Just Recipez", style = MaterialTheme.typography.headlineLarge)
//            Button(
//                modifier = Modifier.padding(vertical = 24.dp),
//                onClick = onContinueClicked
//            ) {
//                Text("Start Cooking")
//            }
//        }
//    }
//}

//@Composable
//fun Greetings(names: List<String>) {
//    Surface(
//        modifier = Modifier.fillMaxSize().padding(top = 48.dp, start = 24.dp),
//        color = MaterialTheme.colorScheme.background
//    ) {
//        LazyColumn {
//            item { Text("Desserts", fontSize = 24.sp) }
//            items(names) { name ->
//                Greeting(name)
//            }
//        }
//    }
//}

//@Composable
//fun Greeting(name: String) {
//    var expanded by remember { mutableStateOf(false) }
//    val extraPadding by animateDpAsState(
//        targetValue = if (expanded) 148.dp else 0.dp,
//        animationSpec = tween(
//            durationMillis = 1000
//        ), label = ""
//    )
//    Column {
//        Surface(
//            color = MaterialTheme.colorScheme.primary,
//            modifier = Modifier.padding(horizontal = 8.dp, vertical = 40.dp)
//        ) {
//            Row(modifier = Modifier.padding(24.dp)) {
//                Column(
//                    modifier = Modifier
//                        .weight(1f)
//                        .padding(bottom = extraPadding)
//                ) {
//                    Text(text = "Recipe, ")
//                    Text(text = name)
//                }
//                OutlinedButton(
//                    onClick = { expanded = !expanded }
//                ) {
//                    Text(if (expanded) "Show less" else "Show more",
//                        color = MaterialTheme.colorScheme.onPrimary)
//                }
//            }
//        }
//    }
//}
