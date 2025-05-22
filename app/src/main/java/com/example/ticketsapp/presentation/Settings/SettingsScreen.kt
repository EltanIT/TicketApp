package com.example.ticketsapp.presentation.Settings

import android.os.Build
import android.util.Log
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.ticketsapp.presentation.Search.SearchEvent
import com.example.ticketsapp.presentation.Settings.components.StatisticComponent
import com.example.ticketsapp.presentation.common.SecondaryButton
import com.example.ticketsapp.presentation.common.AboutTicketComponent
import com.example.ticketsapp.presentation.common.CustomDatePickerDialog
import com.example.ticketsapp.presentation.common.CustomDialog
import com.example.ticketsapp.presentation.common.PrimaryButton
import com.example.ticketsapp.presentation.common.TicketComponent
import com.example.ticketsapp.presentation.navigation.Route
import com.example.ticketsapp.presentation.utils.Role
import com.example.ticketsapp.presentation.utils.dateToDateString
import com.example.ticketsapp.presentation.utils.isSameDay
import org.koin.androidx.compose.koinViewModel
import java.util.Date

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun SettingsScreen(
    navController: NavController,
    viewModel: SettingsViewModel = koinViewModel()
) {

    val state = viewModel.state.value

    LaunchedEffect(key1 = state.logOutIsComplete) {
        if (state.logOutIsComplete){
            navController.popBackStack(Route.MainScreen.route, inclusive = false)
            navController.navigate(Route.SignIn.route)
        }
    }

    val context = LocalContext.current
    LaunchedEffect(key1 = state.exception) {
        if (state.exception.isNotEmpty()){
            Toast.makeText(context, state.exception, Toast.LENGTH_SHORT).show()
            viewModel.defaultException()
        }
    }

    Column(
        Modifier
            .fillMaxSize()
            .padding(horizontal = 24.dp, vertical = 12.dp)
    ) {
        Text(
            text = "Настройки",
            style = MaterialTheme.typography.titleMedium.copy(
                fontSize = 22.sp,
                fontWeight = FontWeight.W700,
                color = Color.Black
            ),
            modifier = Modifier.padding(start = 20.dp)
        )
        Spacer(modifier = Modifier.height(14.dp))

        when(state.actionsType){
            is RoleActions.EmployeeActions -> {
                SecondaryButton(
                    text = "Мои заявки",
                    textStyle = MaterialTheme.typography.titleSmall.copy(
                        color = Color.Black
                    ),
                    modifier = Modifier
                        .fillMaxWidth(0.75f)
                        .height(50.dp)
                ) {
                    viewModel.onEvent(SettingsEvent.OpenMyRequests)
                }
            }
            
            is RoleActions.TechSpecActions -> {
                SecondaryButton(
                    text = "Взятые тикеты",
                    textStyle = MaterialTheme.typography.titleSmall.copy(
                        color = Color.Black
                    ),
                    modifier = Modifier
                        .fillMaxWidth(0.75f)
                        .height(50.dp)
                ) {
                    viewModel.onEvent(SettingsEvent.OpenTackedRequests)
                }
                Spacer(modifier = Modifier.height(9.dp))
                SecondaryButton(
                    text = "Выполненные тикеты",
                    textStyle = MaterialTheme.typography.titleSmall.copy(
                        color = Color.Black
                    ),
                    modifier = Modifier
                        .fillMaxWidth(0.75f)
                        .height(50.dp)
                ) {
                    viewModel.onEvent(SettingsEvent.OpenCompletedRequests)
                }
                Spacer(modifier = Modifier.height(10.dp))
                Text(
                    text = "Выполнено: ${state.countCompletedTickets}",
                    style = MaterialTheme.typography.titleMedium.copy(
                        fontSize = 22.sp,
                        fontWeight = FontWeight.W700,
                        color = Color.Black
                    ),
                )
            }
            
            is RoleActions.AdminActions -> {
                SecondaryButton(
                    text = "Взятые тикеты",
                    textStyle = MaterialTheme.typography.titleSmall.copy(
                        color = Color.Black
                    ),
                    modifier = Modifier
                        .fillMaxWidth(0.75f)
                        .height(50.dp)
                ) {
                    viewModel.onEvent(SettingsEvent.OpenTackedRequests)
                }
                Spacer(modifier = Modifier.height(9.dp))
                SecondaryButton(
                    text = "Выполненные тикеты",
                    textStyle = MaterialTheme.typography.titleSmall.copy(
                        color = Color.Black
                    ),
                    modifier = Modifier
                        .fillMaxWidth(0.75f)
                        .height(50.dp)
                ) {
                    viewModel.onEvent(SettingsEvent.OpenCompletedRequests)
                }
                Spacer(modifier = Modifier.height(9.dp))
                SecondaryButton(
                    text = "Статистика",
                    textStyle = MaterialTheme.typography.titleSmall.copy(
                        color = Color.Black
                    ),
                    modifier = Modifier
                        .fillMaxWidth(0.75f)
                        .height(50.dp)
                ) {
                    viewModel.onEvent(SettingsEvent.OpenStatistic)
                }
                Spacer(modifier = Modifier.height(10.dp))
                Text(
                    text = "Выполнено: ${state.countCompletedTickets}",
                    style = MaterialTheme.typography.titleMedium.copy(
                        fontSize = 22.sp,
                        fontWeight = FontWeight.W700,
                        color = Color.Black
                    ),
                )
            }

            else -> {}
        }
        Spacer(modifier = Modifier.height(20.dp))

        Text(
            text = "ФИО: ${state.user?.fio}",
            style = MaterialTheme.typography.titleMedium.copy(
                    fontSize = 22.sp,
                fontWeight = FontWeight.W700,
                color = Color.Black
            ),
        )
        Spacer(modifier = Modifier.height(15.dp))
        Text(
            text = "Телефон: ${state.user?.phoneNumber}",
            style = MaterialTheme.typography.titleMedium.copy(
                fontSize = 22.sp,
                fontWeight = FontWeight.W700,
                color = Color.Black
            ),
        )

        Spacer(modifier = Modifier.weight(1f))

        Box(
            Modifier.fillMaxWidth(),
            contentAlignment = Alignment.CenterEnd
        ) {
            SecondaryButton(
                text = "Выйти",
                textStyle = MaterialTheme.typography.titleSmall.copy(
                    color = Color(0xffFF0303),
                ),
                modifier = Modifier
                    .height(36.dp)
            ) {
                viewModel.onEvent(SettingsEvent.OpenLogOutDialog)
            }
        }

    }

    if (state.ticketsIsOpen){

        CustomDialog(
            onClose = {
                viewModel.onEvent(SettingsEvent.OpenMyRequests)
            }
        ) {
            LazyColumn(
                modifier = Modifier
                    .background(Color(0xffF2F2F2))
                ,
                verticalArrangement = Arrangement.spacedBy(13.dp)
            ) {
                itemsIndexed(state.tickets){index, item ->
                    TicketComponent(
                        modifier = Modifier
                            .padding(start = 20.dp, end = 20.dp, bottom = 10.dp)
                            .fillMaxWidth(),
                        ticketModel = item.ticket,
                        authorModel = item.author
                    ) {
                        viewModel.onEvent(SettingsEvent.SelectTicket(index))
                    }
                }
            }

        }

        if (state.selectedTicket != null){
            CustomDialog(onClose = {
                viewModel.onEvent(SettingsEvent.SelectTicket(-1))
            }) {
                AboutTicketComponent(
                    modifier = Modifier
                        .padding(start = 28.dp, end = 19.dp, bottom = 22.dp),
                    ticketModel = state.selectedTicket.ticket,
                    authorModel = state.selectedTicket.author,
                    executorModel = state.selectedTicket.executor,
                    problemTypeModel = state.selectedTicket.problemType,
                    departmentModel = state.selectedTicket.department,
                    isSpecialist = Role.entries.find { it.value == state.user?.role }!=Role.EMPLOYEE,
                    isExecutor = state.selectedTicket.ticket?.executor == state.executor?.id,
                    updateStatus = {
                        viewModel.onEvent(SettingsEvent.UpdateTicketStatus(it))
                    }
                )
            }
        }
    }

    if (state.statisticIsOpen){

        var dateDialogIsOpen by remember {
            mutableStateOf(false)
        }

        var selectedDate by remember {
            mutableStateOf(Date())
        }

        if (dateDialogIsOpen){
            CustomDatePickerDialog(
                selectedDate = selectedDate,
                onSelectDate = {
                    selectedDate = it
                }
            ) {
                dateDialogIsOpen = !dateDialogIsOpen
            }
        }

        CustomDialog(
            onClose = {
                viewModel.onEvent(SettingsEvent.OpenStatistic)
            }
        ) {
            PrimaryButton(
                modifier = Modifier.padding(start = 20.dp),
                text = if (isSameDay(selectedDate, Date())) "Сегодня" else dateToDateString(selectedDate)
            ) {
                dateDialogIsOpen = !dateDialogIsOpen
            }
            Spacer(modifier = Modifier.height(10.dp))

            LazyColumn(
                modifier = Modifier
                    .background(Color(0xffF2F2F2))
                ,
                verticalArrangement = Arrangement.spacedBy(15.dp)
            ) {
                Log.i("ticketsAll", state.statistic.toString())

                val statistic = state.statistic.map { it.copy() }

                Log.i("ticketsInit", statistic.toString())


                statistic.forEach {
                    it.tickets = it.tickets.filter { ticket ->
                        ticket.completedAt?.let { it1 -> isSameDay(it1, selectedDate) } == true
                    }
                }

                Log.i("tickets", statistic.toString())

                items(
                    statistic.sortedBy {
                        it.tickets.size
                    }.reversed()
                ){item ->
                    StatisticComponent(
                        modifier = Modifier
                            .padding(start = 20.dp, end = 20.dp, bottom = 10.dp)
                            .fillMaxWidth(),
                        statisticData = item
                    )
                }
            }
        }
    }


    if (state.logOutDialogIsOpen){
        Log.i("settings", "logout")
        CustomDialog(onClose = {
            viewModel.onEvent(SettingsEvent.OpenLogOutDialog)
        }) {
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                modifier = Modifier
                    .padding(
                        horizontal = 10.dp,
                        vertical = 15.dp
                    )
            ) {
                Text(
                    text = "Выйти из аккаунта?",
                    style = MaterialTheme.typography.bodyLarge.copy(
                        lineHeight = 22.sp,
                        color = Color.Black,
                        fontWeight = FontWeight.W700
                    )
                )
                Spacer(modifier = Modifier.height(20.dp))

                Row(
                    Modifier.fillMaxWidth()
                ) {
                    SecondaryButton(
                        modifier = Modifier
                            .height(36.dp)
                            .weight(1f),
                        text = "Нет",
                        textStyle = MaterialTheme.typography.bodyLarge.copy(
                            color = Color.Black,
                            fontWeight = FontWeight.W700
                        )
                    ) {
                        viewModel.onEvent(SettingsEvent.OpenLogOutDialog)
                    }
                    Spacer(modifier = Modifier.width(10.dp))
                    SecondaryButton(
                        modifier = Modifier
                            .height(36.dp)
                            .weight(1f),
                        text = "Да",
                        textStyle = MaterialTheme.typography.bodyLarge.copy(
                            color = Color(0xffFF0303),
                            fontWeight = FontWeight.W700
                        )
                    ) {
                        viewModel.onEvent(SettingsEvent.LogOut)
                    }
                }
            }
        }
    }

}