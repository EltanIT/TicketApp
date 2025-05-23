package com.example.ticketsapp.presentation.Settings

import android.util.Log
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
import com.example.core.domain.usecase.LogOutUseCase
import com.example.core.domain.usecase.UpdateTicketStatusUseCase
import com.example.ticketsapp.presentation.utils.Role
import com.example.ticketsapp.presentation.utils.TicketData
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.cancel
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class SettingsViewModel(
    private val logOutUseCase: LogOutUseCase,
    private val getMyUserUseCase: GetMyUserUseCase,
    private val getAllTicketsUseCase: GetAllTicketsUseCase,
    private val getAllExecutors: GetAllExecutorsUseCase,
    private val getAllAuthorsUseCase: GetAllAuthorsUseCase,
    private val getAllDepartmentsUseCase: GetAllDepartmentsUseCase,
    private val getAllProblemTypesUseCase: GetAllProblemTypesUseCase,

    private val getUserByIdUseCase: GetUserByIdUseCase,

    private val assignExecutorToTicketUseCase: AssignExecutorToTicketUseCase,
    private val getExecutorByUserIdUseCase: GetExecutorByUserIdUseCase,
    private val updateTicketStatusUseCase: UpdateTicketStatusUseCase
): ViewModel() {

    private val _state = mutableStateOf(SettingsState())
    val state: State<SettingsState> = _state


    private var myTickets: MutableList<TicketData> = mutableListOf()
    private var tackedTickets: List<TicketData> = emptyList()
    private var completedTickets: List<TicketData> = emptyList()

    init {
        viewModelScope.launch(Dispatchers.IO) {
            launch {
                try {
                    val user = getMyUserUseCase()
                    val executor = getExecutorByUserIdUseCase(user.id)
                    withContext(Dispatchers.Main){
                        _state.value = state.value.copy(
                            executor = executor
                        )
                    }
                }catch (_: Exception){}
            }
            getProfile()
        }
    }

    private var ticketInfoJob: Job? = null

    fun onEvent(event: SettingsEvent){
        when(event){
            SettingsEvent.LogOut -> {
                viewModelScope.launch {
                    try{
                        logOutUseCase()
                        _state.value = state.value.copy(
                            logOutIsComplete = true
                        )
                    }catch (e: Exception){
                        _state.value = state.value.copy(
                            logOutDialogIsOpen = false,
                            exception = e.message.toString()
                        )
                    }
                }
            }
            SettingsEvent.OpenCompletedRequests -> {
                ticketInfoJob?.cancel("")
                _state.value = state.value.copy(
                    tickets = completedTickets,
                    selectedTicket = null,
                    ticketsIsOpen = !state.value.ticketsIsOpen
                )
            }
            SettingsEvent.OpenMyRequests -> {
                ticketInfoJob?.cancel("")
                _state.value = state.value.copy(
                    tickets = myTickets,
                    selectedTicket = null,
                    ticketsIsOpen = !state.value.ticketsIsOpen
                )
            }
            SettingsEvent.OpenStatistic -> {
                _state.value = state.value.copy(
                    statisticIsOpen = !state.value.statisticIsOpen
                )
            }
            SettingsEvent.OpenTackedRequests -> {
                ticketInfoJob?.cancel("")
                _state.value = state.value.copy(
                    tickets = tackedTickets,
                    selectedTicket = null,
                    ticketsIsOpen = !state.value.ticketsIsOpen
                )
            }

            SettingsEvent.OpenLogOutDialog -> {
                _state.value = state.value.copy(
                    logOutDialogIsOpen = !state.value.logOutDialogIsOpen
                )
            }

            is SettingsEvent.SelectTicket -> {

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

            is SettingsEvent.UpdateTicketStatus -> {
                viewModelScope.launch(Dispatchers.IO) {
                    try {
                        val ticket = state.value.selectedTicket
                        if (event.status == 3){
                            assignExecutorToTicketUseCase(
                                ticket?.ticket?.id?:0,
                                state.value.executor?.id?:0
                            )
                        }
                        updateTicketStatusUseCase(ticket?.ticket?.id?:0, event.status)

                        ticket.let {
                            it?.ticket?.status = event.status
                        }

                        _state.value = state.value.copy(
                            selectedTicket = null
                        )

                        getProfile()
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




    private suspend fun getProfile() {
        try {
            val user = getMyUserUseCase()
            withContext(Dispatchers.Main){
                _state.value = state.value.copy(
                    user = user
                )
            }
            viewModelScope.launch(Dispatchers.IO) {
                try{
                    when(
                        Role.entries.toTypedArray().find { role ->
                            role.value == user.role
                        }
                    ){
                        Role.ADMIN -> {
                            _state.value = state.value.copy(
                                actionsType = RoleActions.AdminActions.MyRequests
                            )
                            launch {
                                getTackedTickets()
                            }
                            launch {
                                getStatistic()
                            }
                            getCompletedTickets()
                        }
                        Role.TECH_SPECIALIST -> {
                            _state.value = state.value.copy(
                                actionsType = RoleActions.TechSpecActions.MyRequests
                            )
                            launch {
                                getTackedTickets()
                            }
                            getCompletedTickets()
                        }
                        Role.EMPLOYEE -> {
                            _state.value = state.value.copy(
                                actionsType = RoleActions.EmployeeActions.MyRequests
                            )
                            getMyTickets()
                        }

                        null -> {
                            throw Exception("role is null")
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

        }catch (e: Exception){
            withContext(Dispatchers.Main){
                _state.value = state.value.copy(
                    exception = e.message.toString()
                )
            }
        }
    }

    private suspend fun getMyTickets(){
        try {
            val tickets = getAllTicketsUseCase()
            val author = getAllAuthorsUseCase().find {
                it.userId == state.value.user?.id
            }
            myTickets = tickets.filter { ticket ->
                ticket.author == author?.id
            }.map {
                TicketData(
                    ticket = it,
                    author = state.value.user
                )
            }.toMutableList()

            myTickets.sortBy {
                it.ticket?.status == 2
            }
            myTickets.reverse()

        }catch (e: Exception){
            withContext(Dispatchers.Main){
                _state.value = state.value.copy(
                    exception = e.message.toString()
                )
            }
        }
    }
    private suspend fun getTackedTickets(){
        try {
            val tickets = getAllTicketsUseCase()
            val executor = getAllExecutors().find {
                it.userId == state.value.user?.id
            }?:return

            tackedTickets = tickets.filter { ticket ->
                ticket.executor == executor.id && ticket.executor!=null
            }.map {
                TicketData(
                    ticket = it
                )
            }

            val authors = getAllAuthorsUseCase()
            val ticketsList = tackedTickets.toMutableList()
            viewModelScope.launch(Dispatchers.IO) {
                for (index in tickets.indices){
                    launch {
                        try {
                            val author = getUserByIdUseCase(
                                authors.find {
                                    it.id == ticketsList[index].ticket?.author
                                }?.userId ?: 0
                            )
                            ticketsList[index] = ticketsList[index].copy(
                                author = author
                            )
                            tackedTickets = ticketsList
                        }catch (_: Exception){}
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
    private suspend fun getCompletedTickets(){
        try {
            val tickets = getAllTicketsUseCase()
            val executor = getAllExecutors().find {
                it.userId == state.value.user?.id
            } ?: return

            completedTickets = tickets.filter { ticket ->
                ticket.executor == executor.id
                        && ticket.status == 1
            }.map {
                TicketData(
                    ticket = it,
                    author = state.value.user
                )
            }
            withContext(Dispatchers.Main){
                _state.value = state.value.copy(
                    countCompletedTickets = completedTickets.size
                )
            }

        }catch (e: Exception){
            withContext(Dispatchers.Main){
                _state.value = state.value.copy(
                    exception = e.message.toString()
                )
            }
        }
    }

    private suspend fun getStatistic(){
        try {
            val executors = getAllExecutors()
            val tickets = getAllTicketsUseCase()

            var statistic = state.value.statistic

            val job = viewModelScope.launch(Dispatchers.IO) {
                statistic = executors.map {
                    StatisticData(
                        executor = getUserByIdUseCase(it.userId),
                        tickets = tickets.filter { ticket ->
                            ticket.executor == it.id
                        }
                    )
                }
            }
            job.join()
            _state.value = state.value.copy(
                statistic = statistic.sortedBy { statisticData ->
                    statisticData.tickets.size
                }.reversed()
            )

            Log.i("statistic", state.value.statistic.toString())

//            val statistic = state.value.statistic.toMutableList()
//            val job = viewModelScope.launch(Dispatchers.IO) {
//                for (index in executors.indices){
//                    val user = getUserByIdUseCase(executors[index].userId)
//                    statistic[index] = statistic[index].copy(
//                        tickets
//                    )
//
//                    for (ticket in tickets){
//                        if (ticket.executor == executors[index].id){
//                            statistic[index] = statistic[index].copy(
//                                count = statistic[index].count+1
//                            )
//                        }
//                    }
//                }
//            }
//            job.join()
//            _state.value = state.value.copy(
//                statistic = statistic.sortedBy { statisticData ->
//                    statisticData.count
//                }.reversed()
//            )


        }catch (e: Exception){
            _state.value = state.value.copy(
                exception = e.message.toString()
            )
        }
    }


}