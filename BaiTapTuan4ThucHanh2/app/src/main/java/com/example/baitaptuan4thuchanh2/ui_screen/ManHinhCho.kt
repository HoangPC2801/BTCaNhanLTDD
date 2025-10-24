package com.example.baitaptuan4thuchanh2.ui_screen

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.baitaptuan4thuchanh2.R
import kotlinx.coroutines.delay

@Composable
fun ManHinhCho(navController: NavController) {
    // Tự động chạy khi Composable này xuất hiện
    LaunchedEffect(key1 = true) {
        delay(2000)
        // Điều hướng
        navController.navigate("man_hinh_gioi_thieu") {
            // Xóa màn hình chờ khỏi backstack
            popUpTo("man_hinh_cho") {
                inclusive = true
            }
        }
    }

    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Image(
             painter = painterResource(id = R.drawable.logo_uth),
             contentDescription = "Logo UTH"
         )
    }
}