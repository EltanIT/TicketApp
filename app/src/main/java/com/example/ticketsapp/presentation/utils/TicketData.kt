package com.example.ticketsapp.presentation.utils

import com.example.core.domain.models.DepartmentModel
import com.example.core.domain.models.RequestTypeModel
import com.example.core.domain.models.TicketModel
import com.example.core.domain.models.UserModel

data class TicketData(
    val ticket: TicketModel? = null,
    val author: UserModel? = null,
    var executor: UserModel? = null,
    val department: DepartmentModel? = null,
    val problemType: RequestTypeModel? = null
)

val statusOrder = mapOf(
    2 to 1,  // "Новая" - высший приоритет
    3 to 2,  // "В процессе"
    1 to 3,  // "Выполнена"
    4 to 4   // "Отменена" - низший приоритет
)
