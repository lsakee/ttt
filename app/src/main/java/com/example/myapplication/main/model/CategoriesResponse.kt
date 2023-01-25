package com.example.myapplication.main.model

data class CategoriesResponse(
    val data: Categories
)

data class Categories(
    val categories: List<Category>
)

data class Category(
    val id: Int,
    val name: String,
    val icon: String
)
