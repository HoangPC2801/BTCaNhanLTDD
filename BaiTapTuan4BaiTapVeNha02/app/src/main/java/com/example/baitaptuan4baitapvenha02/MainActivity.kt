package com.example.baitaptuan4baitapvenha02

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.baitaptuan4baitapvenha02.ui.manhinh.ManHinhQuenMatKhau
import com.example.baitaptuan4baitapvenha02.ui.manhinh.ManHinhXacThucMa
import com.example.baitaptuan4baitapvenha02.ui.manhinh.ManHinhTaoMatKhauMoi
import com.example.baitaptuan4baitapvenha02.ui.manhinh.ManHinhXacNhan
import com.example.baitaptuan4baitapvenha02.ui.theme.BaiTapTuan4BaiTapVeNha02Theme

class MainActivity : ComponentActivity() { // Kế thừa từ ComponentActivity
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            BaiTapTuan4BaiTapVeNha02Theme { // Dùng theme mặc định của dự án
                Surface(color = MaterialTheme.colorScheme.background) {
                    // Đây là nơi định nghĩa toàn bộ luồng điều hướng

                    // 1. Tạo một biến điều hướng (NavController)
                    val boDieuHuong = rememberNavController()

                    // 2. Tạo NavHost (Máy chủ điều hướng)
                    NavHost(
                        navController = boDieuHuong,
                        startDestination = DinhNghiaDuongDan.QUEN_MAT_KHAU // Màn hình bắt đầu
                    ) {
                        // Màn hình 1: Quên Mật khẩu
                        composable(route = DinhNghiaDuongDan.QUEN_MAT_KHAU) {
                            ManHinhQuenMatKhau(boDieuHuong = boDieuHuong)
                        }

                        // Màn hình 2: Xác thực mã
                        composable(
                            route = DinhNghiaDuongDan.XAC_THUC_MA_ARGS,
                            arguments = listOf(
                                navArgument(DinhNghiaDuongDan.ARG_EMAIL) { type = NavType.StringType }
                            )
                        ) { entry ->
                            // Lấy tham số email ra
                            val emailNhanDuoc = entry.arguments?.getString(DinhNghiaDuongDan.ARG_EMAIL)
                            ManHinhXacThucMa(
                                boDieuHuong = boDieuHuong,
                                emailNhanDuoc = emailNhanDuoc
                            )
                        }

                        // Màn hình 3: Tạo mật khẩu mới
                        composable(
                            route = DinhNghiaDuongDan.TAO_MAT_KHAU_MOI_ARGS,
                            arguments = listOf(
                                navArgument(DinhNghiaDuongDan.ARG_EMAIL) { type = NavType.StringType },
                                navArgument(DinhNghiaDuongDan.ARG_MA_XAC_THUC) { type = NavType.StringType }
                            )
                        ) { entry ->
                            val emailNhanDuoc = entry.arguments?.getString(DinhNghiaDuongDan.ARG_EMAIL)
                            val maNhanDuoc = entry.arguments?.getString(DinhNghiaDuongDan.ARG_MA_XAC_THUC)
                            ManHinhTaoMatKhauMoi(
                                boDieuHuong = boDieuHuong,
                                emailNhanDuoc = emailNhanDuoc,
                                maNhanDuoc = maNhanDuoc
                            )
                        }

                        // Màn hình 4: Xác nhận
                        composable(
                            route = DinhNghiaDuongDan.XAC_NHAN_ARGS,
                            arguments = listOf(
                                navArgument(DinhNghiaDuongDan.ARG_EMAIL) { type = NavType.StringType },
                                navArgument(DinhNghiaDuongDan.ARG_MA_XAC_THUC) { type = NavType.StringType },
                                navArgument(DinhNghiaDuongDan.ARG_MAT_KHAU) { type = NavType.StringType }
                            )
                        ) { entry ->
                            val emailNhanDuoc = entry.arguments?.getString(DinhNghiaDuongDan.ARG_EMAIL)
                            val maNhanDuoc = entry.arguments?.getString(DinhNghiaDuongDan.ARG_MA_XAC_THUC)
                            val matKhauNhanDuoc = entry.arguments?.getString(DinhNghiaDuongDan.ARG_MAT_KHAU)
                            ManHinhXacNhan(
                                boDieuHuong = boDieuHuong, // Có thể dùng để back lại
                                emailNhanDuoc = emailNhanDuoc,
                                maNhanDuoc = maNhanDuoc,
                                matKhauNhanDuoc = matKhauNhanDuoc
                            )
                        }
                    }
                }
            }
        }
    }
}