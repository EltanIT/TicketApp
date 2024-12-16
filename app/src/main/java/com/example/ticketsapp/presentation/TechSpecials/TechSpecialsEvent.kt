package com.example.ticketsapp.presentation.TechSpecials

import com.example.core.domain.models.UserModel

sealed class TechSpecialsEvent {

    data class SelectUser(val user: UserModel?): TechSpecialsEvent()

}