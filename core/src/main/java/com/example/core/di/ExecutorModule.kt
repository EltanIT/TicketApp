package com.example.core.di

import com.example.core.data.repository.ExecutorRepositoryImpl
import com.example.core.domain.repository.ExecutorRepository
import org.koin.dsl.module

val executorModule = module {

    single<ExecutorRepository> {
        ExecutorRepositoryImpl(
            get()
        )
    }

}