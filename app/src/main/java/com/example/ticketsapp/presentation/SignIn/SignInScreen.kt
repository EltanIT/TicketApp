package com.example.ticketsapp.presentation.SignIn

import android.widget.Toast
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.ticketsapp.R
import com.example.ticketsapp.presentation.SignIn.components.AuthTextField
import com.example.ticketsapp.presentation.common.PrimaryButton
import com.example.ticketsapp.presentation.navigation.Route
import com.example.ticketsapp.presentation.ui.theme.HintColor
import org.koin.androidx.compose.koinViewModel

@Composable
fun SignInScreen(
    viewModel: SignInViewModel = koinViewModel(),
    navController: NavController
) {
    val state = viewModel.state.value
    
    
    LaunchedEffect(state.isComplete) {
        if (state.isComplete){
            navController.popBackStack(Route.SignIn.route, true)
            navController.navigate(Route.MainScreen.route)
        }
    }

    val context = LocalContext.current
    LaunchedEffect(state.exception.isNotEmpty()) {
        if (state.exception.isNotEmpty()){
            Toast.makeText(context, state.exception, Toast.LENGTH_SHORT).show()
            viewModel.defaultException()
        }
    }



    Column(
        Modifier
            .fillMaxSize()
            .padding(30.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.SpaceBetween
    ) {
        Icon(
            painter = painterResource(id = R.drawable.logo),
            contentDescription = "Logo",
            tint = Color.Black,
            modifier = Modifier
                .width(80.dp)
                .height(91.dp)
        )

        Column(
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = "Вход",
                style = MaterialTheme.typography.titleLarge.copy(
                    color = MaterialTheme.colorScheme.primary
                ),
                color = MaterialTheme.colorScheme.primary
            )
            Spacer(modifier = Modifier.height(16.dp))
            Text(
                text = "Админ не грустный, он завис...",
                style = MaterialTheme.typography.bodyMedium.copy(
                    color = HintColor
                ),
                color = HintColor
            )
        }

        Column {

            Row(
                Modifier.fillMaxWidth()
            ) {
                HorizontalDivider(
                    color = Color(0xffE0E5EC),
                    thickness = 1.dp,
                    modifier = Modifier.weight(1f)
                )
                Spacer(modifier = Modifier.width(33.dp))
                HorizontalDivider(
                    color = Color(0xffE0E5EC),
                    thickness = 1.dp,
                    modifier = Modifier.weight(1f)
                )
            }
            Spacer(modifier = Modifier.height(17.dp))

            AuthTextField(
                modifier = Modifier.fillMaxWidth(),
                value = state.email,
                hintText = "login",
                enabled = !state.isLoading,
                isError = !state.emailValid,
                onPassVisibleChange = {}) {
                    viewModel.onEvent(SignInEvent.EnteredEmail(it))
            }
            Spacer(modifier = Modifier.height(17.dp))
            AuthTextField(
                modifier = Modifier.fillMaxWidth(),
                value = state.password,
                hintText = "********",
                enabled = !state.isLoading,
                passIsVisible = state.passIsVisible,
                onPassVisibleChange = {
                    viewModel.onEvent(SignInEvent.ChangePasswordVisible)
                }) {
                viewModel.onEvent(SignInEvent.EnteredPassword(it))
            }
            Spacer(modifier = Modifier.height(15.dp))
            Text(
                text = "Забыли пароль?",
                style = MaterialTheme.typography.bodySmall.copy(
                    color = MaterialTheme.colorScheme.primary
                ),
                color = MaterialTheme.colorScheme.primary,
                modifier = Modifier.clickable {
                    navController.navigate(Route.ResetPassword.route)
                }
            )
        }
        
        PrimaryButton(
            modifier = Modifier.fillMaxWidth(),
            text = "Войти",
            isLoading = state.isLoading
        ) {
            viewModel.onEvent(SignInEvent.Login)
        }

    }
}