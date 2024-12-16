package com.example.ticketsapp.presentation.NavigationScreen

sealed class NavigationScreenEvent {

    data class SelectItem(val index: Int): NavigationScreenEvent()
}