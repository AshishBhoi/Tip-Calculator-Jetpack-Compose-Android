package com.ashishbhoi.tipcalculator.screen.home

import androidx.compose.foundation.layout.*
import androidx.compose.material.Slider
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.ashishbhoi.tipcalculator.util.totalPerPersonCalculator

@Composable
fun SliderView(
    totalBillState: MutableState<String>,
    splitNumberState: MutableState<Int>,
    sliderPositionState: MutableState<Float>,
    sliderPercentState: MutableState<Int>,
    onValChange: (Double) -> Unit = {}
) {
    Column(
        modifier = Modifier
            .padding(horizontal = 8.dp, vertical = 18.dp)
            .fillMaxWidth(),
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        Text(text = "${sliderPercentState.value} %")
        Spacer(modifier = Modifier.height(14.dp))
        //Slider
        Slider(
            modifier = Modifier.padding(horizontal = 16.dp),
            value = sliderPositionState.value,
            onValueChange = { newVal ->
                sliderPositionState.value = newVal
                sliderPercentState.value = (newVal * 100).toInt()
                onValChange(
                    totalPerPersonCalculator(
                        bill = totalBillState.value.trim().toDouble(),
                        split = splitNumberState.value,
                        tip = sliderPercentState.value
                    )
                )
            })
    }
}