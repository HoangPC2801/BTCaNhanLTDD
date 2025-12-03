package com.example.bttuan4btvnn

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
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MaterialTheme {
                AppNavigation()
            }
        }
    }
}

@Composable
fun AppNavigation() {
    val navController = rememberNavController()
    val viewModel: TaskViewModel = viewModel()

    NavHost(navController = navController, startDestination = "list_screen") {
        composable("list_screen") {
            TaskListScreen(navController, viewModel)
        }
        composable("detail_screen/{taskId}") { backStackEntry ->
            val taskId = backStackEntry.arguments?.getString("taskId")?.toIntOrNull()
            if (taskId != null) {
                LaunchedEffect(taskId) {
                    viewModel.fetchTaskDetail(taskId)
                }
                TaskDetailScreen(navController, viewModel, taskId)
            }
        }
    }
}

// ================== LIST SCREEN ==================
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TaskListScreen(navController: NavController, viewModel: TaskViewModel) {
    LaunchedEffect(Unit) {
        viewModel.fetchTasks()
    }

    Scaffold(
        topBar = {
            CenterAlignedTopAppBar(
                title = { Text("List", color = Color(0xFF2196F3), fontWeight = FontWeight.Bold) },
                navigationIcon = {
                    IconButton(onClick = { /* Handle back if needed */ }) {
                        Icon(
                            imageVector = Icons.Default.ArrowBackIosNew,
                            contentDescription = "Back",
                            tint = Color(0xFF03A9F4)
                        )
                    }
                }
            )
        }
    ) { paddingValues ->
        Box(modifier = Modifier.padding(paddingValues).fillMaxSize()) {
            if (viewModel.isLoading) {
                CircularProgressIndicator(modifier = Modifier.align(Alignment.Center))
            } else if (viewModel.taskList.isEmpty()) {
                EmptyStateView()
            } else {
                LazyColumn(
                    contentPadding = PaddingValues(16.dp),
                    verticalArrangement = Arrangement.spacedBy(12.dp)
                ) {
                    items(viewModel.taskList) { task ->
                        TaskItem(task) {
                            navController.navigate("detail_screen/${task.id}")
                        }
                    }
                }
            }
        }
    }
}

