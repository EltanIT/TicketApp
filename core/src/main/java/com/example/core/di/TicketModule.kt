package com.example.core.di

import com.example.core.data.repository.TicketsRepositoryImpl
import com.example.core.domain.repository.TicketsRepository
import org.koin.dsl.module

val ticketModule =  module{


    single<TicketsRepository> {
        TicketsRepositoryImpl(
            get()
        )
    }
}