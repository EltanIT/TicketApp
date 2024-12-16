package com.example.core.data.model

import com.example.core.domain.models.DepartmentModel
import com.google.gson.annotations.SerializedName
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable


@Serializable
class DepartmentModelImpl(
    @SerializedName("iD_Department") override val id: Int,
    @SerializedName("departmentName") override val name: String,
    @SerializedName("iD_DepartmentType") override val type: Int
) : DepartmentModel {

    companion object{
        fun DepartmentModel.toBody() = DepartmentModelImpl(
            id, name, type
        )
    }
}