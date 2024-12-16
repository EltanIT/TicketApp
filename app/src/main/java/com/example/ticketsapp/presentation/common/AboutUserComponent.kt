package com.example.ticketsapp.presentation.common

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.core.domain.models.TicketModel
import com.example.core.domain.models.UserModel

@Composable
fun AboutUserComponent(
    modifier: Modifier = Modifier,
    userModel: UserModel?
) {

    Column(
        modifier = modifier
    ) {
        Row {
            Text(
                text = "ФИО: ",
                style = MaterialTheme.typography.bodyMedium.copy(
                    fontSize = 20.sp,
                    fontWeight = FontWeight.W700,
                    color = MaterialTheme.colorScheme.primary
                )
            )
            Text(
                text = "${userModel?.fio}",
                style = MaterialTheme.typography.bodyMedium.copy(
                    fontSize = 20.sp,
                    fontWeight = FontWeight.W700,
                    color = Color.Black
                )
            )
        }
    }
}