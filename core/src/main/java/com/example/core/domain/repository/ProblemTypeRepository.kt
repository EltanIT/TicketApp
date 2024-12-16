package com.example.core.domain.repository

import com.example.core.domain.models.RequestTypeModel

interface ProblemTypeRepository {

    suspend fun getAllProblemTypes(): List<RequestTypeModel>
}