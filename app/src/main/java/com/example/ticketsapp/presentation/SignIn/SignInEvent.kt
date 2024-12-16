package com.example.ticketsapp.presentation.SignIn

sealed class SignInEvent {

    data class EnteredEmail(val value: String): SignInEvent()
    data class EnteredPassword(val value: String): SignInEvent()

    data object ChangePasswordVisible: SignInEvent()
    data object Login: SignInEvent()
}