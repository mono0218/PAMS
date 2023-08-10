package com.monodev.pams.component

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Notifications
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedCard
import androidx.compose.material3.Switch
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.rememberVectorPainter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.semantics.contentDescription
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.monodev.pams.R


@Composable
fun MainMenu(navController: NavHostController) {
    Box(modifier= Modifier.fillMaxSize()){
        Column(
            modifier = Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally,
        ){

            var checked by remember { mutableStateOf(true) }

            Image(
                painter = painterResource(id = R.drawable.pams_logo),
                contentDescription = null,
                modifier = Modifier
                    .size(250.dp)
                    .padding(bottom = 0.dp)
            )

            Switch(
                modifier = Modifier.semantics { contentDescription = "Demo" },
                checked = checked,
                onCheckedChange = { checked = it })
        }

        Row(
            modifier = Modifier
                .padding(20.dp)
                .fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ){

            IconButton(onClick = {navController.navigate("Notification")}) {
                Icon(
                    painter = rememberVectorPainter(image = Icons.Default.Notifications),
                    contentDescription = null,
                )
            }

            IconButton(onClick = { navController.navigate("Settings") }) {
                Icon(
                    painter = rememberVectorPainter(image = Icons.Default.Settings),
                    contentDescription = null,
                )
            }
        }
    }
}

@Composable
fun SettingsMenuComponent(navController: NavHostController) {
    Column(
        modifier = Modifier.fillMaxWidth()
    ) {
        Text(
            text = "設定",
            style = TextStyle(
                fontSize = 45.sp,
                lineHeight = 52.sp,
                fontWeight = FontWeight(400),
                color = Color(0xFF000000),

                ),
            modifier= Modifier
                .padding(top = 45.dp,bottom=25.dp,start = 30.dp)
        )
    }
}

@Composable
fun NotificationMenuComponent(navController: NavHostController) {
    Column(
        modifier = Modifier.fillMaxSize()
            .verticalScroll(rememberScrollState())
    ) {
        Text(
            text = "履歴",
            style = TextStyle(
                fontSize = 45.sp,
                lineHeight = 52.sp,
                fontWeight = FontWeight(400),
                color = Color(0xFF000000),

            ),
            modifier= Modifier
                .padding(top = 45.dp,bottom=25.dp,start = 30.dp)
        )
        Column(
            modifier= Modifier
                .padding(top = 10.dp,bottom=10.dp,start = 15.dp, end = 15.dp)
        ) {
            NotificationCard()
            NotificationCard()
            NotificationCard()
            NotificationCard()
        }
    }
}

@Composable
fun NotificationCard() {
    OutlinedCard(
        modifier = Modifier
            .fillMaxWidth()
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
