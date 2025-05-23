package com.example.ticketsapp.presentation.CreateRequest.components

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.example.core.domain.models.DepartmentModel
import com.example.core.domain.models.RequestTypeModel
import com.example.ticketsapp.R
import com.example.ticketsapp.presentation.SignIn.components.AuthTextField
import com.example.ticketsapp.presentation.common.CustomDropDownMenu
import com.example.ticketsapp.presentation.common.PrimaryButton
import com.example.ticketsapp.presentation.common.PrimaryTextField
import com.example.ticketsapp.presentation.ui.theme.HintColor

@Composable
fun RequestDialogComponent(
    modifier: Modifier = Modifier,

    problems: List<RequestTypeModel>,
    departments: List<DepartmentModel>,

    selectedProblem: Int,
    selectedDepartment: Int,

    description: String,
    isLoading: Boolean,

    onChangeProblemType: (Int) -> Unit,
    onChangeDepartment: (Int) -> Unit,
    onChangeDescription: (String) -> Unit,
    onClose: () -> Unit,
    onSendRequest: () -> Unit
) {

    var departmentExpanded by remember {
        mutableStateOf(false)
    }
    var problemTypeExpanded by remember {
        mutableStateOf(false)
    }

    var departmentValue by remember{
        mutableStateOf("")
    }

    val configuration = LocalConfiguration.current
    val screenHeight = configuration.screenHeightDp


    Box(
        modifier
            .fillMaxSize()
            .background(Color.Black.copy(0.5f))
            .clickable {
                onClose()
            },
        contentAlignment = Alignment.Center
    ) {
        Box(
            Modifier
                .padding(horizontal = 16.dp)
                .fillMaxSize()
                .clip(RoundedCornerShape(15.dp))
                .background(Color(0xffD9D9D9), RoundedCornerShape(15.dp))
                .border(1.dp, Color.Black, RoundedCornerShape(15.dp))
                .clickable {
                    departmentExpanded = false
                }
        ) {
            Column(
                Modifier
                    .padding(start = 20.dp, end = 20.dp, bottom = 23.dp)
                    .fillMaxSize(),
            ) {
                Box(
                    Modifier.fillMaxWidth()
                ) {
                    Text(
                        text = "Подача заявки",
                        style = MaterialTheme.typography.titleSmall.copy(
                            color = Color.Black
                        ),
                        modifier = Modifier.align(Alignment.Center)
                    )
                    IconButton(
                        onClick = onClose,
                        modifier = Modifier
                            .align(Alignment.CenterEnd)
                            .offset(x = 11.dp),
                        enabled = !isLoading
                    ) {
                        Icon(
                            painter = painterResource(id = R.drawable.close),
                            contentDescription = "close dialog",
                            tint = Color.Black,
                            modifier = Modifier
                                .offset(x = 11.dp)
                        )
                    }

                }
                Spacer(modifier = Modifier.height(8.dp))


                Column {
                    Text(
                        text = "Где:",
                        style = MaterialTheme.typography.titleMedium.copy(
                            color = Color.Black
                        ),
                        modifier = Modifier.padding(start = 11.dp)
                    )

                    Column(
                        Modifier
                            .fillMaxWidth()
                            .clickable {
                                departmentExpanded = !departmentExpanded
                            }
                            .background(
                                MaterialTheme.colorScheme.primary, RoundedCornerShape(15.dp)
                            ),
                        horizontalAlignment = Alignment.Start,
                        verticalArrangement = Arrangement.Center
                    ){
//                        AuthTextField(
//                            modifier = Modifier
//                                .padding(horizontal = 10.dp, vertical = 10.dp),
//                            value = departmentValue,
//                            hintText = "Отдел",
//                            onPassVisibleChange = { /*TODO*/ }) {
//                            onChangeDepartment(-1)
//                            departmentValue = it
//                            departmentExpanded = true
//                        }
                        TextField(
                            value = departmentValue,
                            label = {
                                Text(
                                    text = "Отдел",
                                    style = MaterialTheme.typography.bodyLarge.copy(
                                        color = HintColor
                                    )
                                )
                            },
                            textStyle = MaterialTheme.typography.bodyLarge.copy(
                                color = MaterialTheme.colorScheme.secondary,
                                textAlign = TextAlign.Start
                            ),
                            onValueChange = {
                                onChangeDepartment(-1)
                                departmentValue = it
                                departmentExpanded = true
                            },
                            modifier = Modifier
                                .fillMaxWidth()
                                .background(Color.Transparent)
                        )

                        if (departmentExpanded && departments.isNotEmpty()){
                            LazyColumn(
                                verticalArrangement = Arrangement.spacedBy(10.dp),
                                modifier = Modifier
                                    .fillMaxWidth()
                            ) {
                                itemsIndexed(departments.filter {
                                    it.name.contains(departmentValue, true)
                                }) { index, item ->
                                    Text(
                                        text = item.name,
                                        style = MaterialTheme.typography.bodyLarge.copy(
                                            color = Color.Black,
                                            textAlign = TextAlign.Start,
                                            fontWeight = FontWeight.W700
                                        ),
                                        modifier = Modifier
                                            .padding(horizontal = 10.dp,vertical = 10.dp)
                                            .clickable {
                                                onChangeDepartment(departments.indexOf(item))
                                                departmentValue = item.name
                                                departmentExpanded = false
                                            }
                                    )
                                }
                            }
                        }
//                        CustomDropDownMenu(
//                            modifier = Modifier
//                                .height((screenHeight / 2).dp)
//                                .fillMaxWidth(),
//                            items = departments.map {
//                                it.name
//                            }.filter {
//                                     it.contains(departmentValue, true)
//                            },
//                            expanded = departmentExpanded,
//                            onClose = {
//                                      departmentExpanded = false
//                            },
//                            onSelectItemIndex = {
//                                onChangeDepartment(it)
//                                departmentValue = departments[it].name
//                            }
//                        )
                    }

                }
                Spacer(modifier = Modifier.height(11.dp))

                Column {
                    Text(
                        text = "Тип проблемы:",
                        style = MaterialTheme.typography.titleMedium.copy(
                            color = Color.Black
                        ),
                        modifier = Modifier.padding(start = 11.dp)
                    )
                    Box(
                        Modifier
                            .fillMaxWidth()
                            .clickable {
                                problemTypeExpanded = !problemTypeExpanded
                            }
                            .background(
                                MaterialTheme.colorScheme.primary, RoundedCornerShape(15.dp)
                            ),
                        contentAlignment = Alignment.CenterStart
                    ) {
                        Text(
                            modifier = Modifier
                                .padding(horizontal = 10.dp, vertical = 10.dp),
                            text = problems[selectedProblem].name,
                            style = MaterialTheme.typography.bodyLarge.copy(
                                color = MaterialTheme.colorScheme.onPrimary,
                                textAlign = TextAlign.Start,
                                fontWeight = FontWeight.W700
                            )
                        )
                        CustomDropDownMenu(
                            modifier = Modifier
                                .height((screenHeight / 2).dp)
                                .fillMaxWidth(),
                            items = problems.map {
                                it.name
                            },
                            expanded = problemTypeExpanded,
                            onClose = {
                                problemTypeExpanded = false
                            },
                            onSelectItemIndex = {
                                onChangeProblemType(it)
                            }
                        )
                    }

                }
                Spacer(modifier = Modifier.height(11.dp))

                Column {
                    Text(
                        text = "Описание проблемы:",
                        style = MaterialTheme.typography.titleMedium.copy(
                            color = Color.Black
                        ),
                        modifier = Modifier.padding(start = 11.dp)
                    )
                    PrimaryTextField(
                        modifier = Modifier
                            .fillMaxWidth(),
                        value = description,
                        hintText = "Нет интернета в аудитории",
                        enabled = !isLoading
                    ) {
                        onChangeDescription(it)
                    }
                }

               Spacer(modifier = Modifier.weight(1f))

                
                PrimaryButton(
                    modifier = Modifier.fillMaxWidth(),
                    text = "Отправить заявку",
                    isLoading = isLoading,
                    onClick = onSendRequest
                )

            }
        }
    }
}