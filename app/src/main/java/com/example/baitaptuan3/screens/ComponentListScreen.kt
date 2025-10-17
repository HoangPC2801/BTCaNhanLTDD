package com.example.baitaptuan3.screens

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.baitaptuan3.navigation.AppRoutes
import com.example.baitaptuan3.ui.theme.BaiTapTuan3Theme

// Lớp dữ liệu để định nghĩa một thành phần UI
data class UIComponent(val name: String, val description: String, val route: String)

// Danh sách các thành phần
val components = listOf(
    UIComponent("Text", "Hiển thị văn bản", AppRoutes.TEXT_DETAIL_SCREEN),
    UIComponent("Image", "Hiển thị hình ảnh", AppRoutes.IMAGE_DETAIL_SCREEN),
    UIComponent("TextField", "Ô nhập liệu cho văn bản", ""),
    UIComponent("PasswordField", "Ô nhập liệu cho mật khẩu", ""),
    UIComponent("Column", "Sắp xếp các thành phần theo chiều dọc", ""),
    UIComponent("Row", "Sắp xếp các thành phần theo chiều ngang", "")
)

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ComponentListScreen(navController: NavController) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("UI Components List") },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = MaterialTheme.colorScheme.primaryContainer
                )
            )
        }
    ) { paddingValues ->
        LazyColumn(
            modifier = Modifier
                .padding(paddingValues)
                .padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            items(components) { component ->
                ComponentItem(component = component, onClick = {
                    // Chỉ điều hướng nếu route không rỗng
                    if (component.route.isNotBlank()) {
                        navController.navigate(component.route)
                    }
                })
            }
        }
    }
}

@Composable
fun ComponentItem(component: UIComponent, onClick: () -> Unit) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .clickable(onClick = onClick),
        colors = CardDefaults.cardColors(containerColor = Color(0xFFE0E0E0))
    ) {
        Column(modifier = Modifier.padding(16.dp)) {
            Text(text = component.name, style = MaterialTheme.typography.titleMedium)
            Text(text = component.description, style = MaterialTheme.typography.bodySmall)
        }
    }
}
