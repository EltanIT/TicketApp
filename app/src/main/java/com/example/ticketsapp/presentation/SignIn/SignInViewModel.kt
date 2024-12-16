package com.example.ticketsapp.presentation.SignIn

import android.util.Log
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.core.domain.usecase.CheckEmailValid
import com.example.core.domain.usecase.SignInUseCase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class SignInViewModel(
    private val signInUseCase: SignInUseCase,
    private val checkEmailValid: CheckEmailValid
): ViewModel() {

    private val _state = mutableStateOf(SignInState())
    val state: State<SignInState> = _state


    fun onEvent(event: SignInEvent){
        when(event){
            SignInEvent.ChangePasswordVisible -> {
                _state.value = state.value.copy(
                    passIsVisible = !state.value.passIsVisible
                )
            }
            is SignInEvent.EnteredEmail -> {
                _state.value = state.value.copy(
                    email = event.value,
                    emailValid = true
                )
            }
            is SignInEvent.EnteredPassword -> {
                _state.value = state.value.copy(
                    password = event.value
                )
            }
            SignInEvent.Login -> {
                viewModelScope.launch(Dispatchers.IO) {
                    try {
                        if (!checkValid()){
                            return@launch
                        }
                        _state.value = state.value.copy(
                            isLoading = true
                        )
                        signInUseCase(state.value.email, state.value.password)

                        _state.value = state.value.copy(
                            isComplete = true,
                            exception = ""
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
//        if(!checkEmailValid(state.value.email)){
//            _state.value = state.value.copy(
//                emailValid = false
//            )
//            return false
//        }
        if(state.value.email.isEmpty()){
            _state.value = state.value.copy(
                exception = "Логин не может быть пустым"
            )
            return false
        }

        if(state.value.password.isEmpty()){
            _state.value = state.value.copy(
                exception = "Пароль не может быть пустым"
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