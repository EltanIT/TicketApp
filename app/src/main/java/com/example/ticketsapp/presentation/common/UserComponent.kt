package com.example.ticketsapp.presentation.common

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.core.domain.models.UserModel

@Composable
fun UserComponent(
    modifier: Modifier = Modifier,
    userModel: UserModel,
    onClickAbout: () -> Unit
) {
    Row(
        modifier = modifier
            .background(
                Color(0xffF5F9FE),
                RoundedCornerShape(15.dp)
            )
            .clip(RoundedCornerShape(15.dp))
            .clickable {
                       onClickAbout()
            },
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Text(
            text = "ФИО:\n${userModel.fio}",
            style = MaterialTheme.typography.bodyLarge.copy(
                lineHeight = 22.sp,
                color = Color.Black
            ),
            modifier = Modifier.padding(start = 10.dp, top = 20.dp, bottom = 20.dp)
        )

        Text(
            text = "Телефон:\n${userModel.phoneNumber}",
            style = MaterialTheme.typography.bodyLarge.copy(
                lineHeight = 22.sp,
                color = Color.Black
            ),
            modifier = Modifier.padding(end = 10.dp)
        )

    }
}