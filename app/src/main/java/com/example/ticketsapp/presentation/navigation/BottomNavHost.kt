package com.example.ticketsapp.presentation.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.ticketsapp.presentation.CreateRequest.CreateRequestScreen
import com.example.ticketsapp.presentation.Requests.RequestsScreen
import com.example.ticketsapp.presentation.Settings.SettingsScreen
import com.example.ticketsapp.presentation.TechSpecials.TechSpecialsScreen

@Composable
fun BottomNavHost(
    modifier: Modifier = Modifier,
    mainNavController: NavController,
    navHostController: NavHostController,
    startDestination: String
) {

    NavHost(
        navController = navHostController,
        startDestination = startDestination,
        modifier = modifier
    ){
        composable(Route.Requests.route) {
            RequestsScreen()
        }
        composable(Route.TechSpecials.route) {
            TechSpecialsScreen()
        }
        composable(Route.Settings.route) {
            SettingsScreen(
                mainNavController
            )
        }

        composable(Route.CreateRequest.route) {
            CreateRequestScreen()
        }
    }

}