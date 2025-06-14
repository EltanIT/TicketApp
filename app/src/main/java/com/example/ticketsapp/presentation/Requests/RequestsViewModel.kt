package com.example.ticketsapp.presentation.Requests

import android.util.Log
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.snapshots.SnapshotStateList
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.core.domain.models.DepartmentModel
import com.example.core.domain.models.RequestTypeModel
import com.example.core.domain.models.UserModel
import com.example.core.domain.usecase.AssignExecutorToTicketUseCase
import com.example.core.domain.usecase.GetAllAuthorsUseCase
import com.example.core.domain.usecase.GetAllDepartmentsUseCase
import com.example.core.domain.usecase.GetAllExecutorsUseCase
import com.example.core.domain.usecase.GetAllProblemTypesUseCase
import com.example.core.domain.usecase.GetAllTicketsUseCase
import com.example.core.domain.usecase.GetExecutorByUserIdUseCase
import com.example.core.domain.usecase.GetMyUserUseCase
import com.example.core.domain.usecase.GetUserByIdUseCase
import com.example.core.domain.usecase.UpdateTicketCompletedDateUseCase
import com.example.core.domain.usecase.UpdateTicketStatusUseCase
import com.example.ticketsapp.presentation.utils.TicketData
import com.example.ticketsapp.presentation.utils.statusOrder
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.async
import kotlinx.coroutines.awaitAll
import kotlinx.coroutines.cancel
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class RequestsViewModel(
    private val getAllTicketsUseCase: GetAllTicketsUseCase,

    private val getAllExecutors: GetAllExecutorsUseCase,
    private val getAllAuthorsUseCase: GetAllAuthorsUseCase,
    private val getAllDepartmentsUseCase: GetAllDepartmentsUseCase,
    private val getAllProblemTypesUseCase: GetAllProblemTypesUseCase,

    private val getUserByIdUseCase: GetUserByIdUseCase,
    private val getMyUserUseCase: GetMyUserUseCase,

    private val assignExecutorToTicketUseCase: AssignExecutorToTicketUseCase,
    private val getExecutorByUserIdUseCase: GetExecutorByUserIdUseCase,
    private val updateTicketStatusUseCase: UpdateTicketStatusUseCase,
    private val updateTicketCompletedDateUseCase: UpdateTicketCompletedDateUseCase,
): ViewModel() {

    private val _state = mutableStateOf(RequestsState())
    val state: State<RequestsState> = _state

    private val _tickets = mutableStateListOf<TicketData>()
    val tickets: SnapshotStateList<TicketData> = _tickets



    init {
        viewModelScope.launch(Dispatchers.IO) {
            launch {
                try {
                    val user = getMyUserUseCase()
                    withContext(Dispatchers.Main){
                        _state.value = state.value.copy(
                            role = user.role
                        )
                    }
                    val executor = getExecutorByUserIdUseCase(user.id)
                    withContext(Dispatchers.Main){
                        _state.value = state.value.copy(
                            executor = executor
                        )
                    }
                }catch (_: Exception){}
            }

            try {
                val tickets = getAllTicketsUseCase()


                _tickets.addAll(
                    tickets.map {
                        TicketData(
                            ticket = it
                        )
                    }
                )
                _tickets.sortBy {
                    statusOrder[it.ticket?.status ?: 0] ?: Int.MAX_VALUE
                }


                val authors = getAllAuthorsUseCase()

                val updatedJob = _tickets.mapIndexed { index, ticketModel ->
                    async {
                        try {
                            val author = getUserByIdUseCase(
                                authors.find {
                                    it.id == ticketModel.ticket?.author
                                }?.userId ?: 0
                            )

                            withContext(Dispatchers.Main){
                                _tickets[index] = _tickets[index].copy(
                                    author = author
                                )
                            }
                        } catch (_: Exception){}
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
    }

    private var ticketInfoJob: Job? = null

    fun onEvent(event: RequestsEvent){
        when(event){
            RequestsEvent.OpenDateChangerDialog -> {

            }
            is RequestsEvent.SelectTicket -> {
                ticketInfoJob?.cancel("")

                _state.value = state.value.copy(
                    selectedTicket = event.ticket
                )
                if (event.ticket == null){
                    return
                }

                var department: DepartmentModel? = null
                var problemType: RequestTypeModel? = null
                var executor: UserModel? = null
                ticketInfoJob = viewModelScope.launch(Dispatchers.IO) {
                    val job1 = launch {
                        try {
                            val departments = getAllDepartmentsUseCase()
                            department = departments.find { department ->
                                department.id == state.value.selectedTicket?.ticket?.departmentId
                            }
                        }catch (e: Exception){
                            _state.value = state.value.copy(
                                exception = e.message.toString()
                            )
                        }
                    }
                    val job2 = launch {
                        try {
                            val problemTypes = getAllProblemTypesUseCase()
                            problemType = problemTypes.find { type ->
                                type.id == state.value.selectedTicket?.ticket?.type
                            }


                        }catch (e: Exception){
                            _state.value = state.value.copy(
                                exception = e.message.toString()
                            )
                        }
                    }
                    val job3 = launch {
                        try {
                            val executorModel = getAllExecutors().find {
                                it.id == state.value.selectedTicket?.ticket?.executor
                            }
                            executor = getUserByIdUseCase(executorModel?.userId?:0)
                        }catch (_: Exception){}
                    }

                    try {
                        job1.join()
                        job2.join()
                        job3.join()

                        _state.value = state.value.copy(
                            selectedTicket = state.value.selectedTicket?.copy(
                                executor = executor,
                                department = department,
                                problemType = problemType
                            )
                        )
                    }catch (e: Exception){
                        _state.value = state.value.copy(
                            exception = e.message.toString()
                        )
                    }

                }
            }

            is RequestsEvent.UpdateTicketStatus -> {
                viewModelScope.launch(Dispatchers.IO) {
                    try {
                        val ticket = state.value.selectedTicket
                        if (event.status == 3){
                            assignExecutorToTicketUseCase(
                                ticket?.ticket?.id?:0,
                                state.value.executor?.id?:0
                            )
                            val executor = getMyUserUseCase()

                            ticket.let {
                                it?.ticket?.executor = state.value.executor?.id
                                it?.executor = executor
                            }
                        }
                        updateTicketStatusUseCase(ticket?.ticket?.id?:0, event.status)

                        if (event.status == 1){
                            updateTicketCompletedDateUseCase(ticket?.ticket?.id?:0)
                        }

                        val index = tickets.indexOfFirst{
                            it.ticket?.id == ticket?.ticket?.id
                        }
                        Log.i("retrofitBody", index.toString())
                        ticket.let {
                            it?.ticket?.status = event.status
                        }

                        _tickets[index] = ticket!!

                        _state.value = state.value.copy(
                            selectedTicket = null
                        )
                    }catch (e: Exception){
                        _state.value = state.value.copy(
                            exception = e.message.toString()
                        )
                    }
                }
            }
        }
    }

    fun defaultException() {
        _state.value = state.value.copy(
            exception = ""
        )
    }
}