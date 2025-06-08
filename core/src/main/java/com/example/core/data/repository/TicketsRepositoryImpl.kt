package com.example.core.data.repository

import android.os.Build
import android.util.Log
import androidx.annotation.RequiresApi
import com.example.core.data.data_source.network.TicketApi
import com.example.core.data.model.DepartmentTypeModelImpl
import com.example.core.data.model.TicketModelImpl
import com.example.core.data.model.TicketModelImpl.Companion.toBody
import com.example.core.data.utils.retrofitErrorHandler
import com.example.core.domain.models.TicketModel
import com.example.core.domain.repository.TicketsRepository
import kotlinx.datetime.LocalDateTime
import retrofit2.Response
import java.time.Month
import java.util.Date

class TicketsRepositoryImpl(
    private val api: TicketApi
): TicketsRepository {
    @RequiresApi(Build.VERSION_CODES.O)
    override suspend fun getAllTickets(): List<TicketModel> {
        val response: Response<List<TicketModelImpl>>
        try{
            response = api.getAllTickets()
        }catch (e: Exception){
            throw Exception("Ошибка сервера")
        }
        return retrofitErrorHandler(response)

//        return listOf(
//            TicketModelImpl(
//                id = 1,
//                description = "fjkdlsjf;as",
//                authorId = 1,
//                executorId = 2,
//                type = 1,
//                departmentId = 2,
//                createdAt = LocalDateTime(1000, Month.APRIL, 1, 2, 3, 5),
//                completedAt = null,
//                status = 1
//            ),
//            TicketModelImpl(
//                id = 2,
//                description = "fdsfsdfa;as",
//                authorId = 1,
//                executorId = 2,
//                type = 1,
//                departmentId = 2,
//                createdAt = LocalDateTime(1000, Month.APRIL, 1, 2, 3, 5),
//                completedAt = null,
//                status = 1
//            ),
//            TicketModelImpl(
//                id = 2,
//                description = "fdsfsdfa;as",
//                authorId = 1,
//                executorId = 3,
//                type = 2,
//                departmentId = 1,
//                createdAt = LocalDateTime(1000, Month.APRIL, 1, 2, 3, 5),
//                completedAt = null,
//                status = 3
//            ),
//        )
    }


    override suspend fun getTicketById(id: Int): TicketModel {
        val response: Response<TicketModelImpl>
        try{
            response = api.getTicketById(id)
        }catch (e: Exception){
            throw Exception("Ошибка сервера")
        }
        return retrofitErrorHandler(response)

    }

    override suspend fun getTicketsByStatus(status: Int): List<TicketModel> {
        val response: Response<List<TicketModelImpl>>
        try{
            response = api.getTicketsByStatus(status)

        }catch (e: Exception){
            throw Exception("Ошибка сервера")
        }
        return retrofitErrorHandler(response)

    }

    override suspend fun assignExecutorToTicket(ticketId: Int, executorId: Int) {
        try{
            api.assignExecutorToTicket(ticketId, executorId)
        }catch (e: Exception){
            Log.i("retrofitLogs", e.message.toString())
            throw Exception("Ошибка сервера")
        }
    }

    override suspend fun createTicket(ticketModel: TicketModel) {
        try{
            api.createTicket(ticketModel.toBody())

        }catch (e: Exception){
            throw Exception("Ошибка сервера")
        }
    }

    override suspend fun updateTicketStatus(ticketId: Int, status: Int) {
        try{
            api.updateTicketStatus(ticketId, status)
        }catch (e: Exception){
            Log.i("retrofitLogs", e.message.toString())
            throw Exception("Ошибка сервера")
        }
    }

    override suspend fun updateTicketCompletedDate(ticketId: Int) {
        try {
            api.updateCompletedDate(ticketId, Date().toString())
        }catch (e: Exception){
            Log.i("retrofitLogs", e.message.toString())
            throw Exception("Ошибка сервера")
        }
    }
}