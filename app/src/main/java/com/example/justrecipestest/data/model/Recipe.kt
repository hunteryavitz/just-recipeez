package com.example.justrecipestest.data.model

data class Recipe(
    val image: Int,
    val title: String,
    val servings: Int,
    val prepTime: Int,
    val description: String,
    val isFavorite: Boolean,
    val ingredients: List<Ingredient>,
    val instructions: List<Instruction>
)
