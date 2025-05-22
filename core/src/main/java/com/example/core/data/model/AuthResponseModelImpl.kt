package com.example.core.data.model

import com.example.core.domain.models.AuthResponseModel
import com.example.core.domain.models.LoginRequestModel
import com.google.gson.annotations.SerializedName
import kotlinx.serialization.Serializable

@Serializable
data class AuthResponseModelImpl(
    override val token: String,
    override val id_user: Int,
    override val role: String
): AuthResponseModel{
    companion object{
        fun AuthResponseModel.toBody() = AuthResponseModelImpl(
            token, id_user, role
        )
    }
}