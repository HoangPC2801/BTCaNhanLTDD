package com.example.baitaptuan4thuchanh2.ui_screen

import androidx.compose.foundation.background
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.ButtonDefaults
import androidx.compose.ui.graphics.Color
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.material3.Button
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.baitaptuan4thuchanh2.R
import com.example.baitaptuan4thuchanh2.model.SlideGioiThieu
import kotlinx.coroutines.launch

val danhSachSlide = listOf(
    SlideGioiThieu(
        R.drawable.anh_slide_1,
        "Easy Time Management",
        "With management based on priority and daily tasks, it will give you convenience in managing and determening the task that must be done first"
    ),
    SlideGioiThieu(
        R.drawable.anh_slide_2,
        "Increase Work Effectiveness",
        "Time management and the determination of more important tasks..."
    ),
    SlideGioiThieu(
        R.drawable.anh_slide_3,
        "Reminder Notification",
        "The advantage of this application is that it also provides reminders..."
    )
)

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun ManHinhGioiThieu(navController: NavController) {

    // 1. Tạo State cho Pager
    val pagerState = rememberPagerState(
        initialPage = 0,
        initialPageOffsetFraction = 0f
    ) {
        // Trả về tổng số trang
        danhSachSlide.size
    }

    // 2. Tạo Coroutine Scope để điều khiển Pager
    val coroutineScope = rememberCoroutineScope()

    // 3. Hàm điều hướng cuối cùng
    fun dieuHuongDenManHinhChinh() {
        navController.navigate("man_hinh_chinh") {
            popUpTo("man_hinh_gioi_thieu") { inclusive = true }
        }
    }

    // 4. Bố cục giao diện
    Column(modifier = Modifier.fillMaxSize()) {
        // Nút "skip"
        Box(modifier = Modifier.fillMaxWidth(), contentAlignment = Alignment.TopEnd) {
            TextButton(
                onClick = { dieuHuongDenManHinhChinh() },
                modifier = Modifier.padding(16.dp)
            ) {
                Text(text = "skip")
            }
        }

        // Pager (Phần vuốt)
        HorizontalPager(
            state = pagerState,
            modifier = Modifier
                .fillMaxWidth()
                .weight(1f) // Chiếm hết phần không gian còn lại
        ) { trang ->
            // Gọi Composable để hiển thị 1 slide
            TrangSlide(slide = danhSachSlide[trang])
        }

        // Hàng chứa các nút điều khiển (Back, Next, Get Started)
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp, vertical = 24.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            // --- Nút Back ---
            if (pagerState.currentPage > 0) {
                IconButton(
                    onClick = {
                        coroutineScope.launch {
                            pagerState.animateScrollToPage(pagerState.currentPage - 1)
                        }
                    },
                    modifier = Modifier
                        .size(48.dp)
                        .background(
                            color = Color(0xFF2196F3),
                            shape = CircleShape
                        )
                ) {
                    Icon(
                        painter = painterResource(id = R.drawable.ic_arrow_back),
                        contentDescription = "Back",
                        tint = Color.White,
                        modifier = Modifier.size(28.dp)
                    )
                }
            } else {
                Spacer(modifier = Modifier.size(48.dp))
            }

            // --- Nút Next / Get Started ---
            val laTrangCuoi = pagerState.currentPage == danhSachSlide.size - 1
            Button(
                onClick = {
                    if (laTrangCuoi) {
                        dieuHuongDenManHinhChinh()
                    } else {
                        coroutineScope.launch {
                            pagerState.animateScrollToPage(pagerState.currentPage + 1)
                        }
                    }
                },
                modifier = Modifier.height(48.dp),
                colors = ButtonDefaults.buttonColors(
                    containerColor = Color(0xFF2196F3)
                ),
                shape = RoundedCornerShape(50.dp)
            ) {
                Text(
                    text = if (laTrangCuoi) "Get Started" else "Next",
                    fontSize = 18.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color.White,
                    modifier = Modifier.padding(horizontal = 24.dp)
                )
            }
        }

    }
}

@Composable
fun TrangSlide(slide: SlideGioiThieu) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(horizontal = 32.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Image(
            painter = painterResource(id = slide.anhMinhHoa),
            contentDescription = slide.tieuDe,
            modifier = Modifier.size(300.dp) // Kích thước ảnh
        )
        Spacer(modifier = Modifier.height(24.dp))
        Text(
            text = slide.tieuDe,
            fontSize = 20.sp,
            fontWeight = FontWeight.Bold
        )
        Spacer(modifier = Modifier.height(16.dp))
        Text(
            text = slide.moTa,
            textAlign = TextAlign.Center
        )
    }
}