package com.ashishbhoi.tipcalculator.screen

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CornerSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Slider
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Remove
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.ashishbhoi.tipcalculator.MyApp
import com.ashishbhoi.tipcalculator.components.InputField
import com.ashishbhoi.tipcalculator.ui.theme.TipCalculatorTheme
import com.ashishbhoi.tipcalculator.util.totalPerPersonCalculator
import com.ashishbhoi.tipcalculator.widgets.RoundIconButton

@ExperimentalComposeUiApi
@Composable
fun HomeScreen() {
    val splitNumberState = remember {
        mutableStateOf(1)
    }
    val sliderPositionState = remember {
        mutableStateOf(0.15f)
    }
    val sliderPercentState = remember {
        mutableStateOf(15)
    }
    val totalBillPerPerson = remember {
        mutableStateOf(0.0)
    }
    Column {
        TopHeader(totalPerPerson = totalBillPerPerson.value)
        BillForm(
            splitNumberState = splitNumberState,
            sliderPositionState = sliderPositionState,
            sliderPercentState = sliderPercentState
        ) { billAmt ->
            totalBillPerPerson.value = billAmt
        }
    }
}


@Composable
fun TopHeader(totalPerPerson: Double = 0.0) {
    val total = "%.2f".format(totalPerPerson)
    Surface(
        modifier = Modifier
            .padding(horizontal = 8.dp, vertical = 12.dp)
            .fillMaxWidth()
            .height(150.dp)
            .clip(shape = RoundedCornerShape(corner = CornerSize(12.dp))),
        color = MaterialTheme.colors.secondaryVariant
    ) {
        Column(
            modifier = Modifier.padding(12.dp),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(text = "Total per Person", style = MaterialTheme.typography.h6)
            Text(
                text = "$$total",
                style = MaterialTheme.typography.h4,
                fontWeight = FontWeight.ExtraBold
            )
        }
    }
}


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


// Tip Editor
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


// Add or remove splits
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


// Slider
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


@ExperimentalComposeUiApi
@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    TipCalculatorTheme {
        MyApp {
            HomeScreen()
        }
    }
}