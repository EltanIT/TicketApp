package com.example.core.data.model

import com.example.core.domain.models.PasswordResetRequestModel
import kotlinx.serialization.Serializable

@Serializable
data class PasswordResetRequestModelImpl(
    override val email: String
): PasswordResetRequestModel{
    companion object{
        fun PasswordResetRequestModel.toBody() = PasswordResetRequestModelImpl(
            email
        )
    }
}