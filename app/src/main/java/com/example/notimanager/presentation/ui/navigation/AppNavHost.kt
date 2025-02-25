package com.example.notimanager.presentation.ui.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.notimanager.presentation.ui.screen.MainScreen
import com.example.notimanager.presentation.ui.screen.NotificationScreen
import com.example.notimanager.presentation.ui.screen.TitleScreen

@Composable
fun AppNavHost(navController: NavHostController) {
    NavHost(
        navController,
        startDestination = "mainScreen"
    ) {
        composable("mainScreen") {
            MainScreen(navController)
        }
        composable("titleScreen/{appName}/{title}") { backStackEntry ->
            val appName = backStackEntry.arguments?.getString("appName")
            val title = backStackEntry.arguments?.getString("title")
            TitleScreen(navController, appName!!, title!!)
        }
        composable("notificationScreen/{appName}/{title}") { backStackEntry ->
            val appName = backStackEntry.arguments?.getString("appName")
            val title = backStackEntry.arguments?.getString("title")
            NotificationScreen(navController, appName!!, title!!)
        }
    }
}