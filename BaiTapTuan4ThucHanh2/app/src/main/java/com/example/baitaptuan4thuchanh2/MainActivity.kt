package com.example.baitaptuan4thuchanh2

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.baitaptuan4thuchanh2.ui.theme.BaiTapTuan4ThucHanh2Theme
import com.example.baitaptuan4thuchanh2.ui_screen.ManHinhCho
import com.example.baitaptuan4thuchanh2.ui_screen.ManHinhChinh
import com.example.baitaptuan4thuchanh2.ui_screen.ManHinhGioiThieu

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            BaiTapTuan4ThucHanh2Theme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    AppNavigation()
                }
            }
        }
    }
}

@Composable
fun AppNavigation() {
    // 1. Tạo NavController
    val navController = rememberNavController()

    // 2. Tạo NavHost (Máy chủ điều hướng)
    NavHost(
        navController = navController,
        startDestination = "man_hinh_cho" // Đặt màn hình bắt đầu
    ) {
        // 3. Định nghĩa các tuyến đường (route)
        composable(route = "man_hinh_cho") {
            ManHinhCho(navController = navController)
        }

        composable(route = "man_hinh_gioi_thieu") {
            ManHinhGioiThieu(navController = navController)
        }

        composable(route = "man_hinh_chinh") {
            ManHinhChinh()
        }
    }
}