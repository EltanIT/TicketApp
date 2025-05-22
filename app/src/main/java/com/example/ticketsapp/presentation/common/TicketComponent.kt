package com.example.ticketsapp.presentation.common

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
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
import com.example.core.domain.models.TicketModel
import com.example.core.domain.models.UserModel
import com.example.ticketsapp.presentation.utils.requestStatuses
import java.text.SimpleDateFormat
import java.util.Locale

@Composable
fun TicketComponent(
    modifier: Modifier = Modifier,
    ticketModel: TicketModel?,
    authorModel: UserModel?,
    onClickAbout: () -> Unit,
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
            }
        ,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Column(
            Modifier
                .padding(8.dp)
                .weight(1f)
        ) {
            Row(
                modifier = Modifier
                    .padding(start = 10.dp, top = 3.dp)
                    .fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = "№ ${ticketModel?.id}",
                    style = MaterialTheme.typography.bodyLarge.copy(
                        lineHeight = 22.sp,
                        color = Color.Black
                    )
                )

                Row (
                    modifier= Modifier.weight(1f),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.Center
                ){
                    Text(
                        text = "Статус: ",
                        style = MaterialTheme.typography.bodyLarge.copy(
                            lineHeight = 22.sp,
                            color = Color.Black
                        ),
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

            }
            Row(
                modifier = Modifier
                    .padding(start = 10.dp)
                    .fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Text(
                    text = "От: ${authorModel?.fio}",
                    style = MaterialTheme.typography.bodyLarge.copy(
                        lineHeight = 22.sp,
                        color = Color.Black
                    )
                )

                val formatter = SimpleDateFormat("HH:mm, d MMMM yyyy", Locale("ru"))
                val formattedDate = ticketModel?.createdAt?.let { formatter.format(it) }

                Text(
                    text = formattedDate?:"???",
                    style = MaterialTheme.typography.bodyLarge.copy(
                        lineHeight = 22.sp,
                        color = Color.Black
                    )
                )
            }
//            Text(
//                text = "Где: ${ticketModel.departmentId}.",
//                style = MaterialTheme.typography.bodyLarge.copy(
//                    color = Color.Black,
//                    lineHeight = 22.sp
//                )
//            )
        }
//        Box(
//            Modifier
//                .height(35.dp)
//                .background(
//                    Color(0xffFFC231)
//                )
//                .clickable {
//                           onClickAbout()
//                },
//            contentAlignment = Alignment.Center
//        ) {
//            Text(
//                text = "Еще",
//                style = MaterialTheme.typography.bodySmall.copy(
//                    color = Color.Black,
//                ),
//                modifier = Modifier
//                    .padding(horizontal = 5.dp)
//            )
//        }
    }

}