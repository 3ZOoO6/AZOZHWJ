
package com.abdulaziz.aiapp.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.abdulaziz.aiapp.ui.chat.ChatScreen
import com.abdulaziz.aiapp.ui.contact.ContactScreen
import com.abdulaziz.aiapp.ui.netflix.NetflixScreen
import com.abdulaziz.aiapp.ui.bots.BotsScreen
import com.abdulaziz.aiapp.ui.bots.Bot

@Composable
fun NavigationGraph(navController: NavHostController) {
    NavHost(navController, startDestination = "home") {
        composable("home") {
            NetflixScreen()
        }
        composable("bots") {
            BotsScreen(onBotSelected = {
                navController.navigate("chat/${it.name}")
            })
        }
        composable("chat/{botName}") { backStackEntry ->
            val botName = backStackEntry.arguments?.getString("botName") ?: "Gemini"
            ChatScreen(botName = botName)
        }
        composable("contact") {
            ContactScreen()
        }
    }
}
