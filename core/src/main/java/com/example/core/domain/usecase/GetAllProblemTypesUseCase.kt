package com.example.core.domain.usecase

import com.example.core.domain.models.RequestTypeModel
import com.example.core.domain.repository.ProblemTypeRepository

class GetAllProblemTypesUseCase(
    private val repository: ProblemTypeRepository
) {

    suspend operator fun invoke(): List<RequestTypeModel>{
        return repository.getAllProblemTypes()
    }
}