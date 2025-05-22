package com.example.core.domain.repository

import com.example.core.domain.models.LoginRequestModel
import com.example.core.domain.models.PasswordResetRequestModel

interface AuthRepository {

    suspend fun login(model: LoginRequestModel)
    suspend fun logout()

    suspend fun checkAuth(): Boolean
    suspend fun getRole(): String?



    suspend fun resetPasswordRequest(model: PasswordResetRequestModel): String
}