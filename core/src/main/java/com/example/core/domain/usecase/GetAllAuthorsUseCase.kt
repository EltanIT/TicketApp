package com.example.core.domain.usecase

import com.example.core.domain.models.AuthorModel
import com.example.core.domain.models.ExecutorModel
import com.example.core.domain.repository.AuthorRepository
import com.example.core.domain.repository.ExecutorRepository

class GetAllAuthorsUseCase(
    private val repository: AuthorRepository
) {

    suspend operator fun invoke(): List<AuthorModel>{
        return repository.getAllAuthors()
    }
}