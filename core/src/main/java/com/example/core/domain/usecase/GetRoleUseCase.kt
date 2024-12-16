package com.example.core.domain.usecase

import com.example.core.domain.repository.AuthRepository

class GetRoleUseCase(
    private val repository: AuthRepository
) {


    suspend operator fun invoke(): String?{
        return repository.getRole()
    }
}