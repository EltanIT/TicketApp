package com.example.ticketsapp.presentation.ResetPassword

sealed class ResetPasswordEvent {

    data class EnteredEmail(val value: String): ResetPasswordEvent()

    data object ResetPassword: ResetPasswordEvent()
}