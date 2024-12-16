package com.example.core.data.data_source.network

import com.example.core.data.model.LoginRequestModelImpl
import com.example.core.data.model.UserModelImpl
import com.example.core.domain.models.LoginRequestModel
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.POST

interface AuthApi {

    @POST("/Auth/login")
    suspend fun singIn(@Body model: LoginRequestModelImpl): Response<UserModelImpl?>
}