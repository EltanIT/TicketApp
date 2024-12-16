package com.example.core.domain.repository

import com.example.core.domain.models.DepartmentModel
import com.example.core.domain.models.DepartmentTypeModel

interface DepartmentRepository {

    suspend fun getAllDepartments(): List<DepartmentModel>

    suspend fun getAllDepartmentTypes(): List<DepartmentTypeModel>
}