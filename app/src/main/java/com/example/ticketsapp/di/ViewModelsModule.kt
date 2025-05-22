package com.example.ticketsapp.di

import com.example.ticketsapp.presentation.CreateRequest.CreateRequestViewModel
import com.example.ticketsapp.presentation.NavigationScreen.NavigationScreenViewModel
import com.example.ticketsapp.presentation.Requests.RequestsViewModel
import com.example.ticketsapp.presentation.ResetPassword.ResetPasswordViewModel
import com.example.ticketsapp.presentation.Search.SearchViewModel
import com.example.ticketsapp.presentation.Settings.SettingsViewModel
import com.example.ticketsapp.presentation.SignIn.SignInViewModel
import com.example.ticketsapp.presentation.TechSpecials.TechSpecialsViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val viewModelsModule = module {

    viewModel<SignInViewModel>{
        SignInViewModel(
            get(),
            get()
        )
    }

    viewModel<ResetPasswordViewModel>{
        ResetPasswordViewModel(
            get(),
            get()
        )
    }

    viewModel<NavigationScreenViewModel>{
        NavigationScreenViewModel(
            get()
        )
    }

    viewModel<CreateRequestViewModel>{
        CreateRequestViewModel(
            get(),
            get(),
            get()
        )
    }

    viewModel<SettingsViewModel>{
        SettingsViewModel(
            get(),
            get(),
            get(),
            get(),
            get(),
            get(),
            get(),
            get(),
            get(),
            get(),
            get(),
        )
    }

    viewModel<RequestsViewModel>{
        RequestsViewModel(
            get(),
            get(),
            get(),
            get(),
            get(),
            get(),
            get(),
            get(),
            get(),
            get(),
        )
    }

    viewModel<SearchViewModel>{
        SearchViewModel(
            get(),
            get(),
            get(),
            get(),
            get(),
            get(),
            get(),
            get(),
            get(),
            get(),
        )
    }

    viewModel<TechSpecialsViewModel>{
        TechSpecialsViewModel(
            get(),
            get()
        )
    }
}