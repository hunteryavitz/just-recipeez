package com.example.justrecipestest.data.model

import kotlinx.serialization.Serializable

@Serializable
data class Ingredient(
    val name: String,
    val isChecked: Boolean = false
)
