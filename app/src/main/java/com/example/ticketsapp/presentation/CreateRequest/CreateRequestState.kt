package com.example.ticketsapp.presentation.CreateRequest

import com.example.core.data.model.DepartmentModelImpl
import com.example.core.data.model.RequestTypeModelImpl
import com.example.core.domain.models.DepartmentModel
import com.example.core.domain.models.RequestTypeModel

data class CreateRequestState(
    val departments: List<DepartmentModel> = listOf(
        DepartmentModelImpl(
            1,
            "Круть",
            1
        ),
        DepartmentModelImpl(
            2,
            "департмент",
            2
        )
    ),
    val problemTypes: List<RequestTypeModel> = listOf(
        RequestTypeModelImpl(
            1,
            "Круть"
        ),
        RequestTypeModelImpl(
            2,
            "мегааа"
        )
    ),
    val dialogIsOpen: Boolean = false,

    val selectedDepartmentIndex: Int = 0,
    val selectedProblemTypeIndex: Int = 0,

    val description: String = "",

    val isLoading: Boolean = false,
    val exception: String = "",
    val isComplete: Boolean = false
)


