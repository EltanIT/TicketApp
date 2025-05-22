package com.example.core.data.repository

import com.example.core.data.data_source.network.AuthorsApi
import com.example.core.data.model.AuthorModelImpl
import com.example.core.data.utils.retrofitErrorHandler
import com.example.core.domain.models.AuthorModel
import com.example.core.domain.repository.AuthorRepository
import retrofit2.Response

class AuthorRepositoryImpl(
    private val api: AuthorsApi
): AuthorRepository {
    override suspend fun getAuthorByUserId(id: Int): AuthorModel {
        try{
            val response = api.getAuthorByUserId(id)
            return retrofitErrorHandler(response)
        }catch (e: Exception){
            throw Exception("Ошибка сервера")
        }
    }

    override suspend fun getAllAuthors(): List<AuthorModel> {
        val response: Response<List<AuthorModelImpl>>
        try{
            response = api.getAllAuthors()
        }catch (e: Exception){
            throw Exception("Ошибка сервера")
        }
        return retrofitErrorHandler(response)
//        return listOf(
//            AuthorModelImpl(
//                1,
//                1
//            ),
//            AuthorModelImpl(
//                2,
//                2
//            ),
//            AuthorModelImpl(
//                3,
//                3
//            ),
//        )
    }
}