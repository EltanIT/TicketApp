package com.example.core.data.data_source.network

import com.example.core.data.model.TicketModelImpl
import com.example.core.domain.models.TicketModel
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.PUT
import retrofit2.http.Path

interface TicketApi {


    @GET("/Ticket/all")
    suspend fun getAllTickets(): Response<List<TicketModelImpl>>

    @GET("/Ticket/{id}")
    suspend fun getTicketById(@Path("id") id: Int): Response<TicketModelImpl>

    @GET("/Ticket/{statusId}")
    suspend fun getTicketsByStatus(@Path("statusId") statusId: Int): Response<List<TicketModelImpl>>

    @POST("/Ticket/create")
    suspend fun createTicket(@Body ticketModel: TicketModelImpl): Response<TicketModelImpl>

    @PUT("/Ticket/{id}/status/{statusId}")
    suspend fun updateTicketStatus(@Path("id") id: Int, @Path("statusId") statusId: Int)


    @PUT("/Ticket/{id}/assign/{executorId}")
    suspend fun assignExecutorToTicket(@Path("id") id: Int, @Path("executorId") executorId: Int)
}