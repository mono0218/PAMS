package com.monodev.pams.component.onboarding

import android.Manifest
import android.content.Intent
import android.os.Build.VERSION.SDK_INT
import android.provider.Settings
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.material3.Button
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.monodev.pams.R
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.pager.PagerState
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.core.app.NotificationManagerCompat
import androidx.core.content.ContextCompat
import coil.ImageLoader
import coil.compose.AsyncImage
import coil.decode.GifDecoder
import coil.decode.ImageDecoderDecoder
import coil.request.ImageRequest
import com.monodev.pams.BuildConfig
import com.monodev.pams.MainActivity
import com.monodev.pams.api.data.DataStoreManager
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking

class StartMenu {
    data class ScreenList(val viewName:String, val img: Int, val comment:String)
    @OptIn(ExperimentalFoundationApi::class)
    @Composable
    fun test(navController: NavHostController, applicationContext: MainActivity) {
        val pagerState = rememberPagerState{6}
        val scope = rememberCoroutineScope()



        val list: Array<ScreenList> = arrayOf(
            ScreenList("Welcome to PAMS",R.drawable.pams_01,"インストール\nありがとうございます"),
            ScreenList("設定のお願い",R.drawable.pams_02,"PAMSをご利用いただけるように\n設定をお願いします"),
            ScreenList("通知受信設定",R.raw.step1,"動画のように設定をお願いします"),
            ScreenList("通知設定",R.raw.step2,"動画のように設定をお願いします"),
            ScreenList("設定完了",R.drawable.pams_01,""),
            ScreenList("使い方",R.drawable.img,"")
        )

        HorizontalPager(state = pagerState) {page ->
            when (page){
                0 -> Screen1(data = list[0], pagerState = pagerState, navController = navController, scope = scope, applicationContext = applicationContext)
                1 -> Screen1(data = list[1], pagerState = pagerState, navController = navController, scope = scope, applicationContext = applicationContext)
                2 -> Screen2(data = list[2], pagerState = pagerState, navController = navController, scope = scope, applicationContext = applicationContext, pName = Settings.ACTION_NOTIFICATION_LISTENER_SETTINGS)
                3 -> Screen2(data = list[3], pagerState = pagerState, navController = navController, scope = scope, applicationContext = applicationContext, pName = Settings.ACTION_APP_NOTIFICATION_SETTINGS)
                4 -> Screen3(data = list[4], pagerState = pagerState, navController = navController, scope = scope, applicationContext = applicationContext)
                5 -> Screen4(data = list[5], pagerState = pagerState, navController = navController, scope = scope, applicationContext = applicationContext)
            }
        }
    }

    @OptIn(ExperimentalFoundationApi::class)
    @Composable
    fun Screen1(data: ScreenList, pagerState: PagerState, navController:NavHostController, scope: CoroutineScope, applicationContext: MainActivity){
        val dataStoreManager = DataStoreManager(applicationContext)
        Box{
            Column(Modifier.fillMaxSize(),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center
            ){
                Text(modifier=Modifier.padding(bottom = 25.dp), text =data.viewName,style = TextStyle(
                    fontSize = 35.sp,
                    lineHeight = 32.sp,
                    fontWeight = FontWeight(400),
                    textAlign = TextAlign.Center)
                )
                Image(
                    modifier = Modifier.padding(bottom = 25.dp),
                    painter = painterResource(id = data.img),
                    contentDescription = null,
                )

                Text(text = data.comment,style = TextStyle(
                    fontSize = 24.sp,
                    lineHeight = 32.sp,
                    fontWeight = FontWeight(400),
                    textAlign = TextAlign.Center)
                )
            }

            OnboardingControlloer(
                scope = scope,
                pagerState = pagerState,
                navController = navController,
                dataStoreManager = dataStoreManager,
                applicationContext = applicationContext
            )
        }
    }

    @OptIn(ExperimentalFoundationApi::class)
    @Composable
    fun Screen2(
        data: ScreenList, pagerState: PagerState, navController:NavHostController, scope: CoroutineScope, applicationContext: MainActivity,
        pName:String){
        val dataStoreManager = DataStoreManager(applicationContext)
        Box{
            Column(Modifier.fillMaxSize(),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center
            ){
                Text(modifier=Modifier.padding(bottom = 25.dp), text =data.viewName,style = TextStyle(
                    fontSize = 35.sp,
                    lineHeight = 32.sp,
                    fontWeight = FontWeight(400),
                    textAlign = TextAlign.Center)
                )
                step(data.img)
                Text(text = data.comment,style = TextStyle(
                    fontSize = 24.sp,
                    lineHeight = 32.sp,
                    fontWeight = FontWeight(400),
                    textAlign = TextAlign.Center)
                )

                Button(onClick = {
                    val intent1 = Intent(pName)
                        .putExtra("android.provider.extra.APP_PACKAGE", BuildConfig.APPLICATION_ID)
                    intent1.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
                    ContextCompat.startActivity(applicationContext, intent1, null)
                }) {
                    Text(text = "設定へ行く")
                }
            }

            OnboardingControlloer(
                scope = scope,
                pagerState = pagerState,
                navController = navController,
                dataStoreManager = dataStoreManager,
                applicationContext = applicationContext
            )
        }
    }

