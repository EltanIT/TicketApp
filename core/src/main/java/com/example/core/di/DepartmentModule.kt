package com.example.core.di

import com.example.core.data.repository.DepartmentRepositoryImpl
import com.example.core.domain.repository.DepartmentRepository
import org.koin.dsl.module

val departmentModule = module {

    single<DepartmentRepository> {
        DepartmentRepositoryImpl(
            get()
        )
    }

}