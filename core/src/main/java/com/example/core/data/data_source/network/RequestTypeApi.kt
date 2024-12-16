package com.example.core.data.data_source.network

import com.example.core.data.model.RequestTypeModelImpl
import retrofit2.Response
import retrofit2.http.GET

interface RequestTypeApi {


    @GET("/RequestType/allRequest")
    suspend fun getAllRequests(): Response<List<RequestTypeModelImpl>>
}