package com.example.core.data.repository

import com.example.core.data.data_source.network.AuthorsApi
import com.example.core.data.model.AuthorModelImpl
import com.example.core.data.utils.retrofitErrorHandler
import com.example.core.domain.models.AuthorModel
import com.example.core.domain.repository.AuthorRepository

class AuthorRepositoryImpl(
    private val api: AuthorsApi
): AuthorRepository {
    override suspend fun getAuthorByUserId(id: Int): AuthorModel {
        val response = api.getAuthorByUserId(id)
        return retrofitErrorHandler(response)
    }

    override suspend fun getAllAuthors(): List<AuthorModel> {
        val response = api.getAllAuthors()
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