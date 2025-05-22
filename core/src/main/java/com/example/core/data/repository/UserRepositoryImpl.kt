package com.example.core.data.repository

import android.accounts.AuthenticatorException
import com.example.core.data.data_source.network.UserApi
import com.example.core.data.model.DepartmentTypeModelImpl
import com.example.core.data.model.UserModelImpl
import com.example.core.data.utils.retrofitErrorHandler
import com.example.core.domain.manager.UserManager
import com.example.core.domain.models.AuthResponseModel
import com.example.core.domain.models.UserModel
import com.example.core.domain.repository.UserRepository
import retrofit2.Response

class UserRepositoryImpl(
    private val api: UserApi,
    private val manager: UserManager
): UserRepository {
    override suspend fun getUserById(id: Int): UserModel {
        val response: Response<UserModelImpl>
        try{
            response = api.getUserById(id)
        }catch (e: Exception){
            throw Exception("Ошибка сервера")
        }
        return retrofitErrorHandler(response)



//        return UserModelImpl(
//            id = 3,
//            role = "Тех. специалист",
//            login = "gera",
//            email = "g@g.g",
//            phoneNumber = "12345678901",
//            fio = "Руслан"
//        )
    }

    override suspend fun getMeUser(): UserModel {
        return manager.getUser()?: throw AuthenticatorException("Вы не вошли в аккаунт")
    }
}