package com.example.btnangcao2

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import coil.compose.AsyncImage
import coil.compose.rememberAsyncImagePainter

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MaterialTheme(
                colorScheme = darkColorScheme(
                    background = Color(0xFF1E1E2C), // Màu nền tối như hình
                    surface = Color(0xFF2D2D3A)
                )
            ) {
                CryptoScreen()
            }
        }
    }
}
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CryptoScreen(viewModel: CryptoViewModel = viewModel()) {
    val cryptoList by viewModel.cryptoList.collectAsState()
    val isLoading by viewModel.isLoading.collectAsState()

    Scaffold(
        containerColor = MaterialTheme.colorScheme.background,
        topBar = {
            CenterAlignedTopAppBar(
                title = { Text("Home", color = Color.White) },
                colors = TopAppBarDefaults.centerAlignedTopAppBarColors(
                    containerColor = Color.Transparent
                )
            )
        }
    ) { padding ->
        if (isLoading && cryptoList.isEmpty()) {
            Box(Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                CircularProgressIndicator()
            }
        } else {
            LazyColumn(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(padding)
                    .padding(horizontal = 16.dp),
                verticalArrangement = Arrangement.spacedBy(20.dp)
            ) {
                // Section 1: Breaking News Banner
                item {
                    Text("New", color = Color.LightGray, fontSize = 16.sp, modifier = Modifier.padding(bottom = 8.dp))
                    NewsBanner()
                }

                // Section 2: Favorites (Horizontal Scroll)
                item {
                    Text("Favorites", color = Color.LightGray, fontSize = 16.sp, modifier = Modifier.padding(bottom = 8.dp))
                    LazyRow(horizontalArrangement = Arrangement.spacedBy(12.dp)) {
                        items(cryptoList.take(2)) { coin -> // Lấy 2 item đầu giả làm Favorites
                            FavoriteCard(coin)
                        }
                    }
                }

                // Section 3: All Fluctuations (Vertical List)
                item {
                    Text("All Fluctuations", color = Color.LightGray, fontSize = 16.sp, modifier = Modifier.padding(vertical = 8.dp))
                }

                items(cryptoList) { coin ->
                    CryptoListItem(coin)
                }
            }
        }
    }
}


@Composable
fun NewsBanner() {
    // Giả lập ảnh Breaking News
    Card(
        shape = RoundedCornerShape(12.dp),
        modifier = Modifier.fillMaxWidth().height(150.dp)
    ) {
        Box {
            Image(
                painter = rememberAsyncImagePainter("https://t4.ftcdn.net/jpg/03/08/69/75/360_F_308697506_9dsBYHXm9FwuW0qvFHuCpERIkKPF841u.jpg"),
                contentDescription = "News",
                contentScale = ContentScale.Crop,
                modifier = Modifier.fillMaxSize()
            )
        }
    }
}

@Composable
fun FavoriteCard(coin: CryptoCoin) {
    Card(
        colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surface),
        shape = RoundedCornerShape(16.dp),
        modifier = Modifier.width(150.dp).height(100.dp)
    ) {
        Column(
            modifier = Modifier.padding(12.dp),
            verticalArrangement = Arrangement.SpaceBetween
        ) {
            Row(verticalAlignment = Alignment.CenterVertically) {
                AsyncImage(
                    model = coin.iconUrl,
                    contentDescription = null,
                    modifier = Modifier.size(24.dp)
                )
                Spacer(Modifier.width(8.dp))
                Text(coin.symbol, color = Color.White, fontWeight = FontWeight.Bold)
            }

            Text("$${coin.price}", color = Color.White, fontSize = 18.sp, fontWeight = FontWeight.Bold)

            // Giả lập chart nhỏ
            Text(
                text = "${if(coin.percentChange > 0) "+" else ""}${coin.percentChange}%",
                color = if (coin.percentChange >= 0) Color(0xFF00C853) else Color(0xFFFF5252),
                fontSize = 12.sp
            )
        }
    }
}

@Composable
fun CryptoListItem(coin: CryptoCoin) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .background(MaterialTheme.colorScheme.surface, RoundedCornerShape(12.dp))
            .padding(16.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Row(verticalAlignment = Alignment.CenterVertically) {
            AsyncImage(
                model = coin.iconUrl,
                contentDescription = null,
                modifier = Modifier.size(40.dp)
            )
            Spacer(Modifier.width(12.dp))
            Column {
                Text(coin.symbol, color = Color.White, fontWeight = FontWeight.Bold)
                Text(coin.name, color = Color.Gray, fontSize = 12.sp)
            }
        }

        Column(horizontalAlignment = Alignment.End) {
            Text("$${coin.price}", color = Color.White, fontWeight = FontWeight.Bold)
            Text(
                text = "${coin.percentChange}%",
                color = if (coin.percentChange >= 0) Color(0xFF00C853) else Color(0xFFFF5252),
                fontSize = 12.sp
            )
        }
    }
}