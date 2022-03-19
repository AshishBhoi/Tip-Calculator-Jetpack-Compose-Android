package com.ashishbhoi.tipcalculator.screen.home

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import com.ashishbhoi.tipcalculator.ui.theme.TipCalculatorTheme


@ExperimentalComposeUiApi
@Composable
fun HomeScreen() {
    Scaffold(topBar = {
        TopAppBar(
            modifier = Modifier.fillMaxWidth(),
            backgroundColor = MaterialTheme.colors.secondaryVariant
        ) {
            Box(
                modifier = Modifier.fillMaxSize(),
                contentAlignment = Alignment.Center
            ) {
                Text(
                    text = "Tip Calculator",
                    style = MaterialTheme.typography.h6,
                    fontWeight = FontWeight.SemiBold
                )
            }
        }
    }) {
        Surface(
            modifier = Modifier.fillMaxSize(),
            color = MaterialTheme.colors.background
        ) {
            MainContent()
        }
    }
}


@ExperimentalComposeUiApi
@Composable
fun MainContent() {
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


@ExperimentalComposeUiApi
@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    TipCalculatorTheme {
        HomeScreen()
    }
}