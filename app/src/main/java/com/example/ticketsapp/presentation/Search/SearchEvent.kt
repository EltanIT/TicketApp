package com.example.ticketsapp.presentation.Search

import com.example.ticketsapp.presentation.utils.TicketData

sealed class SearchEvent {

    data class EnteredSearch(val value: String): SearchEvent()
    data class SelectTicket(val index: Int): SearchEvent()
    data class UpdateTicketStatus(val status: Int): SearchEvent()
}