package com.example.core.domain.manager

import com.example.core.domain.models.AuthResponseModel
import com.example.core.domain.models.UserModel

interface UserManager {

    suspend fun getUser(): UserModel?

    suspend fun saveUser(model: UserModel?)
}