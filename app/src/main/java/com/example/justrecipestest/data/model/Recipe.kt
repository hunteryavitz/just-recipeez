package com.example.justrecipestest.data.model

import kotlinx.serialization.Serializable
import kotlinx.serialization.json.Json
import java.io.File

@Serializable
data class RecipeStub(
    val image: String?,
    val title: String,
    val servings: Int,
    val prepTime: Int, // in minutes
    val description: String,
    val ingredients: List<String>,
    val instructions: List<String>,
    val categories: List<String>,
    val tags: List<String>,
    val nutritionalInfo: NutritionalInfo?
) {
    fun toRecipe(): Recipe {
        return Recipe(
            image = image,
            title = title,
            servings = servings,
            prepTime = prepTime,
            description = description,
            ingredients = ingredients.map { Ingredient(it) },
            instructions = instructions.map { Instruction(it) },
            categories = categories,
            tags = tags,
            nutritionalInfo = nutritionalInfo
        )
    }
}

@Serializable
data class Recipe(
    val image: String?,
    val title: String,
    val servings: Int,
    val prepTime: Int, // in minutes
    val description: String,
    val ingredients: List<Ingredient>,
    val instructions: List<Instruction>,
    val categories: List<String>,
    val tags: List<String>,
    val nutritionalInfo: NutritionalInfo?,
    val isFavorite: Boolean = false
)

fun main(recipeJson: String) {
    val jsonFile = File(recipeJson)
    val jsonContent = jsonFile.readText()

    val recipeStub: RecipeStub = Json.decodeFromString(jsonContent)

    val recipe = recipeStub.toRecipe()

    println(recipe)
}
