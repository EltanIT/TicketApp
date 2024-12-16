package com.example.ticketsapp.presentation.CreateRequest

import android.widget.Toast
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.example.ticketsapp.R
import com.example.ticketsapp.presentation.CreateRequest.components.RequestDialogComponent
import com.example.ticketsapp.presentation.common.PrimaryButton
import com.example.ticketsapp.presentation.ui.theme.Black
import org.koin.androidx.compose.koinViewModel

@Composable
fun CreateRequestScreen(
    viewModel: CreateRequestViewModel = koinViewModel()
) {

    val state = viewModel.state.value

    val context = LocalContext.current
    LaunchedEffect(key1 = state.isComplete) {
        if (state.isComplete    ){
            Toast.makeText(context, "Заявка отправлена", Toast.LENGTH_SHORT).show()
        }
    }

    LaunchedEffect(key1 = state.exception) {
        if (state.exception.isNotEmpty()){
            Toast.makeText(context, state.exception, Toast.LENGTH_SHORT).show()
            viewModel.defaultException()
        }
    }


    Column(
        Modifier
            .fillMaxSize()
            .padding(horizontal = 58.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {

        Icon(
            painter = painterResource(id = R.drawable.cat),
            contentDescription = null,
            tint = Color.Black
        )
        Spacer(modifier = Modifier.height(35.dp))

        PrimaryButton(
            modifier = Modifier.fillMaxWidth(),
            text = "Отправить заявку",
            textColor = Black
        ) {
            viewModel.onEvent(CreateRequestEvent.OpenRequestDialog)
        }
    }

    if (state.dialogIsOpen){
        RequestDialogComponent(
            problems = state.problemTypes,
            departments = state.departments,

            selectedProblem = state.selectedProblemTypeIndex,
            selectedDepartment = state.selectedDepartmentIndex,

            description = state.description,
            isLoading = state.isLoading,


            onChangeProblemType = {
                viewModel.onEvent(CreateRequestEvent.SelectProblemTypeIndex(it))
            },
            onChangeDepartment = {
                viewModel.onEvent(CreateRequestEvent.SelectDepartmentIndex(it))
            },

            onChangeDescription = {
                viewModel.onEvent(CreateRequestEvent.EnteredDescription(it))
            },
            onClose = {
                viewModel.onEvent(CreateRequestEvent.OpenRequestDialog)
            }) {
            viewModel.onEvent(CreateRequestEvent.SendRequest)
        }
    }





}