package com.example.ticketsapp.presentation.Search

import com.example.core.domain.models.ExecutorModel
import com.example.ticketsapp.presentation.utils.TicketData

data class SearchState(
    val executor: ExecutorModel? = null,
    val role: String? = null,
    val searchValue: String = "",
    val tickets: List<TicketData> = emptyList(),

    val selectedTicket: TicketData? = null,

    val isLoading: Boolean = false,
    val exception: String = ""
)
