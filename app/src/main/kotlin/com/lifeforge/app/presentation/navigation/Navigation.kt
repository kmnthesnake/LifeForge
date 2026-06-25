package com.lifeforge.app.presentation.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.lifeforge.app.presentation.ui.screen.HomeScreen
import com.lifeforge.app.presentation.ui.screen.NewLifeScreen
import com.lifeforge.app.presentation.ui.screen.ContinueScreen
import com.lifeforge.app.presentation.ui.screen.SettingsScreen

@Composable
fun LifeForgeNavHost() {
    val navController = rememberNavController()

    NavHost(
        navController = navController,
        startDestination = Route.HOME
    ) {
        composable(Route.HOME) {
            HomeScreen(
                onNewLifeClick = { navController.navigate(Route.NEW_LIFE) },
                onContinueClick = { navController.navigate(Route.CONTINUE) },
                onSettingsClick = { navController.navigate(Route.SETTINGS) },
                onExitClick = { /* Handle exit */ }
            )
        }
        composable(Route.NEW_LIFE) {
            NewLifeScreen(
                onBackClick = { navController.popBackStack() },
                onGameplayClick = { navController.navigate(Route.GAMEPLAY) }
            )
        }
        composable(Route.CONTINUE) {
            ContinueScreen(
                onBackClick = { navController.popBackStack() },
                onGameplayClick = { navController.navigate(Route.GAMEPLAY) }
            )
        }
        composable(Route.SETTINGS) {
            SettingsScreen(
                onBackClick = { navController.popBackStack() }
            )
        }
        composable(Route.GAMEPLAY) {
            // Gameplay screen placeholder
        }
    }
}