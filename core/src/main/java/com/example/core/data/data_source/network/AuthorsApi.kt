package com.example.core.data.data_source.network

import com.example.core.data.model.AuthorModelImpl
import com.example.core.data.model.ExecutorModelImpl
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

interface AuthorsApi {


    @GET("/Authors/user/{userId}")
    suspend fun getAuthorByUserId(@Path("userId") userId: Int): Response<AuthorModelImpl>

    @GET("/Authors/allAuthors")
    suspend fun getAllAuthors(): Response<List<AuthorModelImpl>>
}