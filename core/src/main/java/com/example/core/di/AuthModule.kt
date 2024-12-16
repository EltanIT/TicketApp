package com.example.core.di

import com.example.core.data.data_source.manager.UserMangerImpl
import com.example.core.data.repository.AuthRepositoryImpl
import com.example.core.domain.manager.UserManager
import com.example.core.domain.repository.AuthRepository
import com.example.core.domain.usecase.LogOutUseCase
import com.example.core.domain.usecase.SignInUseCase
import org.koin.dsl.module

val authModule = module {

    single<UserManager> {
        UserMangerImpl(
            get()
        )
    }

    single<AuthRepository> {
        AuthRepositoryImpl(
            get(),
            get()
        )
    }

}