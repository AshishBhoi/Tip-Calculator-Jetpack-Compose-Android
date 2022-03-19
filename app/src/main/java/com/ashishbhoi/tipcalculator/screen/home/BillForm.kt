package com.ashishbhoi.tipcalculator.screen.home

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CornerSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.unit.dp
import com.ashishbhoi.tipcalculator.components.InputField
import com.ashishbhoi.tipcalculator.util.totalPerPersonCalculator

@ExperimentalComposeUiApi
@Composable
fun BillForm(
    splitNumberState: MutableState<Int>,
    sliderPositionState: MutableState<Float>,
    sliderPercentState: MutableState<Int>,
    onValChange: (Double) -> Unit = {}
) {
    val totalBillState = remember {
        mutableStateOf("")
    }
    val validState = remember(totalBillState.value) {
        totalBillState.value.trim().isNotEmpty()
    }
    val keyboardController = LocalSoftwareKeyboardController.current
    Surface(
        modifier = Modifier
            .padding(8.dp)
            .fillMaxWidth(),
        shape = RoundedCornerShape(corner = CornerSize(8.dp)),
        border = BorderStroke(width = 1.dp, color = Color.LightGray)
    ) {
        Column(
            modifier = Modifier.padding(2.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Top
        ) {
            InputField(
                valueState = totalBillState,
                labelID = "Enter Bill",
                enabled = true,
                isSingleLine = true,
                onAction = KeyboardActions {
                    if (!validState) return@KeyboardActions
                    onValChange(
                        totalPerPersonCalculator(
                            bill = totalBillState.value.trim().toDouble(),
                            split = splitNumberState.value,
                            tip = sliderPercentState.value
                        )
                    )
                    keyboardController?.hide()
                }
            )
            if (validState) {
                TipEditor(
                    totalBillState = totalBillState,
                    splitNumberState = splitNumberState,
                    sliderPositionState = sliderPositionState,
                    sliderPercentState = sliderPercentState
                ) { totalPerPerson ->
                    onValChange(totalPerPerson)
                }
            } else {
                Box {}
            }
        }
    }
}