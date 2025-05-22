package com.example.core.data.data_source.network

import com.example.core.data.model.LoginRequestModelImpl
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.POST

interface AuthApi {

    @POST("/Auth/login")
    suspend fun singIn(@Body model: LoginRequestModelImpl): Response<String?>
}