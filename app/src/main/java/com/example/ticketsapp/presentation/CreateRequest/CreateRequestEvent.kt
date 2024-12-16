package com.example.ticketsapp.presentation.CreateRequest

sealed class CreateRequestEvent {

    data class SelectProblemTypeIndex(val value: Int): CreateRequestEvent()
    data class SelectDepartmentIndex(val value: Int): CreateRequestEvent()

    data class EnteredDescription(val value: String): CreateRequestEvent()

    data object OpenRequestDialog: CreateRequestEvent()
    data object SendRequest: CreateRequestEvent()
}