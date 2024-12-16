package com.example.ticketsapp.presentation.TechSpecials

import com.example.core.domain.models.UserModel

data class TechSpecialsState(
    val users: List<UserModel> = mutableListOf(),

    val selectedUser: UserModel? = null,
    val isLoading: Boolean = false,
    val exception: String = ""
)
