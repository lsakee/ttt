package com.example.myapplication.main.util

import android.graphics.Bitmap
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.MultipartBody
import okhttp3.RequestBody.Companion.asRequestBody
import java.io.File

object FormDataUtil {
    const val MEDIA_TYPE_IMAGE = "image/*"

    fun getBody(key: String, value: Any): MultipartBody.Part {
        return MultipartBody.Part.createFormData(key, value.toString())
    }

    fun getImageBody(key: String, file: File): MultipartBody.Part {
        return MultipartBody.Part.createFormData(
            name = key,
            filename = file.name,
            body = file.asRequestBody(MEDIA_TYPE_IMAGE.toMediaType())
        )
    }

    fun getImageBody(key: String, bitmap: Bitmap): MultipartBody.Part {
        return MultipartBody.Part.createFormData(
            name = key,
            filename = System.currentTimeMillis().toString() + ".jpeg",
            body = BitmapRequestBody(bitmap)
        )
    }
}