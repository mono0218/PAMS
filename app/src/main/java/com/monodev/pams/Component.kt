package com.monodev.pams

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Notifications
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Switch
import androidx.compose.material3.Text
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.graphics.vector.rememberVectorPainter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.semantics.contentDescription
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@Preview
@Composable
fun Home(){
    Column {
        MainMenu()
        Submenu()
    }
}

@Composable
fun MainMenu(){
    Column {
        Row(
            modifier = Modifier
                .padding(15.dp)
                .verticalScroll(rememberScrollState()),
            horizontalArrangement = Arrangement.SpaceBetween

        ){
            IconButton(onClick = { /*TODO*/ }) {
                Icon(
                    painter = rememberVectorPainter(image = Icons.Default.Notifications),
                    contentDescription = null,
                )
            }
            IconButton(onClick = { /*TODO*/ }) {
                Icon(
                    painter = rememberVectorPainter(image = Icons.Default.Settings),
                    contentDescription = null,
                )
            }
        }
    }
}

@Composable
fun Submenu(){
    Column(
        modifier = Modifier
            .padding(20.dp)
            .verticalScroll(rememberScrollState()),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Bottom
    ){
        Image(
            painter = painterResource(id = R.raw),
            contentDescription = stringResource(id = null),
            contentScale = ContentScale.Fit,
        )
        var checked by remember { mutableStateOf(true) }
        Switch(
            modifier = Modifier.semantics { contentDescription = "Demo" }.size(100.dp),
            checked = checked,
            onCheckedChange = { checked = it })
    }
}
