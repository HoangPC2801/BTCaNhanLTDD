package com.example.baitaptuan3.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.baitaptuan3.screens.*

// Định nghĩa các route (đường dẫn) cho các màn hình
object AppRoutes {
    const val WELCOME_SCREEN = "welcome"
    const val COMPONENT_LIST_SCREEN = "component_list"
    const val TEXT_DETAIL_SCREEN = "text_detail"
    const val IMAGE_DETAIL_SCREEN = "image_detail"
}

@Composable
fun AppNavigation() {
    val navController = rememberNavController()

    NavHost(navController = navController, startDestination = AppRoutes.WELCOME_SCREEN) {
        // Màn hình chào mừng
        composable(AppRoutes.WELCOME_SCREEN) {
            WelcomeScreen(navController = navController)
        }

        // Màn hình danh sách
        composable(AppRoutes.COMPONENT_LIST_SCREEN) {
            ComponentListScreen(navController = navController)
        }

        // Màn hình chi tiết Text
        composable(AppRoutes.TEXT_DETAIL_SCREEN) {
            TextDetailScreen(navController = navController)
        }

        composable(AppRoutes.IMAGE_DETAIL_SCREEN) {
            ImageDetailScreen(navController = navController)
        }
    }
}