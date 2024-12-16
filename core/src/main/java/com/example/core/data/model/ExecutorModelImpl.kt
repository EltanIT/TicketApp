package com.example.core.data.model

import com.example.core.domain.models.ExecutorModel
import com.google.gson.annotations.SerializedName
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
class ExecutorModelImpl(
    @SerializedName("iD_Executor") override val id: Int,
    @SerializedName("iD_User") override val userId: Int
) : ExecutorModel {

    companion object{
        fun ExecutorModel.toBody() = ExecutorModelImpl(
            id, userId
        )
    }
}