@Composable
fun EmptyStateView() {
    Column(
        modifier = Modifier.fillMaxSize().padding(32.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        // Giả lập icon Clipboard
        Icon(
            imageVector = Icons.Default.Assignment,
            contentDescription = null,
            modifier = Modifier.size(100.dp),
            tint = Color.Black
        )
        Spacer(modifier = Modifier.height(16.dp))
        Text("No Tasks Yet!", fontWeight = FontWeight.Bold, fontSize = 18.sp)
        Text(
            "Stay productive—add something to do",
            color = Color.Gray,
            textAlign = TextAlign.Center
        )
    }
}

@Composable
fun TaskItem(task: Task, onClick: () -> Unit) {
    // Màu nền ngẫu nhiên hoặc theo logic (giả lập giống ảnh)
    val cardColor = when (task.id % 3) {
        1 -> Color(0xFFBBDEFB) // Xanh nhạt
        2 -> Color(0xFFF8BBD0) // Hồng nhạt
        0 -> Color(0xFFDCEDC8) // Xanh lá nhạt
        else -> Color(0xFFE1BEE7)
    }

    Card(
        onClick = onClick,
        colors = CardDefaults.cardColors(containerColor = cardColor),
        shape = RoundedCornerShape(12.dp),
        modifier = Modifier.fillMaxWidth()
    ) {
        Row(
            modifier = Modifier.padding(16.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Column(modifier = Modifier.weight(1f)) {
                Text(
                    text = task.title,
                    fontWeight = FontWeight.Bold,
                    fontSize = 16.sp,
                    color = Color.Black
                )
                Spacer(modifier = Modifier.height(4.dp))
                Text(
                    text = task.description ?: "",
                    fontSize = 14.sp,
                    color = Color.DarkGray,
                    maxLines = 2
                )
            }
            Icon(
                imageVector = Icons.Default.ArrowForwardIos,
                contentDescription = null,
                tint = Color.Black
            )
        }
    }
}

// ================== DETAIL SCREEN ==================
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TaskDetailScreen(navController: NavController, viewModel: TaskViewModel, taskId: Int) {
    val task = viewModel.currentTask

    Scaffold(
        topBar = {
            CenterAlignedTopAppBar(
                title = { Text("Detail", color = Color(0xFF2196F3), fontWeight = FontWeight.Bold) },
                navigationIcon = {
                    IconButton(onClick = { navController.popBackStack() }) {
                        Icon(
                            imageVector = Icons.Default.ArrowBackIosNew,
                            contentDescription = "Back",
                            tint = Color(0xFF03A9F4)
                        )
                    }
                },
                actions = {
                    IconButton(onClick = {
                        viewModel.deleteTask(taskId) {
                            navController.popBackStack()
                        }
                    }) {
                        Icon(
                            imageVector = Icons.Default.Delete,
                            contentDescription = "Delete",
                            tint = Color(0xFFE64A19) // Màu cam đỏ icon thùng rác
                        )
                    }
                }
            )
        }
    ) { paddingValues ->
        if (task == null) {
            Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                CircularProgressIndicator()
            }
        } else {
            Column(
                modifier = Modifier
                    .padding(paddingValues)
                    .padding(16.dp)
                    .fillMaxSize()
            ) {
                // Title
                Text(
                    text = task.title,
                    fontWeight = FontWeight.Bold,
                    fontSize = 20.sp
                )
                Text(
                    text = task.description ?: "",
                    color = Color.Gray,
                    modifier = Modifier.padding(vertical = 8.dp)
                )

                // Info Box (Status, Priority)
                Card(
                    colors = CardDefaults.cardColors(containerColor = Color(0xFFE1BEE7)), // Tím hồng nhạt
                    shape = RoundedCornerShape(8.dp),
                    modifier = Modifier.fillMaxWidth().padding(vertical = 16.dp)
                ) {
                    Row(
                        modifier = Modifier.padding(16.dp),
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        InfoItem(icon = Icons.Default.Category, label = "Category", value = task.category)
                        InfoItem(icon = Icons.Default.PendingActions, label = "Status", value = task.status)
                        InfoItem(icon = Icons.Default.PriorityHigh, label = "Priority", value = task.priority)
                    }
                }

                // Subtasks
                Text("Subtasks", fontWeight = FontWeight.Bold, modifier = Modifier.padding(bottom = 8.dp))
                task.subtasks?.forEach { sub ->
                    SubtaskItem(sub)
                }

                Spacer(modifier = Modifier.height(16.dp))

                // Attachments
                if (!task.attachments.isNullOrEmpty()) {
                    Text("Attachments", fontWeight = FontWeight.Bold, modifier = Modifier.padding(bottom = 8.dp))
                    task.attachments.forEach { file ->
                        AttachmentItem(file)
                    }
                }
            }
        }
    }
}

@Composable
fun InfoItem(icon: androidx.compose.ui.graphics.vector.ImageVector, label: String, value: String) {
    Column(horizontalAlignment = Alignment.CenterHorizontally) {
        Row(verticalAlignment = Alignment.CenterVertically) {
            Icon(imageVector = icon, contentDescription = null, modifier = Modifier.size(16.dp))
            Spacer(modifier = Modifier.width(4.dp))
            Text(text = label, fontSize = 12.sp)
        }
        Text(text = value, fontWeight = FontWeight.Bold, fontSize = 14.sp)
    }
}

@Composable
fun SubtaskItem(subtask: Subtask) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 4.dp)
            .background(Color(0xFFEEEEEE), RoundedCornerShape(8.dp))
            .padding(12.dp)
    ) {
        Checkbox(
            checked = subtask.isCompleted,
            onCheckedChange = { /* API update status here */ },
            modifier = Modifier.size(20.dp)
        )
        Spacer(modifier = Modifier.width(12.dp))
        Text(text = subtask.title)
    }
}

@Composable
fun AttachmentItem(attachment: Attachment) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 4.dp)
            .background(Color(0xFFEEEEEE), RoundedCornerShape(8.dp))
            .padding(12.dp)
    ) {
        Icon(imageVector = Icons.Default.AttachFile, contentDescription = null)
        Spacer(modifier = Modifier.width(12.dp))
        Text(text = attachment.fileName)
    }
}