package com.example.core.domain.usecase

import com.example.core.domain.repository.TicketsRepository

class UpdateTicketStatusUseCase(
    private val ticketsRepository: TicketsRepository
) {

    suspend operator fun invoke(ticketId: Int, status: Int){
        ticketsRepository.updateTicketStatus(ticketId, status)
    }
}