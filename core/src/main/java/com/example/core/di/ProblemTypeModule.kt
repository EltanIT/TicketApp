package com.example.core.di

import com.example.core.data.repository.ProblemTypeRepositoryImpl
import com.example.core.domain.repository.ProblemTypeRepository
import org.koin.dsl.module

val problemTypeModule = module {

    single<ProblemTypeRepository> {
        ProblemTypeRepositoryImpl(
            get()
        )
    }

}