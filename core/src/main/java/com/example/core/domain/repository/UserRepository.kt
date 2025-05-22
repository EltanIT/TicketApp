package com.example.core.domain.repository

import com.example.core.domain.models.AuthResponseModel
import com.example.core.domain.models.UserModel

interface UserRepository {

    suspend fun getUserById(id: Int): UserModel

    suspend fun getMeUser(): UserModel
}