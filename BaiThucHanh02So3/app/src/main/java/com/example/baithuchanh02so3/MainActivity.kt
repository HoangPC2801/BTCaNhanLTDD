package com.example.baithuchanh02so3

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.baithuchanh02so3.ui.theme.BaiThucHanh02So3Theme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            BaiThucHanh02So3Theme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = Color.White
                ) {
                    ManHinhEmail()
                }
            }
        }
    }
}

@Composable
fun ManHinhEmail() {
    var emailNhap by remember { mutableStateOf("") }
    var thongBao by remember { mutableStateOf("") }
    var coLoi by remember { mutableStateOf(false) }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(horizontal = 24.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        // Tiêu đề
        Text(
            text = "Thực hành 02",
            fontSize = 18.sp,
            fontWeight = FontWeight.SemiBold,
            color = Color.Black,
            modifier = Modifier.padding(bottom = 24.dp)
        )

        // Ô nhập Email
        OutlinedTextField(
            value = emailNhap,
            onValueChange = {
                emailNhap = it
                thongBao = ""
                coLoi = false
            },
            placeholder = { Text("Email") },
            singleLine = true,
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Email),
            isError = coLoi,
            shape = RoundedCornerShape(50.dp),
            modifier = Modifier
                .fillMaxWidth()
                .height(56.dp)
        )

        // Hiển thị thông báo lỗi hoặc kết quả
        if (thongBao.isNotBlank()) {
            Text(
                text = thongBao,
                color = if (coLoi) Color.Red else Color.Black,
                fontSize = 14.sp,
                modifier = Modifier
                    .padding(top = 8.dp)
                    .fillMaxWidth(),
                textAlign = TextAlign.Center
            )
        }

        Spacer(modifier = Modifier.height(24.dp))

        // Nút kiểm tra
        Button(
            onClick = {
                when {
                    emailNhap.isBlank() -> {
                        thongBao = "Email không hợp lệ"
                        coLoi = true
                    }
                    !android.util.Patterns.EMAIL_ADDRESS.matcher(emailNhap).matches() -> {
                        thongBao = "Email không đúng định dạng"
                        coLoi = true
                    }
                    else -> {
                        thongBao = "Email hợp lệ"
                        coLoi = false
                    }
                }
            },
            colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF2196F3)),
            shape = RoundedCornerShape(50.dp),
            modifier = Modifier
                .fillMaxWidth()
                .height(56.dp)
        ) {
            Text(
                text = "Kiểm tra",
                color = Color.White,
                fontSize = 16.sp,
                fontWeight = FontWeight.Medium
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun XemTruocManHinhEmail() {
    BaiThucHanh02So3Theme {
        ManHinhEmail()
    }
}
