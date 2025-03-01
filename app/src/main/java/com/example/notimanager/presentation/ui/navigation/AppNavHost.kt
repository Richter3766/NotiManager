package com.example.notimanager.presentation.ui.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.notimanager.common.objects.Encoder.getDecodeString
import com.example.notimanager.presentation.ui.screen.MainScreen
import com.example.notimanager.presentation.ui.screen.NotificationScreen
import com.example.notimanager.presentation.ui.screen.NotificationSubScreen
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
        composable("titleScreen/{appName}") { backStackEntry ->
            val appName = backStackEntry.arguments?.getString("appName")
            TitleScreen(navController, appName!!)
        }
        composable("notificationScreen/{appName}/{notiName}/{isSubText}") { backStackEntry ->
            val appName = backStackEntry.arguments?.getString("appName")
            val notiName = backStackEntry.arguments?.getString("notiName")
            val isSubText = backStackEntry.arguments?.getString("isSubText")
            if (isSubText == ""){
                NotificationScreen(navController, appName!!, getDecodeString(notiName!!))
            }else{
                NotificationSubScreen(navController, appName!!, getDecodeString(notiName!!))
            }

        }
    }
}