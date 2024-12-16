package com.example.ticketsapp.presentation.Requests

import com.example.core.domain.models.ExecutorModel
import com.example.core.domain.models.UserModel
import com.example.ticketsapp.presentation.utils.TicketData
import java.util.Date

data class RequestsState(
    val executor: ExecutorModel? = null,

    val role: String? = null,
    val date: Date = Date(),

    val selectedTicket: TicketData? = null,

    val isLoading: Boolean = false,
    val exception: String = ""
)