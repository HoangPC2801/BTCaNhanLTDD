package com.example.bttuan4btvn01

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.List
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

// 1. Model dữ liệu
data class Book(val id: String, val name: String)
data class Student(val id: String, val name: String)

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            LibraryApp()
        }
    }
}

@Composable
fun LibraryApp() {
    // 2. Dữ liệu mẫu
    val students = listOf(
        Student("SV01", "Nguyen Van A"),
        Student("SV02", "Nguyen Thi B"),
        Student("SV03", "Nguyen Van C")
    )

    // Kho sách tổng (Giả sử thư viện có 5 cuốn)
    val allBooks = listOf(
        Book("B01", "Sách 01: Lập trình Android"),
        Book("B02", "Sách 02: Cấu trúc dữ liệu"),
        Book("B03", "Sách 03: Mạng máy tính"),
        Book("B04", "Sách 04: Trí tuệ nhân tạo"),
        Book("B05", "Sách 05: Tiếng Anh chuyên ngành")
    )

    // State: Sinh viên đang chọn
    var currentStudentIndex by remember { mutableIntStateOf(0) }
    val currentStudent = students[currentStudentIndex]

    // State: Quản lý sách mượn (Dùng mutableStateMapOf để UI tự cập nhật khi thêm/xóa)
    val borrowedData = remember {
        mutableStateMapOf(
            "SV01" to setOf("B01", "B02"),
            "SV02" to setOf("B01"),
            "SV03" to setOf<String>()
        )
    }

    // State: Hiển thị Dialog thêm sách
    var showAddDialog by remember { mutableStateOf(false) }

    // Lấy danh sách ID sách của sinh viên hiện tại
    val currentStudentBookIds = borrowedData[currentStudent.id] ?: emptySet()

    Scaffold(
        bottomBar = { BottomNavigationBar() }
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .padding(innerPadding)
                .padding(16.dp)
                .fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            // Header
            Text(
                text = "Hệ thống\nQuản lý Thư viện",
                fontSize = 24.sp,
                fontWeight = FontWeight.Bold,
                textAlign = TextAlign.Center,
                modifier = Modifier.padding(bottom = 24.dp)
            )

            // Chọn sinh viên
            StudentSection(
                studentName = currentStudent.name,
                onChangeClick = {
                    currentStudentIndex = (currentStudentIndex + 1) % students.size
                }
            )

            Spacer(modifier = Modifier.height(16.dp))

            Text(
                text = "Danh sách sách",
                fontWeight = FontWeight.Bold,
                modifier = Modifier.fillMaxWidth().padding(bottom = 8.dp)
            )

            // Vùng hiển thị danh sách
            Box(
                modifier = Modifier
                    .weight(1f)
                    .fillMaxWidth()
                    .background(Color(0xFFE0E0E0), shape = RoundedCornerShape(8.dp))
                    .padding(16.dp)
            ) {
                // Lọc lấy các object Book từ list ID
                val booksDisplay = allBooks.filter { it.id in currentStudentBookIds }

                if (booksDisplay.isEmpty()) {
                    Text(
                        text = "Bạn chưa mượn quyển sách nào\nNhấn 'Thêm' để bắt đầu hành trình đọc sách!",
                        textAlign = TextAlign.Center,
                        modifier = Modifier.align(Alignment.Center),
                        color = Color.DarkGray,
                        fontSize = 14.sp
                    )
                } else {
                    LazyColumn(
                        verticalArrangement = Arrangement.spacedBy(8.dp)
                    ) {
                        items(booksDisplay) { book ->
                            BookItem(
                                bookName = book.name,
                                onDeleteClick = {
                                    // LOGIC XÓA: Tạo set mới trừ đi cuốn sách vừa xóa
                                    val newSet = currentStudentBookIds - book.id
                                    borrowedData[currentStudent.id] = newSet
                                }
                            )
                        }
                    }
                }
            }

            Spacer(modifier = Modifier.height(16.dp))

            // Nút Thêm
            Button(
                onClick = { showAddDialog = true }, // Mở Dialog
                modifier = Modifier.fillMaxWidth(0.5f),
                colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF1565C0))
            ) {
                Text("Thêm")
            }
        }
    }

    // LOGIC THÊM: Dialog chọn sách
    if (showAddDialog) {
        // Tìm những cuốn sách mà sinh viên CHƯA mượn
        val availableBooks = allBooks.filter { it.id !in currentStudentBookIds }

        AlertDialog(
            onDismissRequest = { showAddDialog = false },
            title = { Text(text = "Chọn sách để thêm") },
            text = {
                if (availableBooks.isEmpty()) {
                    Text("Sinh viên đã mượn tất cả sách!")
                } else {
                    LazyColumn {
                        items(availableBooks) { book ->
                            Text(
                                text = book.name,
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .clickable {
                                        // Thêm sách vào danh sách mượn
                                        val newSet = currentStudentBookIds + book.id
                                        borrowedData[currentStudent.id] = newSet
                                        showAddDialog = false // Đóng dialog
                                    }
                                    .padding(12.dp)
                            )
                            Divider()
                        }
                    }
                }
            },
            confirmButton = {
                TextButton(onClick = { showAddDialog = false }) {
                    Text("Đóng")
                }
            }
        )
    }
}

