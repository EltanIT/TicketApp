package com.example.core.data.repository

import android.util.Log
import com.example.core.data.data_source.network.AuthApi
import com.example.core.data.data_source.network.PasswordResetApi
import com.example.core.data.model.LoginRequestModelImpl
import com.example.core.data.model.LoginRequestModelImpl.Companion.toBody
import com.example.core.data.model.PasswordResetRequestModelImpl.Companion.toBody
import com.example.core.data.model.UserModelImpl.Companion.toBody
import com.example.core.data.utils.decodeToken
import com.example.core.data.utils.retrofitErrorHandler
import com.example.core.domain.manager.UserManager
import com.example.core.domain.models.LoginRequestModel
import com.example.core.domain.models.PasswordResetRequestModel
import com.example.core.domain.repository.AuthRepository
import retrofit2.Response

class AuthRepositoryImpl(
    private val userManager: UserManager,
    private val api: AuthApi,
    private var passwordApi: PasswordResetApi
): AuthRepository {
    override suspend fun login(model: LoginRequestModel) {
        val response: Response<String?>
        try {
            response = api.singIn(model.toBody())
        }catch (e: Exception){
            throw Exception("Ошибка сервера")
        }

        val body = retrofitErrorHandler(response)

        var user = decodeToken(body!!)
        user = user.toBody().copy(
            login = model.login
        )


//        val body = UserModelImpl(
//            id = 2,
//            role = "Администратор",
//            login = "admin",
//            email = "g@g.g",
//            phoneNumber = "1234567890",
//            fio = "Админ"
//        )

        userManager.saveUser(user)

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

    override suspend fun resetPasswordRequest(model: PasswordResetRequestModel): String {
        val response: Response<String?>
        try {
            response = passwordApi.requestPasswordReset(model.toBody())
        }catch (e: Exception){
            Log.i("retrofitLogs", e.message.toString())
            throw Exception("Ошибка сервера")
        }
        return retrofitErrorHandler(response) ?: ""
    }
}