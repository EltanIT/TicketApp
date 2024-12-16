package com.example.ticketsapp.presentation.utils

import androidx.compose.ui.graphics.Color

data class RequestStatus(
    val id: Int,
    val name: String,
    val color: Color
)

val requestStatuses = listOf(
    RequestStatus(
        1, "Выполнена", Color(0xffF22E2E)
    ),
    RequestStatus(
        2, "Новая", Color(0xff2EF242)
    ),
    RequestStatus(
        3, "В процессе", Color(0xffBAB549)
    ),
    RequestStatus(
        4, "Отменена", Color.Black
    ),
)