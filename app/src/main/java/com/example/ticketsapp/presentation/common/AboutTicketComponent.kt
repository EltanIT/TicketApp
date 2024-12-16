package com.example.ticketsapp.presentation.common

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.core.domain.models.DepartmentModel
import com.example.core.domain.models.RequestTypeModel
import com.example.core.domain.models.TicketModel
import com.example.core.domain.models.UserModel
import com.example.ticketsapp.presentation.utils.requestStatuses

@Composable
fun AboutTicketComponent(
    modifier: Modifier = Modifier,
    ticketModel: TicketModel?,
    authorModel: UserModel?,
    executorModel: UserModel?,
    departmentModel: DepartmentModel?,
    problemTypeModel: RequestTypeModel?,

    isSpecialist: Boolean = false,
    isExecutor: Boolean = false,
    updateStatus: (Int) -> Unit
) {

    Column(
        modifier = modifier
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = "Статус: ",
                style = MaterialTheme.typography.bodyMedium.copy(
                    fontSize = 20.sp,
                    fontWeight = FontWeight.W700,
                    color = MaterialTheme.colorScheme.primary
                )
            )
            Text(
                text = "${
                    requestStatuses.find {
                    it.id == ticketModel?.status
                }?.name} ",
                style = MaterialTheme.typography.bodyMedium.copy(
                    fontSize = 20.sp,
                    fontWeight = FontWeight.W700,
                    color = Color.Black
                )
            )
            Box(
                Modifier
                    .size(12.dp)
                    .background(
                        requestStatuses.find {
                            it.id == ticketModel?.status
                        }?.color ?: Color.Black, CircleShape
                    )
            )
        }
        Spacer(modifier = Modifier.padding(top = 9.dp))
        Column {
            Text(
                text = "Время: ",
                style = MaterialTheme.typography.bodyMedium.copy(
                    fontSize = 20.sp,
                    fontWeight = FontWeight.W700,
                    color = MaterialTheme.colorScheme.primary
                )
            )
            Text(
                text = ticketModel?.createdAt.toString(),
                style = MaterialTheme.typography.bodyMedium.copy(
                    fontSize = 20.sp,
                    fontWeight = FontWeight.W700,
                    color = Color.Black
                )
            )
        }
        Spacer(modifier = Modifier.padding(top = 9.dp))
        Column {
            Text(
                text = "Где: ",
                style = MaterialTheme.typography.bodyMedium.copy(
                    fontSize = 20.sp,
                    fontWeight = FontWeight.W700,
                    color = MaterialTheme.colorScheme.primary
                )
            )
            Text(
                text = departmentModel?.name?:"...",
                style = MaterialTheme.typography.bodyMedium.copy(
                    fontSize = 20.sp,
                    fontWeight = FontWeight.W700,
                    color = Color.Black
                )
            )
        }
        Spacer(modifier = Modifier.padding(top = 9.dp))
        Column {
            Text(
                text = "Тип проблемы: ",
                style = MaterialTheme.typography.bodyMedium.copy(
                    fontSize = 20.sp,
                    fontWeight = FontWeight.W700,
                    color = MaterialTheme.colorScheme.primary
                )
            )
            Text(
                text = problemTypeModel?.name?:"...",
                style = MaterialTheme.typography.bodyMedium.copy(
                    fontSize = 20.sp,
                    fontWeight = FontWeight.W700,
                    color = Color.Black
                )
            )
        }
        Spacer(modifier = Modifier.padding(top = 9.dp))
        Column {
            Text(
                text = "Описание проблемы: ",
                style = MaterialTheme.typography.bodyMedium.copy(
                    fontSize = 20.sp,
                    fontWeight = FontWeight.W700,
                    color = MaterialTheme.colorScheme.primary,
                    textAlign = TextAlign.Start
                )
            )
            Text(
                text = ticketModel?.description?:"",
                style = MaterialTheme.typography.bodyMedium.copy(
                    fontSize = 20.sp,
                    fontWeight = FontWeight.W700,
                    color = Color.Black
                )
            )
        }
        Spacer(modifier = Modifier.padding(top = 9.dp))
        Column {
            Text(
                text = "Номер телефона: ",
                style = MaterialTheme.typography.bodyMedium.copy(
                    fontSize = 20.sp,
                    fontWeight = FontWeight.W700,
                    color = MaterialTheme.colorScheme.primary
                )
            )
            Text(
                text = authorModel?.phoneNumber?:"...",
                style = MaterialTheme.typography.bodyMedium.copy(
                    fontSize = 20.sp,
                    fontWeight = FontWeight.W700,
                    color = Color.Black
                )
            )
        }
        Spacer(modifier = Modifier.padding(top = 9.dp))
        Row {
            Text(
                text = "Исполнитель: ",
                style = MaterialTheme.typography.bodyMedium.copy(
                    fontSize = 20.sp,
                    fontWeight = FontWeight.W700,
                    color = MaterialTheme.colorScheme.primary
                )
            )
            Text(
                text = executorModel?.fio?:"",
                style = MaterialTheme.typography.bodyMedium.copy(
                    fontSize = 20.sp,
                    fontWeight = FontWeight.W700,
                    color = Color.Black
                )
            )
        }
        Spacer(modifier = Modifier.padding(top = 15.dp))
        if (isSpecialist){
            if (ticketModel?.status == 3 && isExecutor){
                PrimaryButton(
                    modifier = Modifier
                        .padding(horizontal = 20.dp)
                        .height(60.dp)
                        .fillMaxWidth(),
                    text = "Отменить",
                    enabled = true,
                    onClick = {
                        updateStatus(4)
                    }
                )
                Spacer(modifier = Modifier.height(10.dp))
            }

            val text = when(ticketModel?.status){
                1 -> "Уже выполнена"
                2 -> "Взять"
                3 -> if (isExecutor) "Завершить" else "В процессе"
                4 -> "Отменена"
                else -> ""
            }
            PrimaryButton(
                modifier = Modifier
                    .padding(horizontal = 20.dp)
                    .height(60.dp)
                    .fillMaxWidth(),
                text = text,
                enabled = ticketModel?.status == 2 || (ticketModel?.status == 3 && isExecutor),
                onClick = {
                    if (ticketModel?.status == 2){
                        updateStatus(3)
                    }else{
                        updateStatus(1)
                    }

                }
            )
        }
    }
}