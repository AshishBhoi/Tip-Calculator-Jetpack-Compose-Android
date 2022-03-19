package com.ashishbhoi.tipcalculator.screen.home

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Remove
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.ashishbhoi.tipcalculator.util.totalPerPersonCalculator
import com.ashishbhoi.tipcalculator.widgets.RoundIconButton

@Composable
fun AddPartner(
    totalBillState: MutableState<String>,
    splitNumberState: MutableState<Int>,
    sliderPercentState: MutableState<Int>,
    onValChange: (Double) -> Unit = {}
) {
    Row(verticalAlignment = Alignment.CenterVertically) {
        RoundIconButton(
            imageVector = Icons.Default.Remove,
            contentDescription = "Remove",
            onClick = {
                if (splitNumberState.value > 1) {
                    splitNumberState.value = splitNumberState.value - 1
                    onValChange(
                        totalPerPersonCalculator(
                            bill = totalBillState.value.trim().toDouble(),
                            split = splitNumberState.value,
                            tip = sliderPercentState.value
                        )
                    )
                }
            })
        Text(
            text = splitNumberState.value.toString(),
            modifier = Modifier.padding(horizontal = 8.dp)
        )
        RoundIconButton(
            imageVector = Icons.Default.Add,
            contentDescription = "Add",
            onClick = {
                splitNumberState.value = splitNumberState.value + 1
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
