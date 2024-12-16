package com.example.ticketsapp.presentation.TechSpecials

import android.widget.Toast
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.ticketsapp.presentation.common.AboutUserComponent
import com.example.ticketsapp.presentation.common.CustomDialog
import com.example.ticketsapp.presentation.common.UserComponent
import org.koin.androidx.compose.koinViewModel

@Composable
fun TechSpecialsScreen(
    viewModel: TechSpecialsViewModel = koinViewModel()
) {

    val state = viewModel.state.value

    val context = LocalContext.current
    LaunchedEffect(key1 = state.exception) {
        if (state.exception.isNotEmpty()){
            Toast.makeText(context, state.exception, Toast.LENGTH_SHORT).show()
            viewModel.defaultException()
        }
    }


    Column(
        Modifier
            .padding(horizontal = 20.dp)
            .fillMaxSize()
    ) {
        Spacer(modifier = Modifier.height(13.dp))
        Text(
            text = "Тех. специалисты",
            style = MaterialTheme.typography.titleMedium.copy(
                fontSize = 22.sp,
                fontWeight = FontWeight.W700,
                color = Color.Black
            ),
            modifier = Modifier.padding(start = 20.dp)
        )

        LazyColumn(
            verticalArrangement = Arrangement.spacedBy(10.dp)
        ) {
            items(state.users){ item ->
                UserComponent(
                    modifier = Modifier.fillMaxWidth(),
                    userModel = item
                ) {
                    viewModel.onEvent(TechSpecialsEvent.SelectUser(item))
                }
            }
        }
    }


    if (state.selectedUser!=null){
        CustomDialog(onClose = {
            viewModel.onEvent(TechSpecialsEvent.SelectUser(null))
        }) {
            AboutUserComponent(
                modifier = Modifier.padding(start = 20.dp, end = 20.dp, bottom = 10.dp),
                userModel = state.selectedUser
            )
        }
    }

}