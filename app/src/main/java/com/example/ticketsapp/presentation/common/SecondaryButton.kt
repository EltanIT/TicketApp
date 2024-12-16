package com.example.ticketsapp.presentation.common

import androidx.annotation.DrawableRes
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp

@Composable
fun SecondaryButton(
    modifier: Modifier = Modifier,
    text: String,
    textStyle: TextStyle,
    @DrawableRes icon: Int? = null,
    onClick: () -> Unit
) {

    Row(
        modifier = modifier
            .background(Color(0xffBDC0CB), RoundedCornerShape(15.dp))
            .clip(RoundedCornerShape(15.dp))
            .clickable { onClick() },
        horizontalArrangement = if (icon!=null) Arrangement.SpaceAround else Arrangement.Center,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(
            text = text,
            style = textStyle,
            modifier = Modifier.padding(horizontal = 30.dp)
        )
        if (icon!=null){
            Icon(
                painter = painterResource(id = icon),
                contentDescription = "Icon"
            )
        }
    }

}