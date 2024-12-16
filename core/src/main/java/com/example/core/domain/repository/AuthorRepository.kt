package com.example.core.domain.repository

import com.example.core.domain.models.AuthorModel

interface AuthorRepository {

    suspend fun getAuthorByUserId(id: Int): AuthorModel

    suspend fun getAllAuthors(): List<AuthorModel>
}