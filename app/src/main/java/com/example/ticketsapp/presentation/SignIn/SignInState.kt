package com.example.ticketsapp.presentation.SignIn

data class SignInState(
    val email: String = "",
    val password: String = "",
    val passIsVisible: Boolean = false,

    val emailValid: Boolean = true,

    val isLoading: Boolean = false,
    val isComplete: Boolean = false,
    val exception: String = ""
)