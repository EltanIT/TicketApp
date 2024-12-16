package com.example.core.data.repository

import com.example.core.data.data_source.network.DepartmentApi
import com.example.core.data.model.DepartmentModelImpl
import com.example.core.data.utils.retrofitErrorHandler
import com.example.core.domain.models.DepartmentModel
import com.example.core.domain.models.DepartmentTypeModel
import com.example.core.domain.repository.DepartmentRepository

class DepartmentRepositoryImpl(
    private val api: DepartmentApi
): DepartmentRepository {
    override suspend fun getAllDepartments(): List<DepartmentModel> {
        val response = api.getAllDepartment()
        return retrofitErrorHandler(response)

//        return listOf(
//            DepartmentModelImpl(
//                1,
//                "jfdsklf",
//                2
//            ),
//            DepartmentModelImpl(
//                2,
//                "уроки",
//                2
//            ),
//            DepartmentModelImpl(
//                3,
//                "дом",
//                2
//            ),
//        )
    }

    override suspend fun getAllDepartmentTypes(): List<DepartmentTypeModel> {
        val response = api.getAllDepartmentType()
        return retrofitErrorHandler(response)
    }
}