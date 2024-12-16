package com.example.core.data.data_source.network

import com.example.core.data.model.UserModelImpl
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

interface UserApi {


    @GET("/User/{id}")
    suspend fun getUserById(@Path("id") id: Int): Response<UserModelImpl>
}