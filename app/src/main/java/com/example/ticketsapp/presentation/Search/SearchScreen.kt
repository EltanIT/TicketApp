package com.example.ticketsapp.presentation.Search

import android.widget.Toast
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
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import com.example.ticketsapp.presentation.common.AboutTicketComponent
import com.example.ticketsapp.presentation.common.CustomDialog
import com.example.ticketsapp.presentation.common.CustomDropDownMenu
import com.example.ticketsapp.presentation.common.PrimaryButton
import com.example.ticketsapp.presentation.common.SearchTextField
import com.example.ticketsapp.presentation.common.SecondaryButton
import com.example.ticketsapp.presentation.common.TicketComponent
import com.example.ticketsapp.presentation.utils.Role
import org.koin.androidx.compose.koinViewModel

@Composable
fun SearchScreen(
    viewModel: SearchViewModel = koinViewModel()
) {

    val state = viewModel.state.value

    val context = LocalContext.current
    LaunchedEffect(key1 = state.exception) {
        if (state.exception.isNotEmpty()){
            Toast.makeText(context, state.exception, Toast.LENGTH_SHORT).show()
            viewModel.defaultException()
        }
    }

    var departmentExpanded by remember {
        mutableStateOf(false)
    }

    val configuration = LocalConfiguration.current
    val screenHeight = configuration.screenHeightDp


    Column(
        Modifier
            .padding(horizontal = 20.dp)
            .fillMaxSize()
    ) {
        Spacer(modifier = Modifier.height(13.dp))
        Box{
            Column {
                SearchTextField(
                    value = state.searchValue,
                    hint = "Введите номер заявки"
                ) {
                    viewModel.onEvent(SearchEvent.EnteredSearch(it))
                }
                Spacer(modifier = Modifier.width(5.dp))
                SecondaryButton(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(30.dp),
                    text = state.departments.find { it.id == state.selectedDepartment }?.name?:"Отделение",
                    textStyle = MaterialTheme.typography.titleSmall.copy(
                        color = Color.Black
                    )
                ) {
                    departmentExpanded = !departmentExpanded
                }
            }
            CustomDropDownMenu(
                modifier = Modifier
                    .height((screenHeight/2).dp)
                    .fillMaxWidth(),
                items = state.departments.map { it.name },
                expanded = departmentExpanded,
                onClose = {
                    departmentExpanded = !departmentExpanded
                }
            ) {
                viewModel.onEvent(SearchEvent.SelectDepartment(it))
            }
        }


        Spacer(modifier = Modifier.height(20.dp))

        LazyColumn(
            verticalArrangement = Arrangement.spacedBy(10.dp)
        ) {
            itemsIndexed(state.tickets){ index, item ->
                TicketComponent(
                    modifier = Modifier.fillMaxWidth(),
                    ticketModel = item.ticket,
                    authorModel = item.author
                ) {
                    viewModel.onEvent(SearchEvent.SelectTicket(index))
                }
            }
        }
    }

    if (state.selectedTicket!=null){
        CustomDialog(onClose = {
            viewModel.onEvent(SearchEvent.SelectTicket(-1))
        }) {
            AboutTicketComponent(
                modifier = Modifier
                    .padding(start = 28.dp, end = 19.dp, bottom = 22.dp),
                ticketModel = state.selectedTicket.ticket,
                authorModel = state.selectedTicket.author,
                executorModel = state.selectedTicket.executor,
                problemTypeModel = state.selectedTicket.problemType,
                departmentModel = state.selectedTicket.department,
                isSpecialist = Role.entries.find { it.value == state.role } != Role.EMPLOYEE,
                isExecutor = state.selectedTicket.ticket?.executor == state.executor?.id,
                updateStatus = {
                    viewModel.onEvent(SearchEvent.UpdateTicketStatus(it))
                }
            )
        }
    }
}