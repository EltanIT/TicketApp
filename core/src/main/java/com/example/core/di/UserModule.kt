package com.example.core.di

import com.example.core.data.repository.UserRepositoryImpl
import com.example.core.domain.repository.UserRepository
import org.koin.dsl.module

val userModule = module {

    single<UserRepository> {
        UserRepositoryImpl(
            get(),
            get()
        )
    }


}