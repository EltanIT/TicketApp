package com.example.core.data.repository

import com.example.core.data.data_source.network.ExecutorApi
import com.example.core.data.model.ExecutorModelImpl
import com.example.core.data.utils.retrofitErrorHandler
import com.example.core.domain.models.ExecutorModel
import com.example.core.domain.repository.ExecutorRepository

class ExecutorRepositoryImpl(
    private val api: ExecutorApi
): ExecutorRepository {
    override suspend fun getAllExecutors(): List<ExecutorModel> {
        val response = api.getAllExecutors()
        return retrofitErrorHandler(response)

//        return listOf(
//            ExecutorModelImpl(
//                id = 2,
//                userId = 2
//            ),
//            ExecutorModelImpl(
//                id = 3,
//                userId = 3
//            ),
//            ExecutorModelImpl(
//                id = 4,
//                userId = 3
//            ),
//            ExecutorModelImpl(
//                id = 5,
//                userId = 3
//            ),
//            ExecutorModelImpl(
//                id = 6,
//                userId = 3
//            ),
//            ExecutorModelImpl(
//                id = 7,
//                userId = 3
//            ),
//            ExecutorModelImpl(
//                id = 8,
//                userId = 3
//            ),
//
//
//        )
    }

    override suspend fun getExecutorByUserId(id: Int): ExecutorModel {
        val response = api.getExecutorByUserId(id)
        return retrofitErrorHandler(response)
    }
}