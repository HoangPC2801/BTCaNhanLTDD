package com.example.bttuan4btvnn

import com.google.gson.annotations.SerializedName

// Wrapper trả về từ API (dựa trên JSON mẫu)
data class BaseResponse<T>(
    val isSuccess: Boolean,
    val message: String,
    val data: T?
)

data class Task(
    val id: Int,
    val title: String,
    val description: String?,
    val status: String,
    val priority: String,
    val category: String,
    val dueDate: String?,
    val subtasks: List<Subtask>?,
    val attachments: List<Attachment>?
)

data class Subtask(
    val id: Int,
    val title: String,
    val isCompleted: Boolean
)

data class Attachment(
    val id: Int,
    val fileName: String,
    val fileUrl: String
)