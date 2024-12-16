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
fun SearchTextField(
    modifier: Modifier = Modifier,
    value: String,
    hint: String,
    onValueChange: (String) -> Unit
) {

    BasicTextField(
        modifier = modifier
            .height(38.dp)
            .background(
                Color(0xffD9D9D9),
                RoundedCornerShape(10.dp)
            ),
        value = value,
        onValueChange = onValueChange,
        singleLine = true,
        textStyle = MaterialTheme.typography.bodyMedium.copy(
            color = Color.Black,
            textAlign = TextAlign.Start
        ),
        decorationBox = { textField ->
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(horizontal = 8.dp),
                contentAlignment = Alignment.CenterStart
            ) {
                textField()
                if (value.isEmpty()){
                    Text(
                        text = hint,
                        style = MaterialTheme.typography.bodyMedium.copy(
                            color = Color.Black.copy(0.25f)
                        )
                    )
                }
            }
        }
    )

}