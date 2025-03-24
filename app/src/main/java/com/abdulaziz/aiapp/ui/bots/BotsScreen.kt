
package com.abdulaziz.aiapp.ui.bots

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import coil.compose.rememberAsyncImagePainter
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import org.json.JSONArray
import java.net.URL

data class Bot(
    val id: String,
    val name: String,
    val description: String,
    val api: String,
    val key: String,
    val icon: String
)

@Composable
fun BotsScreen(onBotSelected: (Bot) -> Unit) {
    val bots = remember { mutableStateListOf<Bot>() }

    LaunchedEffect(Unit) {
        val json = withContext(Dispatchers.IO) {
            URL("https://3zooo6.github.io/assets/bots.json").readText()
        }
        val jsonArray = JSONArray(json)
        for (i in 0 until jsonArray.length()) {
            val obj = jsonArray.getJSONObject(i)
            bots.add(
                Bot(
                    obj.getString("id"),
                    obj.getString("name"),
                    obj.getString("description"),
                    obj.getString("api"),
                    obj.optString("key", ""),
                    obj.getString("icon")
                )
            )
        }
    }

    LazyColumn(modifier = Modifier.fillMaxSize().padding(16.dp)) {
        items(bots) { bot ->
            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 8.dp)
                    .clickable { onBotSelected(bot) }
            ) {
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Image(
                        painter = rememberAsyncImagePainter(bot.icon),
                        contentDescription = null,
                        modifier = Modifier
                            .size(64.dp)
                            .padding(8.dp),
                        contentScale = ContentScale.Crop
                    )
                    Column(modifier = Modifier.padding(8.dp)) {
                        Text(bot.name, style = MaterialTheme.typography.titleMedium)
                        Text(bot.description, style = MaterialTheme.typography.bodySmall)
                    }
                }
            }
        }
    }
}
