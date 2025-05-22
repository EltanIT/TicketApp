package com.example.ticketsapp.presentation.Settings

import com.example.core.domain.models.ExecutorModel
import com.example.core.domain.models.TicketModel
import com.example.core.domain.models.UserModel

data class StatisticData(
    val executor: UserModel,
    var tickets: List<TicketModel>
)
