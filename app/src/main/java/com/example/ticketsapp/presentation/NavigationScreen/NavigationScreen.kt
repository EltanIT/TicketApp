package com.example.ticketsapp.presentation.NavigationScreen

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.example.ticketsapp.presentation.navigation.BottomNavHost
import com.example.ticketsapp.presentation.navigation.Route
import org.koin.androidx.compose.koinViewModel

@Composable
fun NavigationScreen(
    mainNavController: NavHostController,
    viewModel: NavigationScreenViewModel = koinViewModel()
) {

    val state = viewModel.state.value

    LaunchedEffect(key1 = state.isNotAuth) {
        if(state.isNotAuth){
            mainNavController.popBackStack()
            mainNavController.navigate(Route.SignIn.route)
        }
    }


    val navHostController = rememberNavController()


    Scaffold(
        bottomBar = {
            Row(
                Modifier
                    .height(53.dp)
                    .fillMaxWidth()
                    .clip(RoundedCornerShape(topEnd = 14.dp, topStart = 14.dp))
                    .background(
                        Color(0xffBDC0CB),
                        RoundedCornerShape(topEnd = 14.dp, topStart = 14.dp)
                    ),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceEvenly
            ) {
                state.navList.forEachIndexed { index, item ->
                    Column(
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        if (index == state.selectedIndex){
                            HorizontalDivider(
                                modifier = Modifier
                                    .width(25.dp),
                                color = Color(0xff33363F),
                                thickness = 2.dp
                            )
                        }
                        Icon(
                            painter = painterResource(id = item.icon),
                            contentDescription = "nav icon",
                            tint = Color(0xff33363F),
                            modifier = Modifier
                                .size(30.dp)
                                .clickable {
                                    viewModel.onEvent(NavigationScreenEvent.SelectItem(index))

                                    navHostController.navigate(item.route) {
                                        navHostController.graph.startDestinationRoute?.let { route ->
//                                            popUpTo(route) {
//                                                saveState = true
//                                            }
                                        }
                                        launchSingleTop = true
//                                        restoreState = true
                                    }
                                }
                        )
                    }
                }
            }
        }
    ) { padding ->

        BottomNavHost(
            modifier=  Modifier.padding(padding),
            mainNavController = mainNavController,
            navHostController = navHostController,
            startDestination = state.navList[0].route
        )
    }

}