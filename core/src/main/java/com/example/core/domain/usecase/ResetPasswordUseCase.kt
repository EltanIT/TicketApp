package com.example.core.domain.usecase

import com.example.core.domain.models.PasswordResetRequestModel
import com.example.core.domain.repository.AuthRepository

class ResetPasswordUseCase(
    private val repository: AuthRepository
) {

    suspend operator fun invoke(email: String): String{
        return repository.resetPasswordRequest(
            object : PasswordResetRequestModel{
                override val email: String
                    get() = email
            }
        )
    }
}