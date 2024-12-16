package com.example.core.data.model

import com.example.core.domain.models.LoginRequestModel
import com.google.gson.annotations.SerializedName
import kotlinx.serialization.Serializable

@Serializable
data class LoginRequestModelImpl(
    override val login: String,
    override val password: String
): LoginRequestModel{
    companion object{
        fun LoginRequestModel.toBody() = LoginRequestModelImpl(
            login, password
        )
    }
}