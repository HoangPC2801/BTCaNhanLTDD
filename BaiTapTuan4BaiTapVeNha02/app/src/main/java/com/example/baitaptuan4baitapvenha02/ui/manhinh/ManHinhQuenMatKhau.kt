package com.example.baitaptuan4baitapvenha02.ui.manhinh

import android.util.Patterns
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Email
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.baitaptuan4baitapvenha02.R
import com.example.baitaptuan4baitapvenha02.DinhNghiaDuongDan

@Composable
fun ManHinhQuenMatKhau(boDieuHuong: NavController) {

    var email by remember { mutableStateOf("") }
    var isError by remember { mutableStateOf(false) }

    fun validateEmail(email: String): Boolean {
        return Patterns.EMAIL_ADDRESS.matcher(email).matches()
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(horizontal = 28.dp),
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

        // Tiêu đề
        Text(
            text = "Forget Password?",
            fontSize = 20.sp,
            fontWeight = FontWeight.Bold
        )

        Spacer(modifier = Modifier.height(8.dp))

        Text(
            text = "Enter your Email, we will send you a verification code.",
            fontSize = 14.sp,
            textAlign = TextAlign.Center,
            color = Color.DarkGray
        )

        Spacer(modifier = Modifier.height(28.dp))

        OutlinedTextField(
            value = email,
            onValueChange = {
                email = it
                isError = it.isNotEmpty() && !validateEmail(it)
            },
            modifier = Modifier.fillMaxWidth(),
            placeholder = { Text("Your Email") },
            leadingIcon = { Icon(Icons.Default.Email, contentDescription = null) },
            singleLine = true,
            isError = isError,
            supportingText = {
                if (isError) {
                    Text(
                        text = "Không đúng định dạng email",
                        color = MaterialTheme.colorScheme.error
                    )
                }
            }
        )

        Spacer(modifier = Modifier.height(20.dp))

        Button(
            onClick = {
                val duongDanMoi = "${DinhNghiaDuongDan.XAC_THUC_MA}/$email"
                boDieuHuong.navigate(duongDanMoi)
            },
            modifier = Modifier
                .fillMaxWidth()
                .height(50.dp),
            colors = ButtonDefaults.buttonColors(
                containerColor = Color(0xFF0A84FF)
            ),
            enabled = email.isNotEmpty() && !isError
        ) {
            Text(
                text = "Next",
                fontSize = 16.sp,
                fontWeight = FontWeight.Bold,
                color = Color.White
            )
        }
    }
}