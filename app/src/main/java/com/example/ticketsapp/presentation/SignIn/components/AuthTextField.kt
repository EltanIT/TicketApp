package com.example.ticketsapp.presentation.SignIn.components

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.example.ticketsapp.R
import com.example.ticketsapp.presentation.ui.theme.ErrorColor
import com.example.ticketsapp.presentation.ui.theme.HintColor

@Composable
fun AuthTextField(
    modifier: Modifier,
    value: String,
    hintText: String,
    enabled: Boolean = true,
    isError: Boolean = false,
    passIsVisible: Boolean? = null,
    onPassVisibleChange: () -> Unit,
    onValueChange: (String) -> Unit
) {

    BasicTextField(
        modifier = modifier
            .height(60.dp)
            .background(
                MaterialTheme.colorScheme.onBackground,
                RoundedCornerShape(14.dp)
            )
            .border(
                1.dp, if (isError) ErrorColor else Color.Transparent, RoundedCornerShape(14.dp)
            ),
        value = value,
        enabled = enabled,
        onValueChange = onValueChange,
        singleLine = true,
        textStyle = MaterialTheme.typography.bodyLarge.copy(
            color = MaterialTheme.colorScheme.secondary,
            textAlign = TextAlign.Start
        ),
        visualTransformation = if (passIsVisible == false) PasswordVisualTransformation('*')
        else VisualTransformation.None,
        decorationBox = { textField ->
            Row(
                Modifier
                    .fillMaxSize()
                    .padding(horizontal = 19.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Box(
                    modifier = Modifier.weight(1f),
                    contentAlignment = Alignment.CenterStart
                ) {
                    textField()
                    if (value.isEmpty()){
                        Text(
                            text = hintText,
                            style = MaterialTheme.typography.bodyLarge.copy(
                                color = HintColor
                            )
                        )
                    }
                }
                if (passIsVisible!=null){
                    Spacer(modifier = Modifier.width(5.dp))
                    Icon(
                        painter = painterResource(
                            id = if(passIsVisible) R.drawable.eye_open else R.drawable.eye_slash
                        ),
                        tint = Color(0xff3B4054),
                        contentDescription = "password eye state",
                        modifier = Modifier
                            .size(22.dp)
                            .clickable {
                                onPassVisibleChange()
                            }
                    )
                }
            }

        }
    )
}