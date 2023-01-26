package com.example.myapplication.main.util

import android.content.Context
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.net.Uri
import android.provider.MediaStore
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import java.io.File
import java.io.FileNotFoundException


class ImageUtil(private val context: Context) {

    companion object {
        const val PREFIX = ".jpg"
    }

    private fun isFailGetColumnIndex(idx: Int) = idx == -1

    private fun getRealPathFromURI(uri: Uri): String {
        var columnIndex = -1
        val proj = arrayOf(MediaStore.Images.Media.DATA)
        val cursor = context.contentResolver.query(uri, proj, null, null, null)
        if (cursor != null && cursor.moveToFirst()) {
            columnIndex = cursor.getColumnIndex(MediaStore.Images.Media.DATA)
        }
        if (isFailGetColumnIndex(columnIndex)) return ""
        val result = cursor!!.getString(columnIndex) // columnIndex에 Path가 존재함
        cursor.close()
        return result
    }

    fun getFileFromUri(uri: Uri): File {
        val realPath = getRealPathFromURI(uri)
        return File(realPath)
    }
}