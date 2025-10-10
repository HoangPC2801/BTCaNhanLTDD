package com.example.baithuchanh02

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.baithuchanh02.ui.theme.BaiThucHanh02Theme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            BaiThucHanh02Theme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    ManHinhSo()
                }
            }
        }
    }
}

@Composable
fun ManHinhSo() {
    // Các biến để lưu trạng thái của giao diện
    var chuoiNhap by remember { mutableStateOf("") }
    var danhSachSo by remember { mutableStateOf<List<Int>>(emptyList()) } //
    var coLoi by remember { mutableStateOf(false) } //

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = "Thực hành 02",
            fontSize = 24.sp,
            style = MaterialTheme.typography.titleLarge,
            modifier = Modifier.padding(bottom = 32.dp)
        )

        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier.fillMaxWidth()
        ) {
            OutlinedTextField(
                value = chuoiNhap,
                onValueChange = {
                    chuoiNhap = it
                    coLoi = false // Xóa lỗi khi người dùng bắt đầu nhập lại
                },
                label = { Text("Nhập vào số lượng") },
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                singleLine = true,
                modifier = Modifier.weight(1f)
            )
            Spacer(modifier = Modifier.width(8.dp))
            Button(onClick = {
                val soLuong = chuoiNhap.toIntOrNull()
                if (soLuong != null && soLuong > 0) {
                    danhSachSo = (1..soLuong).toList()
                    coLoi = false
                } else {
                    danhSachSo = emptyList()
                    coLoi = true
                }
            }) {
                Text("Tạo")
            }
        }

        if (coLoi) {
            Text(
                text = "Dữ liệu bạn nhập không hợp lệ",
                color = Color.Red,
                modifier = Modifier
                    .padding(top = 8.dp)
                    .align(Alignment.Start)
            )
        }

        Spacer(modifier = Modifier.height(16.dp))

        LazyColumn(modifier = Modifier.fillMaxWidth()) {
            items(danhSachSo) { so ->
                MucSo(number = so)
            }
        }
    }
}

@Composable
fun MucSo(number: Int) {
    Text(
        text = number.toString(),
        color = Color.White,
        fontSize = 18.sp,
        textAlign = TextAlign.Center,
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 4.dp)
            .background(color = Color(0xFFE53935), shape = RoundedCornerShape(12.dp))
            .padding(16.dp)
    )
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    BaiThucHanh02Theme {
        ManHinhSo()
    }
}