package com.example.baitaptuan4baitapvenha02

// Dùng object để lưu hằng số tên các màn hình (routes)
object DinhNghiaDuongDan {
    // Tên route cơ sở
    const val QUEN_MAT_KHAU = "man_hinh_quen_mat_khau"
    const val XAC_THUC_MA = "man_hinh_xac_thuc_ma"
    const val TAO_MAT_KHAU_MOI = "man_hinh_tao_mat_khau_moi"
    const val XAC_NHAN = "man_hinh_xac_nhan"

    // Tên các tham số (arguments)
    const val ARG_EMAIL = "emailNguoiDung"
    const val ARG_MA_XAC_THUC = "maXacThuc"
    const val ARG_MAT_KHAU = "matKhauMoi"

    // Các route đầy đủ kèm tham số
    val XAC_THUC_MA_ARGS = "$XAC_THUC_MA/{$ARG_EMAIL}"
    val TAO_MAT_KHAU_MOI_ARGS = "$TAO_MAT_KHAU_MOI/{$ARG_EMAIL}/{$ARG_MA_XAC_THUC}"
    val XAC_NHAN_ARGS = "$XAC_NHAN/{$ARG_EMAIL}/{$ARG_MA_XAC_THUC}/{$ARG_MAT_KHAU}"
}