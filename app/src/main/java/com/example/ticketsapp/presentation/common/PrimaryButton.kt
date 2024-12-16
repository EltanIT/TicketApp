package com.example.ticketsapp.presentation.common

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp


@Composable
fun PrimaryButton(
    modifier: Modifier,
    text: String,
    textColor: Color = MaterialTheme.colorScheme.onPrimary,
    isLoading: Boolean = false,
    enabled: Boolean = true,
    onClick: () -> Unit
) {

    Button(
        modifier = modifier
            .height(60.dp)
            .background(MaterialTheme.colorScheme.primary, RoundedCornerShape(14.dp))
            .clip(RoundedCornerShape(14.dp)),
        onClick = onClick,
        enabled = !isLoading && enabled
    ) {
        if(isLoading){
            LoadingIndicator()
        }else{
            Text(
                text = text,
                style = MaterialTheme.typography.bodyLarge.copy(
                    color = textColor
                )
            )
        }
    }

}