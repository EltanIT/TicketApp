package com.example.ticketsapp.presentation.common

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.ticketsapp.presentation.ui.theme.ErrorColor

@Composable
fun PrimaryTextField(
    modifier: Modifier,
    value: String,
    hintText: String,
    enabled: Boolean = true,
    isError: Boolean = false,
    onValueChange: (String) -> Unit
) {

    BasicTextField(
        modifier = modifier
            .height(43.dp)
            .background(
                MaterialTheme.colorScheme.primary,
                RoundedCornerShape(13.dp)
            )
            .border(
                1.dp, if (isError) ErrorColor else Color.Transparent, RoundedCornerShape(13.dp)
            ),
        value = value,
        enabled = enabled,
        onValueChange = onValueChange,
        singleLine = true,
        textStyle = MaterialTheme.typography.bodyLarge.copy(
            color = MaterialTheme.colorScheme.onPrimary,
            textAlign = TextAlign.Start
        ),
        decorationBox = { textField ->
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(horizontal = 19.dp),
                contentAlignment = Alignment.CenterStart
            ) {
                textField()
                if (value.isEmpty()){
                    Text(
                        text = hintText,
                        style = TextStyle(
                            fontSize = 16.sp,
                            fontWeight = FontWeight.W400,
                            textAlign = TextAlign.Start,
                            color = Color(0x99CCD8B7)
                        )
                    )
                }
            }
        }
    )
}