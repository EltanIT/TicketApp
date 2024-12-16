package com.example.core.domain.usecase

import android.util.Patterns

class CheckEmailValid {

    operator fun invoke(email: String): Boolean{
        return Patterns.EMAIL_ADDRESS.matcher(email).matches()
    }
}