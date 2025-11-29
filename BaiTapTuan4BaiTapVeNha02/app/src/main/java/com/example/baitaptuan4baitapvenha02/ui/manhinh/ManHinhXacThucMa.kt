package com.example.baitaptuan4baitapvenha02.ui.manhinh

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.Visibility
import androidx.compose.material.icons.filled.VisibilityOff
import androidx.compose.material3.*
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.baitaptuan4baitapvenha02.DinhNghiaDuongDan
import com.example.baitaptuan4baitapvenha02.R

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ManHinhXacThucMa(
    boDieuHuong: NavController,
    emailNhanDuoc: String?
) {
    var maXacThuc by remember { mutableStateOf("") }
    var hienMa by remember { mutableStateOf(false) } // trạng thái ẩn/hiện mã

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
    ) { padding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(padding)
                .padding(horizontal = 24.dp),
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
                text = "Verify Code",
                fontSize = 20.sp,
                fontWeight = FontWeight.Bold
            )

            Spacer(modifier = Modifier.height(4.dp))

            Text(
                text = "Enter the code we just sent you on your registered Email",
                fontSize = 14.sp
            )

            Spacer(modifier = Modifier.height(28.dp))

            // Nhập mã có nút ẩn/hiện
            OutlinedTextField(
                value = maXacThuc,
                onValueChange = {
                    if (it.length <= 5) maXacThuc = it
                },
                placeholder = { Text("*****") },
                modifier = Modifier.fillMaxWidth(),
                singleLine = true,
                visualTransformation = if (hienMa) VisualTransformation.None else PasswordVisualTransformation(),
                trailingIcon = {
                    IconButton(onClick = { hienMa = !hienMa }) {
                        Icon(
                            imageVector = if (hienMa) Icons.Filled.VisibilityOff else Icons.Filled.Visibility,
                            contentDescription = if (hienMa) "Ẩn mã" else "Hiện mã"
                        )
                    }
                }
            )

            Spacer(modifier = Modifier.height(32.dp))

            Button(
                onClick = {
                    boDieuHuong.navigate("${DinhNghiaDuongDan.TAO_MAT_KHAU_MOI}/$emailNhanDuoc/$maXacThuc")
                },
                enabled = maXacThuc.length == 5,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(50.dp),
                colors = ButtonDefaults.buttonColors(
                    containerColor = Color(0xFF0A84FF)
                ),
                shape = RoundedCornerShape(30.dp)
            ) {
                Text(
                    text = "Next",
                    fontSize = 18.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color.White,
                    modifier = Modifier.padding(horizontal = 24.dp)
                )
            }
        }
    }
}
