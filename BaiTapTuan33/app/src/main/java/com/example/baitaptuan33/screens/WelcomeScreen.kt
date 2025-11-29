package com.example.baitaptuan33.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.baitaptuan33.R
import com.example.baitaptuan33.navigation.AppRoutes

@Composable
fun WelcomeScreen(navController: NavController) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(32.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Image(
            painter = painterResource(id = R.drawable.logo),
            contentDescription = "Jetpack Compose Logo",
            modifier = Modifier
                .size(150.dp)
        )
        Spacer(modifier = Modifier.height(24.dp))
        Text(
            text = "Jetpack Compose là một bộ công cụ hiện đại để xây dựng giao diện người dùng gốc của Android.",
            style = MaterialTheme.typography.bodyLarge,
            textAlign = TextAlign.Center
        )
        Spacer(modifier = Modifier.height(48.dp))
        Button(
            onClick = {
                // Điều hướng đến màn hình danh sách
                navController.navigate(AppRoutes.COMPONENT_LIST_SCREEN)
            },
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("Tôi đã sẵn sàng")
        }
    }
}