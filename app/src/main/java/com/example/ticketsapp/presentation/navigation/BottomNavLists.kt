package com.example.ticketsapp.presentation.navigation

import com.example.ticketsapp.R


val adminNavList = listOf(
    BottomNavItem(
        R.drawable.home,
        Route.Requests.route
    ),
    BottomNavItem(
        R.drawable.user,
        Route.TechSpecials.route
    ),
    BottomNavItem(
        R.drawable.setting,
        Route.Settings.route
    ),
)
val techSpecNavList = listOf(
    BottomNavItem(
        R.drawable.home,
        Route.Requests.route
    ),
    BottomNavItem(
        R.drawable.setting,
        Route.Settings.route
    ),
)
val employeeNavList = listOf(
    BottomNavItem(
        R.drawable.home,
        Route.CreateRequest.route
    ),
    BottomNavItem(
        R.drawable.setting,
        Route.Settings.route
    ),
)