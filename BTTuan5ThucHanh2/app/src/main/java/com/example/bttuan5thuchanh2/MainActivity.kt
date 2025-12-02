package com.example.bttuan5thuchanh2

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.*
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import coil.compose.AsyncImage

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ProductDetailScreen()
        }
    }
}

@Composable
fun ProductDetailScreen(viewModel: ProductViewModel = viewModel()) {
    val product by viewModel.product
    val isLoading by viewModel.isLoading
    val errorMessage by viewModel.errorMessage

    Scaffold(
        topBar = { TopBar() },
        containerColor = Color.White
    ) { paddingValues ->
        Box(
            modifier = Modifier
                .padding(paddingValues)
                .fillMaxSize()
        ) {
            when {
                isLoading -> {
                    // Đang tải API -> Hiện vòng xoay
                    CircularProgressIndicator(modifier = Modifier.align(Alignment.Center))
                }
                errorMessage != null -> {
                    // Lỗi mạng -> Hiện thông báo đỏ
                    Text(
                        text = errorMessage ?: "Lỗi không xác định",
                        color = Color.Red,
                        modifier = Modifier.align(Alignment.Center)
                    )
                }
                product != null -> {
                    // Có dữ liệu API -> Hiển thị lên giao diện
                    ProductContent(product!!)
                }
            }
        }
    }
}

@Composable
fun TopBar() {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Box(
            modifier = Modifier
                .size(40.dp)
                .clip(RoundedCornerShape(10.dp))
                .background(Color(0xFF00B0FF)),
            contentAlignment = Alignment.Center
        ) {
            Icon(
                imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                contentDescription = "Back",
                tint = Color.White
            )
        }
        Spacer(modifier = Modifier.width(16.dp))
        Text(
            text = "Product detail",
            fontSize = 20.sp,
            fontWeight = FontWeight.Bold,
            color = Color(0xFF00B0FF)
        )
    }
}

@Composable
fun ProductContent(product: Product) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(horizontal = 16.dp)
            .verticalScroll(rememberScrollState())
    ) {
        // 1. ẢNH SẢN PHẨM (Lấy từ API: product.image)
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(300.dp)
                .clip(RoundedCornerShape(20.dp))
                .background(Color(0xFFFF5722)) // Màu nền cam đỏ giống ảnh mẫu (để lót dưới)
        ) {
            AsyncImage(
                model = product.image, // URL từ API
                contentDescription = null,
                modifier = Modifier
                    .fillMaxSize()
                    .padding(20.dp), // Padding để ảnh nằm lọt vào trong khung màu
                contentScale = ContentScale.Fit
            )
        }

        Spacer(modifier = Modifier.height(20.dp))

        // 2. TÊN SẢN PHẨM (Lấy từ API: product.name)
        Text(
            text = product.name,
            fontSize = 22.sp,
            fontWeight = FontWeight.Bold,
            color = Color.Black
        )

        Spacer(modifier = Modifier.height(8.dp))

        // 3. GIÁ (Lấy từ API: product.price)
        Text(
            text = "Giá: ${product.price} $", // Hiển thị giá từ API
            fontSize = 20.sp,
            fontWeight = FontWeight.Bold,
            color = Color.Red
        )

        Spacer(modifier = Modifier.height(20.dp))

        // 4. MÔ TẢ (Lấy từ API: product.description)
        Card(
            shape = RoundedCornerShape(10.dp),
            colors = CardDefaults.cardColors(containerColor = Color(0xFFF5F5F5)),
            modifier = Modifier.fillMaxWidth()
        ) {
            Text(
                text = product.description, // Nội dung mô tả từ API
                fontSize = 14.sp,
                color = Color.Gray,
                lineHeight = 20.sp,
                modifier = Modifier.padding(16.dp),
                textAlign = androidx.compose.ui.text.style.TextAlign.Justify
            )
        }

        Spacer(modifier = Modifier.height(24.dp))
    }
}