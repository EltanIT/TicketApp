package com.example.core.di

import com.example.core.data.repository.AuthorRepositoryImpl
import com.example.core.data.repository.ExecutorRepositoryImpl
import com.example.core.domain.repository.AuthorRepository
import com.example.core.domain.repository.ExecutorRepository
import org.koin.dsl.module

val authorModule = module {

    single<AuthorRepository> {
        AuthorRepositoryImpl(
            get()
        )
    }

}