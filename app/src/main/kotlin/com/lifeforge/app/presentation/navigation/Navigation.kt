package com.lifeforge.app.presentation.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.lifeforge.app.presentation.ui.screen.MainMenuScreen
import com.lifeforge.app.presentation.ui.screen.CharacterCreationScreen
import com.lifeforge.app.presentation.ui.screen.GameplayScreen

@Composable
fun LifeForgeNavHost() {
    val navController = rememberNavController()

    NavHost(
        navController = navController,
        startDestination = "main_menu"
    ) {
        composable("main_menu") {
            MainMenuScreen(
                onNewLifeClick = { navController.navigate("character_creation") },
                onContinueClick = { navController.navigate("gameplay") },
                onSettingsClick = { /* TODO: Implement settings */ },
                onExitClick = { /* TODO: Implement exit */ }
            )
        }
        composable("character_creation") {
            CharacterCreationScreen(
                onCharacterCreated = { navController.navigate("gameplay") }
            )
        }
        composable("gameplay") {
            GameplayScreen(
                onBackClick = { navController.popBackStack() }
            )
        }
    }
}
