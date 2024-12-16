package com.example.ticketsapp.presentation.Settings

import com.example.core.domain.models.ExecutorModel
import com.example.core.domain.models.UserModel
import com.example.ticketsapp.presentation.utils.TicketData

data class SettingsState(
    val executor: ExecutorModel? = null,
    val user: UserModel? = null,

    val tickets: List<TicketData> = emptyList(),
    val statistic: List<StatisticData> = emptyList(),

    val selectedTicket: TicketData? = null,
    val countCompletedTickets: Int = 0,

    val logOutIsComplete: Boolean = false,

    val logOutDialogIsOpen: Boolean = false,
    val ticketsIsOpen: Boolean = false,
    val statisticIsOpen: Boolean = false,

    val exception: String = "",

    val actionsType: RoleActions = RoleActions.EmployeeActions.MyRequests
)