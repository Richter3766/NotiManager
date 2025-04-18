package com.notimanager.notimanager.presentation.ui.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.notimanager.notimanager.common.objects.Encoder.getDecodeString
import com.notimanager.notimanager.presentation.ui.screen.DateFormatterScreen
import com.notimanager.notimanager.presentation.ui.screen.FilteredListScreen
import com.notimanager.notimanager.presentation.ui.screen.MainScreen
import com.notimanager.notimanager.presentation.ui.screen.NotificationScreen
import com.notimanager.notimanager.presentation.ui.screen.NotificationSubScreen
import com.notimanager.notimanager.presentation.ui.screen.SettingScreen
import com.notimanager.notimanager.presentation.ui.screen.TitleScreen

@Composable
fun AppNavHost(navController: NavHostController) {
    NavHost(
        navController,
        startDestination = "mainScreen"
    ) {
        composable("mainScreen") {
            MainScreen(navController)
        }
        composable("SettingScreen") {
            SettingScreen(navController)
        }
        composable("titleScreen/{appName}") { backStackEntry ->
            val appName = backStackEntry.arguments?.getString("appName")
            TitleScreen(navController, appName!!)
        }
        composable("notificationScreen/{appName}/{notiName}/{isSubText}") { backStackEntry ->
            val appName = backStackEntry.arguments?.getString("appName")
            val notiName = backStackEntry.arguments?.getString("notiName")
            val isSubText = backStackEntry.arguments?.getString("isSubText")
            if (isSubText == "False") NotificationScreen(navController, appName!!, getDecodeString(notiName!!))
            else NotificationSubScreen(navController, appName!!, getDecodeString(notiName!!))
        }
        composable("FilteredListScreen") {
            FilteredListScreen(navController)
        }
        composable("DateFormatterScreen") {
            DateFormatterScreen(navController)
        }
    }
}