package com.example.core.data.data_source.network

import com.example.core.data.model.DepartmentModelImpl
import com.example.core.data.model.DepartmentTypeModelImpl
import retrofit2.Response
import retrofit2.http.GET

interface DepartmentApi {

    @GET("/Department/allDepartment")
    suspend fun getAllDepartment(): Response<List<DepartmentModelImpl>>

    @GET("/Department/allDepartmentType")
    suspend fun getAllDepartmentType(): Response<List<DepartmentTypeModelImpl>>
}