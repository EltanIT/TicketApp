package com.example.core.data.model

import com.example.core.domain.models.RequestTypeModel
import com.google.gson.annotations.SerializedName
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
class RequestTypeModelImpl(
    @SerializedName("iD_RequestType") override val id: Int,
    @SerializedName("requestTypeName") override val name: String
) : RequestTypeModel {

    companion object{
        fun RequestTypeModel.toBody() = RequestTypeModelImpl(
            id, name
        )
    }
}