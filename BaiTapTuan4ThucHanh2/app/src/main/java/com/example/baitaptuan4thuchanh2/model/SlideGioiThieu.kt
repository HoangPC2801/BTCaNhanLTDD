package com.example.baitaptuan4thuchanh2.model

import androidx.annotation.DrawableRes

data class SlideGioiThieu(
    @DrawableRes val anhMinhHoa: Int, // Dùng @DrawableRes để Android biết đây là ID ảnh
    val tieuDe: String,
    val moTa: String
)