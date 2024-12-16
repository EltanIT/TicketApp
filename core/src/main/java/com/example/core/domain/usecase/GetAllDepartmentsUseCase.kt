package com.example.core.domain.usecase

import com.example.core.domain.models.DepartmentModel
import com.example.core.domain.repository.DepartmentRepository

class GetAllDepartmentsUseCase(
    private val departmentRepository: DepartmentRepository
) {

    suspend operator fun invoke(): List<DepartmentModel>{
        return departmentRepository.getAllDepartments()
    }
}