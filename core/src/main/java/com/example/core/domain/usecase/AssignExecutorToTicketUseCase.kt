package com.example.core.domain.usecase

import com.example.core.domain.repository.TicketsRepository

class AssignExecutorToTicketUseCase(
    private val ticketsRepository: TicketsRepository
) {


    suspend operator fun invoke(ticketId: Int, executorId: Int){
        ticketsRepository.assignExecutorToTicket(ticketId, executorId)
    }
}