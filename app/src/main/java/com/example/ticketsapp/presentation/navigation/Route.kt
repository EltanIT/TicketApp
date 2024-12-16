package com.example.ticketsapp.presentation.navigation

sealed class Route(
    val route: String
) {

    data object SignIn: Route("SignIn")


    data object Requests: Route("Requests")
    data object Search: Route("Search")
    data object TechSpecials: Route("Employees")

    data object Settings: Route("Settings")


    data object CreateRequest: Route("CreateRequest")



    data object MainScreen: Route("MainScreen")
}