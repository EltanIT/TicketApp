package com.example.core.domain.usecase

import com.example.core.domain.models.UserModel
import com.example.core.domain.repository.UserRepository

class GetMyUserUseCase(
    private val repository: UserRepository
) {


    suspend operator fun invoke(): UserModel{
        return repository.getMeUser()
    }
}