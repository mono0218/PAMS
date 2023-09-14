package com.monodev.pams.component
import android.content.Context
import android.content.Intent
import android.webkit.WebView
import android.webkit.WebViewClient
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Info
import androidx.compose.material.icons.filled.Notifications
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedCard
import androidx.compose.material3.Switch
import androidx.compose.material3.Text
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
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.viewinterop.AndroidView
import androidx.navigation.NavHostController
import com.monodev.pams.api.data.AwaitDao
import com.monodev.pams.R
import com.monodev.pams.foreground.ForegroundService

class Component{
    @Composable
    fun MainMenu(navController: NavHostController, applicationContext: Context) {
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

                    checked = checked,
                    onCheckedChange = {
                        checked = it
                        val intent1 = Intent(applicationContext, ForegroundService::class.java)
                        if(checked ===true){
                            applicationContext.startForegroundService(intent1)
                        }else{
                            applicationContext.stopService(intent1)
                        }
                    }
                )
            }

            Row(
                modifier = Modifier
                    .padding(20.dp)
                    .fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween
            ){
                IconButton(onClick = { navController.navigate("WebView") }) {
                    Icon(
                        painter = rememberVectorPainter(image = Icons.Default.Info),
                        contentDescription = null,
                    )
                }

                IconButton(onClick = {navController.navigate("Notification")}) {
                    Icon(
                        painter = rememberVectorPainter(image = Icons.Default.Notifications),
                        contentDescription = null,
                    )
                }
            }
        }
    }

    @Composable
    fun NotificationMenuComponent(applicationContext: Context) {
        val result = AwaitDao().execute(applicationContext)
        Column(
            modifier = Modifier
                .fillMaxSize()
                .verticalScroll(rememberScrollState())
        ) {
            Text(
                text = "履歴",
                style = TextStyle(
                    fontSize = 35.sp,
                    lineHeight = 52.sp,
                    fontWeight = FontWeight(400),
                    color = Color(0xFF000000),

                    ),
                modifier= Modifier
                    .padding(top = 45.dp,bottom=25.dp,start = 20.dp)
            )
            Column(
                modifier= Modifier
                    .padding(top = 10.dp,bottom=10.dp,start = 15.dp, end = 15.dp)
            ) {
                result.forEach { i ->
                    NotificationCard("フィッシングメッセージを検知しました",i.content,i.time)
                }
            }
        }
    }

    @Composable
    fun NotificationCard(title:String,content:String,time:String) {
        OutlinedCard(
            modifier = Modifier
                .fillMaxSize()
                .padding(bottom = 20.dp),
            colors = CardDefaults.cardColors(
                containerColor = Color(0xFFFFFFFF),
            ),
        ) {
            Column(Modifier.fillMaxSize().padding(15.dp)) {
                Text(
                    text = title,
                    style = TextStyle(
                        fontSize = 15.sp,
                        fontWeight = FontWeight.Bold,
                        color = Color(0xFF49454F),
                    ),
                    modifier = Modifier
                        .width(280.dp)
                        .height(20.dp)
                )

                Text(
                    text = "通知内容：${content}",
                    style = TextStyle(
                        fontSize = 13.sp,
                        lineHeight = 30.sp,
                    ),
                    modifier = Modifier
                        .offset(y = 20.dp)
                        .width(280.dp)
                        .height(80.dp)
                )
                Text(
                    text = "日時：${time}",
                    style = TextStyle(
                        fontSize = 12.sp,
                        lineHeight = 30.sp,
                    ),
                    modifier = Modifier
                        .padding(bottom = 20.dp)
                        .offset(y = 35.dp)
                        .width(280.dp)
                        .height(40.dp)
                )
            }
        }
    }

    @Composable
    fun BlogWebView(){
        AndroidView(
            factory = ::WebView,
            update = { webView ->
                webView.webViewClient = WebViewClient()
                webView.loadUrl("https://blog.monodev.cloud")
            }
        )
    }
}

