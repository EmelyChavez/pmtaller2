package com.pdmtaller2.c00097923_EmelyChavez.data.model

data class Restaurant(
    val id: Int,
    val name: String,
    val description: String,
    val imageUrl: Int,
    val categories: List<String>,
    val menu: List<Dish>
)
