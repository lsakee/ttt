package com.example.myapplication.main.model

data class UploadFilesResponse(
    val data: Files
)

data class Files(
    val files: List<FileNameFileUrl>
)

data class FileNameFileUrl(
    val filename: String = "",
    val fileUrl: String = ""
)
