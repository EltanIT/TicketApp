package com.example.core.domain.repository

import com.example.core.domain.models.TicketModel

interface TicketsRepository {

    suspend fun getAllTickets(): List<TicketModel>

    suspend fun createTicket(
        ticketModel: TicketModel
    )

    suspend fun updateTicketStatus(ticketId: Int, status: Int)
    suspend fun updateTicketCompletedDate(ticketId: Int)

    suspend fun getTicketById(id: Int): TicketModel
    suspend fun getTicketsByStatus(status: Int): List<TicketModel>

    suspend fun assignExecutorToTicket(ticketId: Int, executorId: Int)
}