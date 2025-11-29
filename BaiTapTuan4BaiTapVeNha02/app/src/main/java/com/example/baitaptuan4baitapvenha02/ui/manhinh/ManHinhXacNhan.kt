package com.example.baitaptuan4baitapvenha02.ui.manhinh

import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.DomainVerification
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.baitaptuan4baitapvenha02.R
// 1. Thêm import cho DinhNghiaDuongDan
import com.example.baitaptuan4baitapvenha02.DinhNghiaDuongDan

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ManHinhXacNhan(
    boDieuHuong: NavController,
    emailNhanDuoc: String?,
    maNhanDuoc: String?,
    matKhauNhanDuoc: String?
) {
    val context = LocalContext.current

    Scaffold(
        topBar = {
            TopAppBar(
                title = {},
                navigationIcon = {
                    IconButton(onClick = { boDieuHuong.navigateUp() }) {
                        Icon(
                            imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                            contentDescription = "Quay lại"
                        )
                    }
                }
            )
        }
    ) { paddingValues ->

        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
                .padding(24.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {

            Spacer(modifier = Modifier.height(20.dp))

            Image(
                painter = painterResource(id = R.drawable.logo),
                contentDescription = "UTH Logo",
                modifier = Modifier.size(250.dp)
            )

            Text(
                text = "SmartTasks",
                fontSize = 28.sp,
                fontWeight = FontWeight.Bold,
                color = Color(0xFF007BFF),
                modifier = Modifier.offset(y = (-50).dp)
            )

            Spacer(modifier = Modifier.height(20.dp))

            Text(
                text = "Confirm",
                fontSize = 20.sp,
                fontWeight = FontWeight.Bold
            )
            Text(
                text = "We are here to help you!",
                fontSize = 14.sp,
                color = Color.Gray
            )

            Spacer(modifier = Modifier.height(30.dp))

            OutlinedTextField(
                value = emailNhanDuoc ?: "",
                onValueChange = {},
                leadingIcon = { Icon(Icons.Filled.Email, contentDescription = null) },
                label = { Text("Email") },
                modifier = Modifier.fillMaxWidth(),
                enabled = false
            )
            Spacer(modifier = Modifier.height(12.dp))

            OutlinedTextField(
                value = maNhanDuoc ?: "",
                onValueChange = {},
                leadingIcon = { Icon(Icons.Filled.DomainVerification, contentDescription = null) },
                label = { Text("Code") },
                modifier = Modifier.fillMaxWidth(),
                enabled = false
            )
            Spacer(modifier = Modifier.height(12.dp))

            OutlinedTextField(
                value = matKhauNhanDuoc?.map { '•' }?.joinToString("") ?: "",
                onValueChange = {},
                leadingIcon = { Icon(Icons.Filled.Lock, contentDescription = null) },
                label = { Text("Password") },
                modifier = Modifier.fillMaxWidth(),
                enabled = false
            )

            Spacer(modifier = Modifier.height(28.dp))

            Button(
                onClick = {
                    // 2. Thay đổi logic onClick

                    // (Tùy chọn) Vẫn gọi hàm mô phỏng API
                    goiApiDatLaiMatKhau(emailNhanDuoc, maNhanDuoc, matKhauNhanDuoc)

                    // Hiển thị thông báo thành công
                    Toast.makeText(context, "Thành công! Đang quay lại...", Toast.LENGTH_SHORT).show()

                    // 3. Quay về màn hình Quên mật khẩu
                    // Giả sử route của ManHinhQuenMatKhau là DinhNghiaDuongDan.QUEN_MAT_KHAU
                    // Bạn cần đảm bảo hằng số này được định nghĩa trong file DinhNghiaDuongDan.kt
                    // và được sử dụng trong NavHost
                    boDieuHuong.popBackStack(DinhNghiaDuongDan.QUEN_MAT_KHAU, false)

                    // Nếu bạn không có route cụ thể, và ManHinhQuenMatKhau
                    // là màn hình ngay trước màn hình xác thực,
                    // bạn có thể thử popBackStack đến DinhNghiaDuongDan.XAC_THUC_MA
                    // và đặt inclusive = true để xóa luôn nó.
                    // Vd: boDieuHuong.popBackStack(DinhNghiaDuongDan.XAC_THUC_MA, true)
                    //
                    // Nhưng an toàn nhất là dùng route của ManHinhQuenMatKhau
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .height(50.dp),
                colors = ButtonDefaults.buttonColors(
                    containerColor = Color(0xFF0A84FF)
                ),
                shape = RoundedCornerShape(30.dp)
            ) {
                Text(
                    text = "Submit",
                    color = Color.White,
                    fontSize = 16.sp,
                    fontWeight = FontWeight.SemiBold
                )
            }
        }
    }
}

private fun goiApiDatLaiMatKhau(email: String?, ma: String?, matKhau: String?) {
    // Sửa lại log một chút cho rõ ràng
    println("--- GỌI API ĐẶT LẠI MẬT KHẨU ---")
    println("Email: $email")
    println("Mã: $ma")
    println("Mật khẩu mới: $matKhau")
    println("---------------------------------")
}