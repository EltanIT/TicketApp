package com.example.core.data.model

import com.example.core.domain.models.UserModel
import com.google.gson.annotations.SerializedName
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable


@Serializable
data class UserModelImpl(
    @SerializedName("iD_User") override val id: Int,
    override val login: String,
    override val email: String,
    override val role: String,
    override val fio: String,
    @SerializedName("numberPhone") override val phoneNumber: String
) : UserModel {

    companion object{
        fun UserModel.toBody() = UserModelImpl(
            id,
            login,
            email,
            role,
            fio,
            phoneNumber
        )
    }
}