package com.example.core.domain.usecase

import com.example.core.domain.models.UserModel
import com.example.core.domain.repository.ExecutorRepository
import com.example.core.domain.repository.UserRepository

class GetUserByIdUseCase(
    private val repository: UserRepository
) {


    suspend operator fun invoke(id: Int): UserModel{
        return repository.getUserById(id)
    }
}