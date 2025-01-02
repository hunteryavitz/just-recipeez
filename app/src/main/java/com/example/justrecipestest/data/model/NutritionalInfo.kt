package com.example.justrecipestest.data.model

import kotlinx.serialization.Serializable

@Serializable
data class NutritionalInfo(
    val calories: Int,
    val fat: Int, // in grams
    val saturatedFat: Int, // in grams
    val cholesterol: Int, // in milligrams
    val sodium: Int, // in milligrams
    val carbohydrates: Int, // in grams
    val fiber: Int, // in grams
    val sugar: Int, // in grams
    val protein: Int // in grams
)
