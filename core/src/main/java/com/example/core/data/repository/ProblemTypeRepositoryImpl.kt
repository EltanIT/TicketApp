package com.example.core.data.repository

import com.example.core.data.data_source.network.RequestTypeApi
import com.example.core.data.model.DepartmentTypeModelImpl
import com.example.core.data.model.RequestTypeModelImpl
import com.example.core.data.utils.retrofitErrorHandler
import com.example.core.domain.models.RequestTypeModel
import com.example.core.domain.repository.ProblemTypeRepository
import retrofit2.Response

class ProblemTypeRepositoryImpl(
    private val api: RequestTypeApi
): ProblemTypeRepository {
    override suspend fun getAllProblemTypes(): List<RequestTypeModel> {
        val response: Response<List<RequestTypeModelImpl>>
        try{
            response = api.getAllRequests()
        }catch (e: Exception){
            throw Exception("Ошибка сервера")
        }
        return retrofitErrorHandler(response)

//        return listOf(
//            RequestTypeModelImpl(
//                1,
//                "проблема"
//            ),
//            RequestTypeModelImpl(
//                2,
//                "fdsfasd"
//            ),
//        )
    }
}