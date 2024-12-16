package com.example.core.domain.usecase

import com.example.core.domain.models.LoginRequestModel
import com.example.core.domain.repository.AuthRepository

class SignInUseCase(
    private val repository: AuthRepository
) {

    suspend operator fun invoke(email: String, password: String){
        repository.login(
            object : LoginRequestModel{
                override val login: String
                    get() = email
                override val password: String
                    get() = password

            }
        )
    }
}