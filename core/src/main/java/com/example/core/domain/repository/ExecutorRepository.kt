package com.example.core.domain.repository

import com.example.core.domain.models.ExecutorModel

interface ExecutorRepository {

    suspend fun getAllExecutors(): List<ExecutorModel>
    suspend fun getExecutorByUserId(id: Int): ExecutorModel
}