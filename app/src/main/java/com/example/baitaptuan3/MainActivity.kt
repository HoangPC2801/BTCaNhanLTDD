package com.example.baitaptuan3 // Thay đổi package cho đúng

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
import com.example.baitaptuan3.navigation.AppNavigation
import com.example.baitaptuan3.ui.theme.BaiTapTuan3Theme // Thay bằng tên theme của bạn

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            BaiTapTuan3Theme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    // Gọi hàm navigation chính ở đây
                    AppNavigation()
                }
            }
        }
    }
}