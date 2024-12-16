package com.example.core.domain.usecase

import com.example.core.domain.models.TicketModel
import com.example.core.domain.repository.TicketsRepository

class GetAllTicketsUseCase(
    private val repository: TicketsRepository
) {

    suspend operator fun invoke(): List<TicketModel> {
        return repository.getAllTickets()
    }
}