package com.example.ticketsapp.presentation.navigation

import androidx.compose.foundation.background
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.ticketsapp.presentation.NavigationScreen.NavigationScreen
import com.example.ticketsapp.presentation.ResetPassword.ResetPasswordScreen
import com.example.ticketsapp.presentation.SignIn.SignInScreen

@Composable
fun MainNavHost(
    navHostController: NavHostController,
    startDestination: String,
) {
    NavHost(
        navController = navHostController,
        startDestination = startDestination,
        modifier = Modifier.background(MaterialTheme.colorScheme.background)
    ){
        composable(Route.SignIn.route) {
            SignInScreen(
                navController =  navHostController
            )
        }

        composable(Route.ResetPassword.route) {
            ResetPasswordScreen(
                navController =  navHostController
            )
        }

        composable(Route.MainScreen.route) {
            NavigationScreen(
                navHostController,
            )
        }
    }
    
}