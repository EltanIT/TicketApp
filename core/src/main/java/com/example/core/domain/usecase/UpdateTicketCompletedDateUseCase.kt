package com.example.core.domain.usecase

import com.example.core.domain.repository.TicketsRepository

class UpdateTicketCompletedDateUseCase(
    private val ticketsRepository: TicketsRepository
) {

    suspend operator fun invoke(ticketId: Int){
        ticketsRepository.updateTicketCompletedDate(ticketId)
    }
}