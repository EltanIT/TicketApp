package com.example.core.data.model

import com.example.core.domain.models.DepartmentTypeModel
import com.google.gson.annotations.SerializedName
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
class DepartmentTypeModelImpl(
    @SerializedName("iD_DepartmentType") override val id: Int,
    @SerializedName("departmentTypeName") override val name: String,
) : DepartmentTypeModel {

    companion object{
        fun DepartmentTypeModel.toBody() = DepartmentTypeModelImpl(
            id, name
        )
    }
}