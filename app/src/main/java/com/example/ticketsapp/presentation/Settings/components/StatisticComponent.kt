package com.example.ticketsapp.presentation.Settings.components

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.ticketsapp.presentation.Settings.StatisticData

@Composable
fun StatisticComponent(
    modifier: Modifier = Modifier,
    statisticData: StatisticData,
) {

    Row(
        modifier = modifier
            .background(
                Color(0xffF5F9FE),
                RoundedCornerShape(15.dp)
            )
            .clip(RoundedCornerShape(15.dp))
            .border(1.dp, Color.Black, RoundedCornerShape(15.dp)),
        verticalAlignment = Alignment.CenterVertically,
    ) {
        Text(
            text = statisticData.executor.fio,
            style = MaterialTheme.typography.bodyLarge.copy(
                lineHeight = 22.sp,
                color = Color.Black
            ),
            modifier = Modifier
                .padding(vertical = 25.dp)
                .weight(1f)
        )

        Text(
            text = "${statisticData.tickets.size} Тикет",
            style = MaterialTheme.typography.bodyLarge.copy(
                lineHeight = 22.sp,
                color = Color.Black
            ),
            modifier = Modifier
                .padding(end = 10.dp)
        )

    }

}