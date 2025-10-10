package com.example.baithuchanh02so2

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
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
import com.example.baithuchanh02so2.ui.theme.BaiThucHanh02So2Theme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            BaiThucHanh02So2Theme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = Color.White
                ) {
                    ManHinhKiemTraTuoi()
                }
            }
        }
    }
}

@Composable
fun ManHinhKiemTraTuoi() {
    var hoTen by remember { mutableStateOf("") }
    var tuoi by remember { mutableStateOf("") }
    var ketQua by remember { mutableStateOf("") }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        // Tiêu đề
        Text(
            text = "THỰC HÀNH 01",
            fontSize = 22.sp,
            fontWeight = FontWeight.Bold,
            textAlign = TextAlign.Center,
            modifier = Modifier.padding(bottom = 32.dp)
        )

        // Khung xám chứa ô nhập
        Box(
            modifier = Modifier
                .background(Color(0xFFDADADA), RoundedCornerShape(8.dp))
                .padding(16.dp)
                .fillMaxWidth(0.9f)
        ) {
            Column {
                // Ô nhập họ tên
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Text(
                        text = "Họ và tên",
                        modifier = Modifier.width(100.dp)
                    )
                    OutlinedTextField(
                        value = hoTen,
                        onValueChange = { hoTen = it },
                        modifier = Modifier.weight(1f),
                        singleLine = true
                    )
                }

                Spacer(modifier = Modifier.height(12.dp))

                // Ô nhập tuổi
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Text(
                        text = "Tuổi",
                        modifier = Modifier.width(100.dp)
                    )
                    OutlinedTextField(
                        value = tuoi,
                        onValueChange = { tuoi = it },
                        modifier = Modifier.weight(1f),
                        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                        singleLine = true
                    )
                }
            }
        }

        Spacer(modifier = Modifier.height(24.dp))

        // Nút Kiểm tra
        Button(
            onClick = {
                val tuoiSo = tuoi.toIntOrNull()
                if (hoTen.isBlank()) {
                    ketQua = "Vui lòng nhập họ và tên."
                } else if (tuoiSo == null || tuoiSo < 0) {
                    ketQua = "Tuổi không hợp lệ, vui lòng nhập lại."
                } else {
                    val nhomTuoi = when (tuoiSo) {
                        in 0..1 -> "Em bé"
                        in 2..5 -> "Trẻ em"
                        in 6..65 -> "Người lớn"
                        else -> "Người già"
                    }
                    ketQua = "Xin chào ${hoTen}, bạn là $nhomTuoi."
                }
            },
            colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF1565C0)),
            shape = RoundedCornerShape(8.dp),
            modifier = Modifier
                .width(150.dp)
                .height(50.dp)
        ) {
            Text("Kiểm tra", color = Color.White, fontSize = 16.sp)
        }

        Spacer(modifier = Modifier.height(24.dp))

        // Kết quả
        if (ketQua.isNotBlank()) {
            Text(
                text = ketQua,
                style = MaterialTheme.typography.bodyLarge,
                textAlign = TextAlign.Center
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewManHinh() {
    BaiThucHanh02So2Theme {
        ManHinhKiemTraTuoi()
    }
}
