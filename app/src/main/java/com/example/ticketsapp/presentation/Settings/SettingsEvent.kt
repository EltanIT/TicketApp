package com.example.ticketsapp.presentation.Settings

import com.example.ticketsapp.presentation.Requests.RequestsEvent

sealed class SettingsEvent {

    data object LogOut: SettingsEvent()
    data object OpenLogOutDialog: SettingsEvent()

    data object OpenMyRequests: SettingsEvent()
    data object OpenTackedRequests: SettingsEvent()

    data object OpenCompletedRequests: SettingsEvent()
    data object OpenStatistic: SettingsEvent()
    data class SelectTicket(val index: Int): SettingsEvent()


    data class UpdateTicketStatus(val status: Int): SettingsEvent()

}