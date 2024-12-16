package com.example.ticketsapp.presentation.NavigationScreen

import android.util.Log
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.snapshots.SnapshotStateList
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.core.domain.usecase.GetRoleUseCase
import com.example.ticketsapp.presentation.navigation.BottomNavItem
import com.example.ticketsapp.presentation.navigation.adminNavList
import com.example.ticketsapp.presentation.navigation.employeeNavList
import com.example.ticketsapp.presentation.navigation.techSpecNavList
import com.example.ticketsapp.presentation.utils.Role
import kotlinx.coroutines.launch

class NavigationScreenViewModel(
    private val getRoleUseCase: GetRoleUseCase
): ViewModel() {

    private val _state = mutableStateOf(NavigationScreenState())
    val state: State<NavigationScreenState> = _state


    init {
        viewModelScope.launch {
            try {
                val roleValue = getRoleUseCase.invoke()
                roleValue?.let {
                    val role = Role.entries.toTypedArray().find { role ->
                        role.value == roleValue
                    }
                    when(role){
                        Role.ADMIN -> _state.value = state.value.copy(
                            navList = adminNavList
                        )
                        Role.TECH_SPECIALIST -> _state.value = state.value.copy(
                            navList = techSpecNavList
                        )
                        Role.EMPLOYEE -> _state.value = state.value.copy(
                            navList = employeeNavList
                        )

                        null -> {
                            throw Exception("role is null")
                        }
                    }
                    return@launch
                }
                _state.value = state.value.copy(
                    isNotAuth = true
                )
            }catch (e: Exception){
                Log.i("role", e.message.toString())
                _state.value = state.value.copy(
                    isNotAuth = true
                )
            }

        }


    }

    fun onEvent(event: NavigationScreenEvent){
        when(event){
            is NavigationScreenEvent.SelectItem -> {
                _state.value = state.value.copy(
                    selectedIndex = event.index
                )
            }
        }
    }
}