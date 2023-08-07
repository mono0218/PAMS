package com.monodev.pams

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.filled.Home
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.OutlinedCard
import androidx.compose.material3.TextButton
import androidx.compose.ui.Alignment
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp


@Composable
fun Home(){

    Column(
        modifier = Modifier
            .padding(20.dp)
            .verticalScroll(rememberScrollState()),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Bottom
    ){
        Icon(
            Icons.Default.Home,
            contentDescription = "a"
        )
        aa()
        aa()
        aa()

    }

    Column (
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Bottom
    ){
        Box(
            contentAlignment = Alignment.BottomCenter
        ){
            NavigationBar()
        }
    }
}

@Composable
fun NavigationBar() {
    var selectedItem by remember { mutableStateOf(0) }
    val items = listOf("ホーム", "履歴", "設定")
    NavigationBar ( ){
        items.forEachIndexed { index, item ->
            NavigationBarItem(
                icon = { Icon(Icons.Filled.Favorite, contentDescription = item) },
                label = { Text(item) },
                selected = selectedItem == index,
                onClick = { selectedItem = index }
            )
        }
    }
}

@Preview
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun aa() {
    OutlinedCard(
        modifier = Modifier
            .width(1000.dp)
            .heightIn(100.dp, 200.dp)
            .padding(bottom = 20.dp),
        colors = CardDefaults.cardColors(
            containerColor = Color(0xFFFFFF),
        ),
    ) {
        Box(Modifier.fillMaxSize()) {
            Text(
                text = "Welcome to PAMS",
                style = TextStyle(
                    fontSize = 15.sp,
                    lineHeight = 20.sp,
                    fontWeight = FontWeight(500),
                    color = Color(0xFF49454F),
                    letterSpacing = 0.1.sp,
                ),
                modifier = Modifier
                    .offset(x = 15.dp, y = 12.dp)
                    .width(280.dp)
                    .height(20.dp)
            )

            Text(
                text = "インストールありがとうございます\n以下のボタンから使い方ガイドをご覧いただけます。",
                style = TextStyle(
                    fontSize = 15.sp,
                    lineHeight = 20.sp,
                    fontWeight = FontWeight(400),
                    color = Color(0xFF49454F),
                    letterSpacing = 0.25.sp,
                ),
                modifier = Modifier
                    .offset(x = 15.dp, y = 35.dp)
                    .width(280.dp)
                    .height(80.dp)
            )
            Row(
                modifier= Modifier
                    .offset(x = 0.dp, y = 128.dp)
                    .width(370.dp)
                    .height(40.dp)
                    .padding(start = 8.dp, end = 8.dp),
                horizontalArrangement = Arrangement.spacedBy(8.dp, Alignment.Start),
                verticalAlignment = Alignment.CenterVertically,
            ) {
                TextButton(onClick = { /* Do something */ }){
                    Text("使い方を見る")
                }
            }
        }
    }
}