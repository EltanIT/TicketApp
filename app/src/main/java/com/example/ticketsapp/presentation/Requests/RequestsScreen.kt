package com.example.ticketsapp.presentation.Requests

import android.widget.Toast
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.ticketsapp.presentation.common.AboutTicketComponent
import com.example.ticketsapp.presentation.common.CustomDialog
import com.example.ticketsapp.presentation.common.SecondaryButton
import com.example.ticketsapp.presentation.common.TicketComponent
import com.example.ticketsapp.presentation.utils.Role
import org.koin.androidx.compose.koinViewModel

@Composable
fun RequestsScreen(
    viewModel: RequestsViewModel = koinViewModel()
) {

    val state = viewModel.state.value
    val tickets = viewModel.tickets

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
            .fillMaxSize(),
        verticalArrangement = Arrangement.spacedBy(9.dp)
    ) {
        Spacer(modifier = Modifier.height(13.dp))
        Row(
            modifier = Modifier
                .fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = "Заявки",
                style = MaterialTheme.typography.titleMedium.copy(
                    fontSize = 22.sp,
                    fontWeight = FontWeight.W700,
                    color = Color.Black
                ),
                modifier = Modifier.padding(start = 20.dp)
            )

//            SecondaryButton(
//                modifier = Modifier.height(27.dp),
//                text = "Сегодня",
//                textStyle = MaterialTheme.typography.bodyMedium.copy(
//                    fontWeight = FontWeight.W700,
//                    color = Color.Black,
//
//                )
//            ) {
//
//            }
        }

        LazyColumn(
            Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.spacedBy(10.dp)
        ) {
            itemsIndexed(tickets){ index, item ->
                TicketComponent(
                    modifier = Modifier.fillMaxWidth(),
                    ticketModel = item.ticket,
                    authorModel = item.author
                ) {
                    viewModel.onEvent(RequestsEvent.SelectTicket(item))
                }
            }
        }
    }


    if (state.selectedTicket!=null){
        CustomDialog(onClose = {
            viewModel.onEvent(RequestsEvent.SelectTicket(null))
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
                    viewModel.onEvent(RequestsEvent.UpdateTicketStatus(it))
                }
            )
        }
    }

}