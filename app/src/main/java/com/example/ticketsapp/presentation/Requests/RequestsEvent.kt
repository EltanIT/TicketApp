package com.example.ticketsapp.presentation.Requests

import com.example.ticketsapp.presentation.utils.TicketData

sealed class RequestsEvent {

    data object OpenDateChangerDialog: RequestsEvent()

    data class SelectTicket(val ticket: TicketData?): RequestsEvent()

    data class UpdateTicketStatus(val status: Int): RequestsEvent()
}