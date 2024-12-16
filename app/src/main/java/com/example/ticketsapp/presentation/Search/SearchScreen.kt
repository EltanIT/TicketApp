package com.example.ticketsapp.presentation.Search

import android.widget.Toast
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import com.example.ticketsapp.presentation.common.AboutTicketComponent
import com.example.ticketsapp.presentation.common.CustomDialog
import com.example.ticketsapp.presentation.common.SearchTextField
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


    Column(
        Modifier
            .padding(horizontal = 20.dp)
            .fillMaxSize()
    ) {
        Spacer(modifier = Modifier.height(13.dp))
        SearchTextField(
            value = state.searchValue,
            hint = "Введите номер заявки"
        ) {
            viewModel.onEvent(SearchEvent.EnteredSearch(it))
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