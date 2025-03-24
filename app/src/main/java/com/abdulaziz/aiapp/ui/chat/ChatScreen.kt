
package com.abdulaziz.aiapp.ui.chat

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.unit.dp
import kotlinx.coroutines.launch

data class ChatMessage(val text: String, val isUser: Boolean)

@Composable
fun ChatScreen(botName: String = "Gemini") {
    val messages = remember { mutableStateListOf<ChatMessage>() }
    var input by remember { mutableStateOf(TextFieldValue("")) }
    val scope = rememberCoroutineScope()

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFF1E1E1E))
            .padding(8.dp)
    ) {
        Text(
            text = "بوت: $botName",
            style = MaterialTheme.typography.headlineSmall,
            color = Color.White,
            modifier = Modifier.padding(bottom = 8.dp)
        )

        LazyColumn(
            modifier = Modifier.weight(1f).padding(4.dp),
            reverseLayout = true
        ) {
            items(messages.reversed()) { msg ->
                val bgColor = if (msg.isUser) Color(0xFF3C3F41) else Color(0xFF2C2F33)
                val textColor = if (msg.isUser) Color.White else Color(0xFFD0D0D0)
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(4.dp)
                        .background(bgColor)
                        .padding(8.dp)
                ) {
                    Text(msg.text, color = textColor)
                }
            }
        }

        Row(modifier = Modifier.fillMaxWidth()) {
            TextField(
                value = input,
                onValueChange = { input = it },
                modifier = Modifier.weight(1f),
                placeholder = { Text("اكتب رسالتك...") },
                colors = TextFieldDefaults.textFieldColors(
                    containerColor = Color(0xFF2C2F33),
                    textColor = Color.White
                )
            )
            Button(
                onClick = {
                    if (input.text.isNotBlank()) {
                        messages.add(ChatMessage(input.text, true))
                        scope.launch {
                            // رد وهمي مؤقت
                            messages.add(ChatMessage("تم استلام: ${input.text}", false))
                        }
                        input = TextFieldValue("")
                    }
                },
                modifier = Modifier.padding(start = 8.dp)
            ) {
                Text("إرسال")
            }
        }
    }
}
