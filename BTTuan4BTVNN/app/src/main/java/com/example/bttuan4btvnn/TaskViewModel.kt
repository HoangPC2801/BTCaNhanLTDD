package com.example.bttuan4btvnn

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch

class TaskViewModel : ViewModel() {
    // State cho danh sách
    var taskList by mutableStateOf<List<Task>>(emptyList())
    var isLoading by mutableStateOf(false)
    var errorMessage by mutableStateOf<String?>(null)

    // State cho màn hình Detail
    var currentTask by mutableStateOf<Task?>(null)

    fun fetchTasks() {
        viewModelScope.launch {
            isLoading = true
            errorMessage = null
            try {
                val response = RetrofitClient.apiService.getTasks()
                if (response.isSuccess) {
                    taskList = response.data ?: emptyList()
                } else {
                    errorMessage = response.message
                }
            } catch (e: Exception) {
                errorMessage = "Error: ${e.message}"
                // Dữ liệu giả lập nếu API lỗi hoặc rỗng để test giao diện Empty
                // taskList = emptyList()
            } finally {
                isLoading = false
            }
        }
    }

    fun fetchTaskDetail(id: Int) {
        viewModelScope.launch {
            isLoading = true
            try {
                val response = RetrofitClient.apiService.getTaskDetail(id)
                if (response.isSuccess) {
                    currentTask = response.data
                }
            } catch (e: Exception) {
                errorMessage = "Error fetching detail"
            } finally {
                isLoading = false
            }
        }
    }

    fun deleteTask(id: Int, onSuccess: () -> Unit) {
        viewModelScope.launch {
            try {
                RetrofitClient.apiService.deleteTask(id)
                // Giả sử xóa thành công
                onSuccess()
                fetchTasks() // Load lại danh sách
            } catch (e: Exception) {
                // Xử lý lỗi
            }
        }
    }
}