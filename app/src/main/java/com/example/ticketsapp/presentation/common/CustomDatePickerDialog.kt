package com.example.ticketsapp.presentation.common

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.material3.DatePicker
import androidx.compose.material3.DatePickerDialog
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.rememberDatePickerState
import androidx.compose.runtime.Composable
import java.time.Instant
import java.util.Date

@RequiresApi(Build.VERSION_CODES.O)
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CustomDatePickerDialog(
    selectedDate: Date,
    onSelectDate: (Date) -> Unit,
    onClose: () -> Unit
) {

    val state = rememberDatePickerState(
        initialSelectedDateMillis = selectedDate.toInstant().toEpochMilli()
    )


    DatePickerDialog(
        onDismissRequest = onClose,
        confirmButton = {
            TextButton(onClick = {
                state.selectedDateMillis?.let { millis ->
                    onSelectDate(
                        Date.from(Instant.ofEpochMilli(millis))
                    )
                    onClose()
                }
            }) {
                Text("OK")
            }
        },
        dismissButton = {
            TextButton(onClick = onClose) {
                Text("Отмена")
            }
        }
    ) {
        DatePicker(
            state = state,
        )
    }
}