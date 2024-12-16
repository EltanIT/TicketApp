package com.example.core.data.data_source.network

import com.example.core.data.model.ExecutorModelImpl
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

interface ExecutorApi {


    @GET("/Executors/allExecutors")
    suspend fun getAllExecutors(): Response<List<ExecutorModelImpl>>

    @GET("/Executors/user/{userId}")
    suspend fun getExecutorByUserId(@Path("userId") id: Int): Response<ExecutorModelImpl>
}