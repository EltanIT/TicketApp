package com.example.core.domain.usecase

import com.example.core.domain.models.ExecutorModel
import com.example.core.domain.repository.ExecutorRepository

class GetAllExecutorsUseCase(
    private val repository: ExecutorRepository
) {

    suspend operator fun invoke(): List<ExecutorModel>{
        return repository.getAllExecutors()
    }
}