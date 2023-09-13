package com.monodev.pams.component.onboarding

import android.content.Intent
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
import androidx.compose.material3.LinearProgressIndicator
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
import androidx.core.content.ContextCompat.getString
import androidx.core.content.ContextCompat.startActivity
import androidx.navigation.NavHostController
import com.monodev.pams.BuildConfig
import com.monodev.pams.MainActivity
import com.monodev.pams.R

import android.content.Context
import com.monodev.pams.api.data.DataStoreManager
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking

class StartMenu() {

    @OptIn(ExperimentalFoundationApi::class)
    @Composable
    fun test(navController: NavHostController, applicationContext: MainActivity) {
        val pagerState = rememberPagerState{5}
        val scope = rememberCoroutineScope()
        val dataStoreManager = DataStoreManager(applicationContext)

        data class ScreenList(val viewName:String, val img: Int, val comment:String, val flag:Boolean)

        val list: Array<ScreenList> = arrayOf(
            ScreenList("Welcome to PAMS",R.drawable.pams_01,"インストール\nありがとうございます",true),
            ScreenList("設定のお願い",R.drawable.pams_02,"PAMSをご利用いただけるように\n設定をお願いします",true),
            ScreenList("STEP 1",R.drawable.pams_logo,"画像のように設定をお願いします",false),
            ScreenList("STEP 2",R.drawable.pams_logo,"画像のように設定をお願いします",false),
            ScreenList("設定完了",R.drawable.pams_01,"",false),
        )

        HorizontalPager(state = pagerState) {page ->
            Column(Modifier.padding(top= 80.dp),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center
            ){
                Text(modifier=Modifier.padding(bottom = 30.dp), text = list[page].viewName,style = TextStyle(
                    fontSize = 35.sp,
                    lineHeight = 32.sp,
                    fontWeight = FontWeight(400),
                    textAlign = TextAlign.Center)
                )
                
                if(list[page].flag){
                    Image(
                        modifier = Modifier.padding(bottom = 25.dp),
                        painter = painterResource(id = list[page].img),
                        contentDescription = null,
                    )

                    Text(text = list[page].comment,style = TextStyle(
                        fontSize = 24.sp,
                        lineHeight = 32.sp,
                        fontWeight = FontWeight(400),
                        textAlign = TextAlign.Center)
                    )
                }else{
                    if(list[page].viewName === "STEP 1"){
                        Image(
                            modifier = Modifier.padding(bottom = 25.dp),
                            painter = painterResource(id = list[page].img),
                            contentDescription = null,
                        )

                        Text(text = list[page].comment,style = TextStyle(
                            fontSize = 24.sp,
                            lineHeight = 32.sp,
                            fontWeight = FontWeight(400),
                            textAlign = TextAlign.Center)
                        )

                        Button(onClick = {
                            val _intent = Intent("android.settings.ACTION_NOTIFICATION_LISTENER_SETTINGS")
                            _intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
                            startActivity(applicationContext,_intent,null)
                        }) {
                            Text(text = "設定へ行く")
                        }
                    }else if(list[page].viewName === "STEP 2"){
                        Image(
                            modifier = Modifier.padding(bottom = 25.dp),
                            painter = painterResource(id = list[page].img),
                            contentDescription = null,
                        )

                        Text(text = list[page].comment,style = TextStyle(
                            fontSize = 24.sp,
                            lineHeight = 32.sp,
                            fontWeight = FontWeight(400),
                            textAlign = TextAlign.Center)
                        )

                        Button(onClick = {
                            val _intent = Intent()
                            _intent.setAction(Settings.ACTION_APP_NOTIFICATION_SETTINGS)
                                .putExtra("android.provider.extra.APP_PACKAGE", BuildConfig.APPLICATION_ID)
                            _intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
                            startActivity(applicationContext,_intent,null);
                        }) {
                            Text(text = "設定へ行く")
                        }
                    }
                }

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
                        if(pagerState.currentPage === 4){
                            runBlocking(Dispatchers.IO) {
                                dataStoreManager.saveConfig(1)
                            }
                            navController.navigate("Home")
                        }else {
                            scope.launch {
                                pagerState.scrollToPage(pagerState.currentPage + 1)
                            }
                        }
                    }) {
                        Text(text = "Next")
                    }
                }

                LinearProgressIndicator(
                    modifier= Modifier
                        .fillMaxSize()
                        .padding(bottom = 500.dp),
                    progress = (pagerState.currentPage + 1) / 5F
                )
            }
        }
    }
}
