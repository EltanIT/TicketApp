package com.example.ticketsapp.presentation.common

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.gestures.scrollable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp

@Composable
fun CustomDropDownMenu(
    modifier: Modifier = Modifier,
    items: List<Any>,
    expanded: Boolean,
    onClose: () -> Unit,
    onSelectItemIndex: (Int) -> Unit
) {


    var listWidth by remember {
        mutableStateOf(30.dp)
    }
    val density = LocalDensity.current

    DropdownMenu(
        modifier = Modifier
            .fillMaxWidth(),
        expanded = expanded,
        onDismissRequest = onClose,
        containerColor = MaterialTheme.colorScheme.primary
    ) {
        Box(
            modifier = modifier
                .background(MaterialTheme.colorScheme.primary)
                .onGloballyPositioned {
                    listWidth = with(density) { it.size.width.toDp() }
                }
        ) {
            LazyColumn(
                verticalArrangement = Arrangement.spacedBy(10.dp),
                modifier = Modifier
                    .width(listWidth)
            ) {
                itemsIndexed(items) { index, item ->
                    Text(
                        text = "$item",
                        style = MaterialTheme.typography.bodyLarge.copy(
                            color = Color.Black,
                            textAlign = TextAlign.Start,
                            fontWeight = FontWeight.W700
                        ),
                        modifier = Modifier
                            .padding(horizontal = 10.dp,vertical = 10.dp)
                            .clickable {
                                onSelectItemIndex(index)
                                onClose()
                            }
                    )
                }
            }
        }

    }

}