@Composable
fun StudentSection(studentName: String, onChangeClick: () -> Unit) {
    Column(modifier = Modifier.fillMaxWidth()) {
        Text(text = "Sinh viên", fontWeight = FontWeight.Bold)
        Spacer(modifier = Modifier.height(4.dp))
        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically
        ) {
            OutlinedTextField(
                value = studentName,
                onValueChange = {},
                readOnly = true,
                modifier = Modifier.weight(1f),
                shape = RoundedCornerShape(8.dp)
            )
            Spacer(modifier = Modifier.width(8.dp))
            Button(
                onClick = onChangeClick,
                colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF1565C0)),
                shape = RoundedCornerShape(8.dp)
            ) {
                Text("Thay đổi")
            }
        }
    }
}

@Composable
fun BookItem(bookName: String, onDeleteClick: () -> Unit) {
    Surface(
        color = Color.White,
        shape = RoundedCornerShape(8.dp),
        shadowElevation = 2.dp,
        modifier = Modifier.fillMaxWidth()
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier.padding(12.dp)
        ) {
            // Checkbox chỉ để trang trí cho giống mockup
            Checkbox(
                checked = true,
                onCheckedChange = null, // Không làm gì khi bấm vào checkbox
                colors = CheckboxDefaults.colors(checkedColor = Color(0xFFC62828))
            )

            Text(
                text = bookName,
                modifier = Modifier
                    .weight(1f)
                    .padding(start = 8.dp)
            )

            // Nút Xóa (Icon thùng rác)
            IconButton(onClick = onDeleteClick) {
                Icon(
                    imageVector = Icons.Default.Delete,
                    contentDescription = "Xóa sách",
                    tint = Color.Gray
                )
            }
        }
    }
}

@Composable
fun BottomNavigationBar() {
    NavigationBar(containerColor = Color.White) {
        NavigationBarItem(
            icon = { Icon(Icons.Default.Home, contentDescription = null) },
            label = { Text("Quản lý", color = Color(0xFF1565C0)) },
            selected = true,
            onClick = { }
        )
        NavigationBarItem(
            icon = { Icon(Icons.Default.List, contentDescription = null) },
            label = { Text("DS Sách", color = Color.Gray) },
            selected = false,
            onClick = { }
        )
        NavigationBarItem(
            icon = { Icon(Icons.Default.Person, contentDescription = null) },
            label = { Text("Sinh viên", color = Color.Gray) },
            selected = false,
            onClick = { }
        )
    }
}