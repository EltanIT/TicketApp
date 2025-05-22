package com.example.ticketsapp.presentation.Search

import com.example.core.domain.models.DepartmentModel
import com.example.core.domain.models.DepartmentTypeModel
import com.example.core.domain.models.ExecutorModel
import com.example.ticketsapp.presentation.utils.TicketData

data class SearchState(
    val executor: ExecutorModel? = null,
    val role: String? = null,
    val searchValue: String = "",
    val tickets: List<TicketData> = emptyList(),
    val departments: List<DepartmentModel> = emptyList(),

    val selectedTicket: TicketData? = null,
    val selectedDepartment: Int? = null,

    val isLoading: Boolean = false,
    val exception: String = ""
)
