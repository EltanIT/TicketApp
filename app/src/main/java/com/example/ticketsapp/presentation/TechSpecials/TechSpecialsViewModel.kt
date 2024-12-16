package com.example.ticketsapp.presentation.TechSpecials

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.core.data.model.UserModelImpl
import com.example.core.domain.models.UserModel
import com.example.core.domain.usecase.GetAllExecutorsUseCase
import com.example.core.domain.usecase.GetUserByIdUseCase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class TechSpecialsViewModel(
    private val getAllExecutors: GetAllExecutorsUseCase,
    private val getUserByIdUseCase: GetUserByIdUseCase
): ViewModel() {

    private val _state = mutableStateOf(TechSpecialsState())
    val state: State<TechSpecialsState> = _state

    init {
        val users = ArrayList<UserModel>()
        viewModelScope.launch(Dispatchers.IO) {
             val job = launch {
                try {
                    val executors = getAllExecutors()
                    for (executor in executors){
                        try {
                            val user = getUserByIdUseCase(executor.userId)
                            users.add(user)
                        }catch (e: Exception){
                            withContext(Dispatchers.Main){
                                _state.value = state.value.copy(
                                    exception = e.message.toString()
                                )
                            }
                        }
                    }
                }catch (e: Exception){
                    withContext(Dispatchers.Main){
                        _state.value = state.value.copy(
                            exception = e.message.toString()
                        )
                    }
                }
            }

            job.join()
            _state.value = state.value.copy(
                users = users
            )
        }


    }

    fun onEvent(event: TechSpecialsEvent){
        when(event){
            is TechSpecialsEvent.SelectUser -> {
                _state.value = state.value.copy(
                    selectedUser = event.user
                )
            }
        }
    }

    fun defaultException() {
        _state.value = state.value.copy(
            exception = ""
        )
    }
}