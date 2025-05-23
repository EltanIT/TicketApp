package com.example.core.data.data_source.network

import com.example.core.data.model.LoginRequestModelImpl
import com.example.core.data.model.PasswordResetRequestModelImpl
import com.example.core.data.model.UserModelImpl
import com.example.core.domain.models.LoginRequestModel
import okhttp3.ResponseBody
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.POST

interface PasswordResetApi {

    @POST("/api/PasswordReset/request-reset")
    suspend fun requestPasswordReset(@Body model: PasswordResetRequestModelImpl): Response<ResponseBody?>

    @POST("/PasswordReset/reset")
    suspend fun resetPassword(@Body model: LoginRequestModelImpl): Response<UserModelImpl?>
}