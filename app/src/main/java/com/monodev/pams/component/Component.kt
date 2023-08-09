package com.monodev.pams.component

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
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
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.graphics.vector.rememberVectorPainter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.semantics.contentDescription
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.monodev.pams.R

@Preview
@Composable
fun MainMenu(){
    
    Row(
        modifier = Modifier
            .padding(20.dp)
            .fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween
    ){

        IconButton(onClick = {}) {
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

    Column(
        modifier = Modifier
            .padding(20.dp)
            .verticalScroll(rememberScrollState()),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ){

        var checked by remember { mutableStateOf(true) }

        Image(
            painter = painterResource(R.drawable.download),
            contentDescription = "A dog image",
            modifier = Modifier
                .size(500.dp)
                .padding(bottom = 0.dp)
        )

        Switch(
            modifier = Modifier.semantics { contentDescription = "Demo" },
            checked = checked,
            onCheckedChange = { checked = it })
    }
}
@Composable
fun SettingsMenuComponent(){

}

@Composable
fun NotificationMenuComponent(){

}
