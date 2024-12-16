package com.example.core.domain.repository

import com.example.core.domain.models.LoginRequestModel

interface AuthRepository {

    suspend fun login(model: LoginRequestModel)
    suspend fun logout()

    suspend fun checkAuth(): Boolean
    suspend fun getRole(): String?
}