package com.example.ticketsapp.presentation.common

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.example.ticketsapp.R

@Composable
fun CustomDialog(
    modifier: Modifier = Modifier,
    onClose: () -> Unit,
    content: @Composable() () -> Unit
) {
    Box(
        modifier = modifier
            .fillMaxSize()
            .clickable { onClose() },
        contentAlignment = Alignment.Center
    ) {
        Column(
            modifier = Modifier
                .padding(horizontal = 22.dp)
                .background(Color(0xffF2F2F2), RoundedCornerShape(15.dp))
                .clip(RoundedCornerShape(15.dp))
                .border(1.dp, Color.Black, RoundedCornerShape(15.dp))
                .clickable(false) {},
        ) {
            Box(
                Modifier
                    .padding(top = 9.dp, end = 9.dp)
                    .fillMaxWidth(),
                contentAlignment = Alignment.TopEnd
            ) {
                IconButton(
                    onClick = onClose
                ) {
                    Icon(
                        painter = painterResource(id = R.drawable.close),
                        contentDescription = "close dialog",
                        tint = Color.Black,
                        modifier = Modifier
                            .offset(x = 11.dp)
                    )
                }
            }
            content()
        }

    }
}