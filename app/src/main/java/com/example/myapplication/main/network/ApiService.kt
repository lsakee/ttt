package com.example.myapplication.main.network

import com.example.myapplication.main.model.CategoriesResponse
import com.skydoves.sandwich.ApiResponse
import retrofit2.http.GET


interface ApiService {

    @GET("/api/categories")
    suspend fun fetchCategories() : ApiResponse<CategoriesResponse>
}