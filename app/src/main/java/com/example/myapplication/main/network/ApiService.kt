package com.example.myapplication.main.network

import com.example.myapplication.main.model.CategoriesResponse
import com.example.myapplication.main.model.UploadFilesResponse
import com.skydoves.sandwich.ApiResponse
import okhttp3.MultipartBody
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Multipart
import retrofit2.http.POST
import retrofit2.http.Part


interface ApiService {

    @GET("/api/categories")
    suspend fun fetchCategories() : ApiResponse<CategoriesResponse>

    @Multipart
    @POST("/api/files")
    suspend fun uploadFile(@Part type: MultipartBody.Part, @Part files: MultipartBody.Part): ApiResponse<UploadFilesResponse>
}