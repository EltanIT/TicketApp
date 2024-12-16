package com.example.ticketsapp.presentation.navigation

import androidx.annotation.DrawableRes

data class BottomNavItem(
    @DrawableRes val icon: Int,
    val route: String
)