package com.example.core.data.model

import com.example.core.domain.models.AuthorModel
import com.google.gson.annotations.SerializedName
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
class AuthorModelImpl(
    @SerializedName("iD_Author") override val id: Int,
    @SerializedName("iD_User") override val userId: Int
) : AuthorModel {

    companion object{
        fun AuthorModel.toBody() = AuthorModelImpl(
            id, userId
        )
    }
}