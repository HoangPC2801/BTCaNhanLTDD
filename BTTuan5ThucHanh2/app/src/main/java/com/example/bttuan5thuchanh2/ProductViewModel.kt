package com.example.bttuan5thuchanh2

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch

class ProductViewModel : ViewModel() {
    private val _product = mutableStateOf<Product?>(null)
    val product: State<Product?> = _product

    private val _isLoading = mutableStateOf(true)
    val isLoading: State<Boolean> = _isLoading

    private val _errorMessage = mutableStateOf<String?>(null)
    val errorMessage: State<String?> = _errorMessage

    init {
        fetchProduct()
    }

    private fun fetchProduct() {
        viewModelScope.launch {
            try {
                _isLoading.value = true
                // Gọi API
                val response = RetrofitClient.apiService.getProduct()
                _product.value = response
            } catch (e: Exception) {
                _errorMessage.value = "Lỗi kết nối: ${e.message}"
            } finally {
                _isLoading.value = false
            }
        }
    }
}