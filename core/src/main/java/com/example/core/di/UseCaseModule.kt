package com.example.core.di

import com.example.core.domain.usecase.AssignExecutorToTicketUseCase
import com.example.core.domain.usecase.CheckEmailValid
import com.example.core.domain.usecase.CreateTicketUseCase
import com.example.core.domain.usecase.GetAllAuthorsUseCase
import com.example.core.domain.usecase.GetAllDepartmentsUseCase
import com.example.core.domain.usecase.GetAllExecutorsUseCase
import com.example.core.domain.usecase.GetAllProblemTypesUseCase
import com.example.core.domain.usecase.GetAllTicketsUseCase
import com.example.core.domain.usecase.GetExecutorByUserIdUseCase
import com.example.core.domain.usecase.GetMyUserUseCase
import com.example.core.domain.usecase.GetRoleUseCase
import com.example.core.domain.usecase.GetUserByIdUseCase
import com.example.core.domain.usecase.LogOutUseCase
import com.example.core.domain.usecase.ResetPasswordUseCase
import com.example.core.domain.usecase.SignInUseCase
import com.example.core.domain.usecase.UpdateTicketStatusUseCase
import org.koin.dsl.module

val useCaseModule = module {
    factory<SignInUseCase> {
        SignInUseCase(
            get()
        )
    }

    factory<ResetPasswordUseCase> {
        ResetPasswordUseCase(
            get()
        )
    }

    factory<LogOutUseCase> {
        LogOutUseCase(
            get()
        )
    }

    factory<CheckEmailValid> {
        CheckEmailValid()
    }

    factory<GetRoleUseCase> {
        GetRoleUseCase(
            get()
        )
    }

    factory<CreateTicketUseCase> {
        CreateTicketUseCase(
            get(),
            get(),
            get()
        )
    }

    factory<GetAllDepartmentsUseCase> {
        GetAllDepartmentsUseCase(
            get()
        )
    }

    factory<GetMyUserUseCase> {
        GetMyUserUseCase(
            get()
        )
    }

    factory<GetAllTicketsUseCase> {
        GetAllTicketsUseCase(
            get()
        )
    }
    factory<GetAllExecutorsUseCase> {
        GetAllExecutorsUseCase(
            get()
        )
    }

    factory<GetUserByIdUseCase> {
        GetUserByIdUseCase(
            get()
        )
    }

    factory<GetAllAuthorsUseCase> {
        GetAllAuthorsUseCase(
            get()
        )
    }

    factory<GetAllProblemTypesUseCase> {
        GetAllProblemTypesUseCase(
            get()
        )
    }

    factory<AssignExecutorToTicketUseCase> {
        AssignExecutorToTicketUseCase(
            get()
        )
    }

    factory<GetExecutorByUserIdUseCase> {
        GetExecutorByUserIdUseCase(
            get()
        )
    }

    factory<UpdateTicketStatusUseCase> {
        UpdateTicketStatusUseCase(
            get()
        )
    }
}