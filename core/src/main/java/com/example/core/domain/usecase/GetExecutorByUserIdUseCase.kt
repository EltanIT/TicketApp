package com.example.core.domain.usecase

import com.example.core.domain.models.ExecutorModel
import com.example.core.domain.repository.ExecutorRepository

class GetExecutorByUserIdUseCase(
    private val repository: ExecutorRepository
) {


    suspend operator fun invoke(userId: Int): ExecutorModel {
        return repository.getExecutorByUserId(userId)
    }
}