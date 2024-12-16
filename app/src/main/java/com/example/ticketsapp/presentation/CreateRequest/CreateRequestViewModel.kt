package com.example.ticketsapp.presentation.CreateRequest

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.core.domain.models.DepartmentModel
import com.example.core.domain.models.RequestTypeModel
import com.example.core.domain.usecase.CreateTicketUseCase
import com.example.core.domain.usecase.GetAllDepartmentsUseCase
import com.example.core.domain.usecase.GetAllProblemTypesUseCase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class CreateRequestViewModel(
    private val createTicketUseCase: CreateTicketUseCase,
    private val getAllDepartmentsUseCase: GetAllDepartmentsUseCase,
    private val getAllProblemTypesUseCase: GetAllProblemTypesUseCase
): ViewModel() {

    private val _state = mutableStateOf(CreateRequestState())
    val state: State<CreateRequestState> = _state

    init {
        viewModelScope.launch(Dispatchers.IO) {
            var departments = emptyList<DepartmentModel>()
            var problemTypes = emptyList<RequestTypeModel>()

            val job1 = launch {
                try {
                    problemTypes = getAllProblemTypesUseCase()
                }catch (e: Exception){
                    _state.value = state.value.copy(
                        exception = e.message.toString()
                    )
                }
            }
            val job2 = launch {
                try {
                    departments = getAllDepartmentsUseCase()
                }catch (e: Exception){
                    withContext(Dispatchers.Main){
                        _state.value = state.value.copy(
                            exception = e.message.toString()
                        )
                    }
                }
            }

            job1.join()
            job2.join()
            withContext(Dispatchers.Main){
                _state.value = state.value.copy(
                    departments = departments,
                    problemTypes = problemTypes
                )
            }

        }
    }


    fun onEvent(event: CreateRequestEvent){
        when(event){

            is CreateRequestEvent.SelectDepartmentIndex -> {
                _state.value = state.value.copy(
                    selectedDepartmentIndex = event.value
                )
            }
            is CreateRequestEvent.SelectProblemTypeIndex -> {
                _state.value = state.value.copy(
                    selectedProblemTypeIndex = event.value
                )
            }

            is CreateRequestEvent.EnteredDescription -> {
                _state.value = state.value.copy(
                    description = event.value
                )
            }


            CreateRequestEvent.OpenRequestDialog -> {
                _state.value = state.value.copy(
                    dialogIsOpen = !state.value.dialogIsOpen
                )
            }
            CreateRequestEvent.SendRequest -> {
                viewModelScope.launch(Dispatchers.IO) {
                    try {
                        _state.value = state.value.copy(
                            isLoading = true,
                            isComplete = false,
                        )
                        if(!checkRequestEmpty()){
                            return@launch
                        }

                        createTicketUseCase(
                            state.value.departments[state.value.selectedDepartmentIndex].id,
                            state.value.problemTypes[state.value.selectedProblemTypeIndex].id,
                            state.value.description
                        )

                        _state.value = state.value.copy(
                            isLoading = false,
                            isComplete = true,
                            dialogIsOpen = false,
                            description = ""
                        )
                    }catch (e: Exception){
                        _state.value = state.value.copy(
                            isLoading = false,
                            isComplete = false,
                            exception = e.message.toString()
                        )
                    }
                }
            }
        }
    }


    private fun checkRequestEmpty(): Boolean{

        if (state.value.selectedDepartmentIndex < 0){
            _state.value = state.value.copy(
                isLoading = false,
                exception = "Выберите отдел"
            )
            return false
        }
        if (state.value.selectedProblemTypeIndex < 0){
            _state.value = state.value.copy(
                isLoading = false,
                exception = "Выберите тип проблемы"
            )
            return false
        }
        if (state.value.description.isEmpty()){
            _state.value = state.value.copy(
                isLoading = false,
                exception = "Описание не может быть пустым"
            )
            return false
        }
        return true
    }

    fun defaultException() {
        _state.value = state.value.copy(
            exception = ""
        )
    }
}