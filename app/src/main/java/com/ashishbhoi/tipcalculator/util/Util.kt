package com.ashishbhoi.tipcalculator.util

fun totalPerPersonCalculator(bill: Double, split: Int, tip: Int): Double {
    val total = bill * (100.0 + tip.toDouble()) / 100.0
    return total / split.toDouble()
}


