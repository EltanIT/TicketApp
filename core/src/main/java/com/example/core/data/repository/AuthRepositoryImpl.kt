package com.example.core.data.repository

import com.example.core.data.data_source.network.AuthApi
import com.example.core.data.model.LoginRequestModelImpl
import com.example.core.data.model.LoginRequestModelImpl.Companion.toBody
import com.example.core.data.utils.retrofitErrorHandler
import com.example.core.domain.manager.UserManager
import com.example.core.domain.models.LoginRequestModel
import com.example.core.domain.repository.AuthRepository

class AuthRepositoryImpl(
    private val userManager: UserManager,
    private val api: AuthApi
): AuthRepository {
    override suspend fun login(model: LoginRequestModel) {
        val response = api.singIn(model.toBody())
        val body = retrofitErrorHandler(response)


//        val body = UserModelImpl(
//            id = 2,
//            role = "Администратор",
//            login = "admin",
//            email = "g@g.g",
//            phoneNumber = "1234567890",
//            fio = "Админ"
//        )

        userManager.saveUser(body)
    }

    override suspend fun logout() {
        userManager.saveUser(null)
    }

    override suspend fun checkAuth(): Boolean {
        return userManager.getUser()!=null
    }

    override suspend fun getRole(): String? {
        return userManager.getUser()?.role
    }
}