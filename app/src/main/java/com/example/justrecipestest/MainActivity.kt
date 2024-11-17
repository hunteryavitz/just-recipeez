package com.example.justrecipestest

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Scaffold
import androidx.compose.ui.Modifier
import com.example.justrecipestest.data.model.Ingredient
import com.example.justrecipestest.data.model.Instruction
import com.example.justrecipestest.data.model.Recipe
import com.example.justrecipestest.ui.components.recipecard.RecipeCard
import com.example.justrecipestest.ui.theme.JustRecipesTestTheme

val recipe = Recipe(
    image = R.drawable.header_02,
    title = "Chocolate Cake",
    servings = 8,
    prepTime = 60,
    description = "A delicious chocolate cake for all occasions",
    ingredients = listOf(
        Ingredient("Flour", false),
        Ingredient("Sugar", false),
        Ingredient("Cocoa Powder", false),
        Ingredient("Baking Powder", false),
        Ingredient("Baking Soda", false),
        Ingredient("Salt", false),
        Ingredient("Eggs", false),
        Ingredient("Milk", false)
    ),
    instructions = listOf(
        Instruction("Preheat oven to 350°F (180°C). Grease and flour two 9-inch round baking pans.", false),
        Instruction("Stir together sugar, flour, cocoa, baking powder, baking soda, and salt in large bowl.", false),
        Instruction("Add eggs, milk, oil, and vanilla; beat on medium speed of mixer 2 minutes.", false),
        Instruction("Stir in boiling water (batter will be thin). Pour batter into prepared pans.", false),
        Instruction("Bake 30 to 35 minutes or until wooden pick inserted in center comes out clean.", false),
        Instruction("Cool 10 minutes; remove from pans to wire racks. Cool completely.", false)
    )
)

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
                    RecipeCard(innerPadding, recipe)
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