    @OptIn(ExperimentalFoundationApi::class)
    @Composable
    fun Screen3(data: ScreenList, pagerState: PagerState, navController:NavHostController, scope: CoroutineScope, applicationContext: MainActivity){
        val permission1 = NotificationManagerCompat.getEnabledListenerPackages(applicationContext).contains(applicationContext.packageName)
        val permission2 =ContextCompat.checkSelfPermission(applicationContext, Manifest.permission.POST_NOTIFICATIONS)

        print(permission1)
        print(permission2)

        if(permission1 === false||permission2 === -1){

            Column(Modifier.fillMaxSize(),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center
            ) {
                Text(
                    modifier = Modifier.padding(bottom = 25.dp),
                    text = "設定失敗",
                    style = TextStyle(
                        fontSize = 35.sp,
                        lineHeight = 32.sp,
                        fontWeight = FontWeight(400),
                        textAlign = TextAlign.Center
                    )
                )
                Image(
                    modifier = Modifier.padding(bottom = 25.dp),
                    painter = painterResource(id = data.img),
                    contentDescription = null,
                )

                Text(
                    text = "初期設定に失敗しました。\n下のボタンを押して設定をやり直してください", style = TextStyle(
                        fontSize = 18.sp,
                        lineHeight = 32.sp,
                        fontWeight = FontWeight(400),
                        textAlign = TextAlign.Center
                    )
                )

                Button(modifier= Modifier.padding(15.dp),onClick = {
                    scope.launch {
                        pagerState.scrollToPage(0)
                    }
                }) {
                    Text(text = "やり直す")
                }
            }

        }else{
            Screen1(data = data, pagerState = pagerState, navController = navController, scope = scope, applicationContext = applicationContext)
        }
    }
    @Composable
    @OptIn(ExperimentalFoundationApi::class)
    fun Screen4(data: ScreenList, pagerState: PagerState, navController:NavHostController, scope: CoroutineScope, applicationContext: MainActivity){
        val dataStoreManager = DataStoreManager(applicationContext)
        Box{
            Column(Modifier.fillMaxSize(),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center
            ){
                Text(modifier=Modifier.padding(bottom = 25.dp), text =data.viewName,style = TextStyle(
                    fontSize = 35.sp,
                    lineHeight = 32.sp,
                    fontWeight = FontWeight(400),
                    textAlign = TextAlign.Center)
                )
                Image(
                    modifier = Modifier.padding(bottom = 50.dp).size(550.dp),
                    painter = painterResource(id = data.img),
                    contentDescription = null,
                )
            }

            OnboardingControlloer(
                scope = scope,
                pagerState = pagerState,
                navController = navController,
                dataStoreManager = dataStoreManager,
                applicationContext = applicationContext
            )
        }
    }

    @OptIn(ExperimentalFoundationApi::class)
    @Composable
    fun OnboardingControlloer(
        scope: CoroutineScope,
        pagerState: PagerState,
        navController: NavHostController,
        dataStoreManager: DataStoreManager,
        applicationContext: MainActivity
    ){
        Row(modifier = Modifier
            .fillMaxSize()
            .padding(15.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.Bottom){

            OutlinedButton(onClick = {
                scope.launch {
                    pagerState.scrollToPage(pagerState.currentPage - 1)
                }
            }) {
                Text(text = "Back")
            }

            Button(onClick = {
                println(pagerState.currentPage)
                if(pagerState.currentPage === 5){
                    navController.navigate("Home")
                    MainActivity().restart(applicationContext)
                    runBlocking {
                        dataStoreManager.saveConfig(1)
                    }
                }else {
                    scope.launch {
                        pagerState.scrollToPage(pagerState.currentPage + 1)
                    }
                }
            }) {
                Text(text = "Next")
            }
        }
    }

    @Composable
    fun step(data:Int) {
        Column(
            Modifier.size(450.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            val context = LocalContext.current
            val imageLoader = ImageLoader.Builder(context)
                .components {
                    if (SDK_INT >= 28) {
                        add(ImageDecoderDecoder.Factory())
                    } else {
                        add(GifDecoder.Factory())
                    }
                }
                .build()
            AsyncImage(
                modifier = Modifier,
                model = ImageRequest.Builder(LocalContext.current)
                    .data(data)
                    .crossfade(true)
                    .build(),
                imageLoader = imageLoader,
                contentDescription = "",
                contentScale = ContentScale.Fit
            )
        }
    }
}

