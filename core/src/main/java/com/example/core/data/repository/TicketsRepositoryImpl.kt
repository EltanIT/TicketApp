package com.example.core.data.repository

import android.os.Build
import androidx.annotation.RequiresApi
import com.example.core.data.data_source.network.TicketApi
import com.example.core.data.model.TicketModelImpl
import com.example.core.data.model.TicketModelImpl.Companion.toBody
import com.example.core.data.utils.retrofitErrorHandler
import com.example.core.domain.models.TicketModel
import com.example.core.domain.repository.TicketsRepository
import kotlinx.datetime.LocalDateTime
import java.time.Month

class TicketsRepositoryImpl(
    private val api: TicketApi
): TicketsRepository {
    @RequiresApi(Build.VERSION_CODES.O)
    override suspend fun getAllTickets(): List<TicketModel> {
        val response = api.getAllTickets()
        return retrofitErrorHandler(response)
    }


    override suspend fun getTicketById(id: Int): TicketModel {
        val response = api.getTicketById(id)

        return retrofitErrorHandler(response)
    }

    override suspend fun getTicketsByStatus(status: Int): List<TicketModel> {
        val response = api.getTicketsByStatus(status)

        return retrofitErrorHandler(response)
    }

    override suspend fun assignExecutorToTicket(ticketId: Int, executorId: Int) {
        api.assignExecutorToTicket(ticketId, executorId)
    }

    override suspend fun createTicket(ticketModel: TicketModel) {
        api.createTicket(ticketModel.toBody())
    }

    override suspend fun updateTicketStatus(ticketId: Int, status: Int) {
        api.updateTicketStatus(ticketId, status)
    }
}