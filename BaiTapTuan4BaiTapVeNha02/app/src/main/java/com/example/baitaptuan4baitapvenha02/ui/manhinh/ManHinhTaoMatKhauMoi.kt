package com.example.baitaptuan4baitapvenha02.ui.manhinh

import android.widget.Toast
import androidx.compose.material3.*
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.Visibility
import androidx.compose.material.icons.filled.VisibilityOff
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.baitaptuan4baitapvenha02.DinhNghiaDuongDan
import com.example.baitaptuan4baitapvenha02.R

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ManHinhTaoMatKhauMoi(
    boDieuHuong: NavController,
    emailNhanDuoc: String?,
    maNhanDuoc: String?
) {
    var matKhauMoi by remember { mutableStateOf("") }
    var xacNhanMatKhau by remember { mutableStateOf("") }

    // Trạng thái ẩn/hiện mật khẩu
    var hienMatKhauMoi by remember { mutableStateOf(false) }
    var hienXacNhanMatKhau by remember { mutableStateOf(false) }

    val context = LocalContext.current

    Scaffold(
        topBar = {
            TopAppBar(
                title = { },
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
                .padding(horizontal = 32.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Top
        ) {
            Spacer(modifier = Modifier.height(60.dp))

            // Logo
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
                text = "Create new password",
                style = MaterialTheme.typography.titleMedium,
                fontWeight = FontWeight.Bold
            )

            Spacer(modifier = Modifier.height(8.dp))

            Text(
                text = "Your new password must be different from\npreviously used passwords",
                style = MaterialTheme.typography.bodyMedium,
                textAlign = TextAlign.Center
            )

            Spacer(modifier = Modifier.height(24.dp))

            // Ô nhập mật khẩu mới
            OutlinedTextField(
                value = matKhauMoi,
                onValueChange = { matKhauMoi = it },
                label = { Text("Password") },
                modifier = Modifier.fillMaxWidth(),
                singleLine = true,
                visualTransformation = if (hienMatKhauMoi) VisualTransformation.None else PasswordVisualTransformation(),
                trailingIcon = {
                    IconButton(onClick = { hienMatKhauMoi = !hienMatKhauMoi }) {
                        Icon(
                            imageVector = if (hienMatKhauMoi) Icons.Filled.VisibilityOff else Icons.Filled.Visibility,
                            contentDescription = if (hienMatKhauMoi) "Ẩn mật khẩu" else "Hiện mật khẩu"
                        )
                    }
                }
            )

            Spacer(modifier = Modifier.height(16.dp))

            // Ô nhập xác nhận mật khẩu
            OutlinedTextField(
                value = xacNhanMatKhau,
                onValueChange = { xacNhanMatKhau = it },
                label = { Text("Confirm Password") },
                modifier = Modifier.fillMaxWidth(),
                singleLine = true,
                visualTransformation = if (hienXacNhanMatKhau) VisualTransformation.None else PasswordVisualTransformation(),
                trailingIcon = {
                    IconButton(onClick = { hienXacNhanMatKhau = !hienXacNhanMatKhau }) {
                        Icon(
                            imageVector = if (hienXacNhanMatKhau) Icons.Filled.VisibilityOff else Icons.Filled.Visibility,
                            contentDescription = if (hienXacNhanMatKhau) "Ẩn mật khẩu" else "Hiện mật khẩu"
                        )
                    }
                }
            )

            Spacer(modifier = Modifier.height(24.dp))

            // Nút Next
            Button(
                onClick = {
                    if (matKhauMoi != xacNhanMatKhau) {
                        Toast.makeText(context, "Mật khẩu không khớp!", Toast.LENGTH_SHORT).show()
                    } else {
                        val duongDanMoi =
                            "${DinhNghiaDuongDan.XAC_NHAN}/$emailNhanDuoc/$maNhanDuoc/$matKhauMoi"
                        boDieuHuong.navigate(duongDanMoi)
                    }
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .height(50.dp),
                colors = ButtonDefaults.buttonColors(
                    containerColor = Color(0xFF0A84FF)
                ),
                shape = RoundedCornerShape(30.dp)
            ) {
                Text(text = "Next", fontSize = 18.sp, color = Color.White)
            }
        }
    }
}
