package com.example.core.data.repository

import com.example.core.data.data_source.network.ExecutorApi
import com.example.core.data.model.DepartmentTypeModelImpl
import com.example.core.data.model.ExecutorModelImpl
import com.example.core.data.utils.retrofitErrorHandler
import com.example.core.domain.models.ExecutorModel
import com.example.core.domain.repository.ExecutorRepository
import retrofit2.Response

class ExecutorRepositoryImpl(
    private val api: ExecutorApi
): ExecutorRepository {
    override suspend fun getAllExecutors(): List<ExecutorModel> {
        val response: Response<List<ExecutorModelImpl>>
        try{
            response = api.getAllExecutors()
        }catch (e: Exception){
            throw Exception("Ошибка сервера")
        }
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
        val response: Response<ExecutorModelImpl>
        try{
            response = api.getExecutorByUserId(id)
        }catch (e: Exception){
            throw Exception("Ошибка сервера")
        }
        return retrofitErrorHandler(response)
    }
}