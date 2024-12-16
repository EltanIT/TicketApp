package com.example.ticketsapp.presentation.NavigationScreen

import com.example.ticketsapp.presentation.navigation.BottomNavItem
import com.example.ticketsapp.presentation.navigation.employeeNavList

data class NavigationScreenState(
    val navList: List<BottomNavItem> = employeeNavList,
    val selectedIndex: Int = 0,
    val isNotAuth: Boolean = false,
)
