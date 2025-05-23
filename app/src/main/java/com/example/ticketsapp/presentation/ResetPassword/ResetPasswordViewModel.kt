package com.example.ticketsapp.presentation.ResetPassword

import android.util.Log
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.core.domain.usecase.CheckEmailValid
import com.example.core.domain.usecase.ResetPasswordUseCase
import com.example.core.domain.usecase.SignInUseCase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class ResetPasswordViewModel(
    private val resetPasswordUseCase: ResetPasswordUseCase,
    private val checkEmailValid: CheckEmailValid
): ViewModel() {

    private val _state = mutableStateOf(ResetPasswordState())
    val state: State<ResetPasswordState> = _state


    fun onEvent(event: ResetPasswordEvent){
        when(event){
            is ResetPasswordEvent.EnteredEmail -> {
                _state.value = state.value.copy(
                    email = event.value,
                    emailValid = true
                )
            }
            ResetPasswordEvent.ResetPassword -> {
                viewModelScope.launch(Dispatchers.IO) {
                    try {
                        if (!checkValid()){
                            return@launch
                        }
                        _state.value = state.value.copy(
                            isLoading = true
                        )
                        val completedText = resetPasswordUseCase(state.value.email)

                        _state.value = state.value.copy(
                            isComplete = true,
                            exception = completedText
                        )
                    }catch (e: Exception){
                        Log.i("signIn", e.toString())
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


    private fun checkValid(): Boolean{
        if(!checkEmailValid(state.value.email)){
            _state.value = state.value.copy(
                emailValid = false
            )
            return false
        }
        if(state.value.email.isEmpty()){
            _state.value = state.value.copy(
                exception = "Почта не может быть пустым"
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