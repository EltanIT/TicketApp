package com.example.ticketsapp.presentation.ResetPassword

data class ResetPasswordState(
    val email: String = "",

    val emailValid: Boolean = true,

    val isLoading: Boolean = false,
    val isComplete: Boolean = false,
    val exception: String = ""
)