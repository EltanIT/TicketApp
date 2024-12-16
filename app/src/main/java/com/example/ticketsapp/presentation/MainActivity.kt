package com.example.ticketsapp.presentation

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.compose.rememberNavController
import com.example.ticketsapp.presentation.navigation.MainNavHost
import com.example.ticketsapp.presentation.navigation.Route
import com.example.ticketsapp.presentation.ui.theme.TicketsAppTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            TicketsAppTheme(
                darkTheme = false,
                dynamicColor = false,
            ) {
                val navController = rememberNavController()
                MainNavHost(
                    navHostController = navController,
                    startDestination = Route.SignIn.route
                )
            }
        }
    }
}