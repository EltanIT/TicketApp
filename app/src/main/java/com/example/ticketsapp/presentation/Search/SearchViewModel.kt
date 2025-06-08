package com.example.ticketsapp.presentation.Search

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
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
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.cancel
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.util.Date

class SearchViewModel(
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

    private val _state = mutableStateOf(SearchState())
    val state: State<SearchState> = _state

    private var allTickets = ArrayList<TicketData>();

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
                val departments = getAllDepartmentsUseCase()
                withContext(Dispatchers.Main){
                    _state.value = state.value.copy(
                        departments = departments,
                        selectedDepartment = departments.last().id
                    )
                }
                allTickets = tickets.map {
                    TicketData(
                        ticket = it
                    )
                } as ArrayList<TicketData>

                val authors = getAllAuthorsUseCase()

                val ticketsList = allTickets.toMutableList()
                for (index in tickets.indices){
                    launch {
                        try {
                            val author = getUserByIdUseCase(
                                authors.find {
                                    it.id == tickets[index].author
                                }?.userId ?: 0
                            )
                            ticketsList[index] = ticketsList[index].copy(
                                author = author
                            )
                            allTickets = ticketsList as ArrayList<TicketData>
                        }catch (e: Exception){
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
    }


    private var searchJob: Job? = null

    private var ticketInfoJob: Job? = null

    fun onEvent(event: SearchEvent){
        when(event){
            is SearchEvent.EnteredSearch -> {
                _state.value = state.value.copy(
                    searchValue = event.value
                )

                searchJob?.cancel("")
                searchJob = viewModelScope.launch(Dispatchers.IO) {
                    try {
                        val list = allTickets.filter { ticketModel ->
                            ticketModel.ticket?.id.toString().contains(event.value)
                                    && ticketModel.ticket?.departmentId == state.value.selectedDepartment
                        }

                        _state.value = state.value.copy(
                            tickets = list
                        )
                    }catch (e: Exception){
                        _state.value = state.value.copy(
                            exception = e.message.toString()
                        )
                    }
                }
            }
            is SearchEvent.SelectTicket -> {

                ticketInfoJob?.cancel("")
                if (event.index < 0){
                    _state.value = state.value.copy(
                        selectedTicket = null
                    )
                    return
                }

                _state.value = state.value.copy(
                    selectedTicket = state.value.tickets[event.index]
                )

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
                            val executors = getAllExecutors()
                            executor = getUserByIdUseCase(executors.find { executor ->
                                executor.id == state.value.selectedTicket?.ticket?.executor
                            }?.userId?:0)
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

            is SearchEvent.UpdateTicketStatus -> {
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
                                it?.ticket?.completedAt = Date()
                            }
                        }
                        updateTicketStatusUseCase(ticket?.ticket?.id?:0, event.status)
                        if (event.status == 1){
                            updateTicketCompletedDateUseCase(ticket?.ticket?.id?:0)
                        }

                        val index = allTickets.indexOfFirst{
                            it.ticket?.id == ticket?.ticket?.id
                        }
                        ticket.let {
                            it?.ticket?.status = event.status
                        }
                        allTickets[index] = allTickets[index].copy(
                            ticket = ticket?.ticket
                        )

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

            is SearchEvent.SelectDepartment -> {
                _state.value = _state.value.copy(
                    selectedDepartment = state.value.departments[event.index].id
                )

                searchJob?.cancel("")
                searchJob = viewModelScope.launch(Dispatchers.IO) {
                    try {
                        val list = allTickets.filter { ticketModel ->
                            ticketModel.ticket?.id.toString().contains(state.value.searchValue)
                                    && ticketModel.ticket?.departmentId == state.value.selectedDepartment
                        }

                        _state.value = state.value.copy(
                            tickets = list
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