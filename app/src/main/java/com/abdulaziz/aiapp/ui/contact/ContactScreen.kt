
package com.abdulaziz.aiapp.ui.contact

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalUriHandler
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun ContactScreen() {
    val uriHandler = LocalUriHandler.current

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(24.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text("اتصل بي", fontSize = 28.sp, fontWeight = FontWeight.Bold)

        Spacer(modifier = Modifier.height(24.dp))

        Button(onClick = {
            uriHandler.openUri("https://www.snapchat.com/add/bx90_9?share_id=advDbNuiL0c&locale=ar-EG")
        }) {
            Text("Snapchat")
        }

        Spacer(modifier = Modifier.height(12.dp))

        Button(onClick = {
            uriHandler.openUri("mailto:abdulzizsaleme@gmail.com")
        }) {
            Text("إرسال بريد إلكتروني")
        }
    }
}
