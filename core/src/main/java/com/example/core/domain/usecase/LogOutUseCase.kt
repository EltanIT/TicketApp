package com.example.core.domain.usecase

import com.example.core.domain.repository.AuthRepository

class LogOutUseCase(
    private val repository: AuthRepository
) {

    suspend operator fun invoke(){
        repository.logout()
    }
}