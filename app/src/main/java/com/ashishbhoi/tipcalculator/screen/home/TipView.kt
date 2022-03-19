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
fun TipView(
    totalBillState: MutableState<String>,
    sliderPercentState: MutableState<Int>
) {
    Row(
        modifier = Modifier
            .padding(horizontal = 8.dp, vertical = 14.dp)
            .fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(text = "Tip")
        Text(
            text = "$${
                "%.2f".format(
                    totalBillState.value.trim()
                        .toDouble() * (sliderPercentState.value).toDouble() / 100
                )
            }", modifier = Modifier.padding(4.dp)
        )
    }
}