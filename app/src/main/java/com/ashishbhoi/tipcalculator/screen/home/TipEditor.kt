package com.ashishbhoi.tipcalculator.screen.home

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp


@Composable
fun TipEditor(
    totalBillState: MutableState<String>,
    splitNumberState: MutableState<Int>,
    sliderPositionState: MutableState<Float>,
    sliderPercentState: MutableState<Int>,
    onValChange: (Double) -> Unit = {}
) {
    Row(
        modifier = Modifier
            .padding(8.dp)
            .fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(text = "Split")
        AddPartner(
            totalBillState = totalBillState,
            splitNumberState = splitNumberState,
            sliderPercentState = sliderPercentState
        ) { totalPerPerson ->
            onValChange(totalPerPerson)
        }
    }
    TipView(
        totalBillState = totalBillState,
        sliderPercentState = sliderPercentState
    )
    SliderView(
        totalBillState = totalBillState,
        splitNumberState = splitNumberState,
        sliderPositionState = sliderPositionState,
        sliderPercentState = sliderPercentState
    ) { totalPerPerson ->
        onValChange(totalPerPerson)
    }
}