package com.ashishbhoi.tipcalculator.ui.theme

import androidx.compose.material.Typography
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import com.ashishbhoi.tipcalculator.R


val Roboto = FontFamily(
    Font(R.font.roboto_regular),
    Font(R.font.roboto_black, weight = FontWeight.Black),
    Font(R.font.roboto_bold, weight = FontWeight.Bold),
    Font(R.font.roboto_medium, weight = FontWeight.Medium),
    Font(R.font.roboto_light, weight = FontWeight.Light),
    Font(R.font.roboto_thin, weight = FontWeight.Thin),
    Font(R.font.roboto_italic, weight = FontWeight.Normal, style = FontStyle.Italic),
)

// Set of Material typography styles to start with
val Typography = Typography(
    h1 = TextStyle(
        fontFamily = Roboto,
        fontWeight = FontWeight.W400,
        fontSize = 57.sp,
        lineHeight = 64.sp,
        letterSpacing = (-0.25).sp,
    ),
    h2 = TextStyle(
        fontFamily = Roboto,
        fontWeight = FontWeight.W400,
        fontSize = 45.sp,
        lineHeight = 52.sp,
        letterSpacing = 0.sp,
    ),
    h3 = TextStyle(
        fontFamily = Roboto,
        fontWeight = FontWeight.W400,
        fontSize = 36.sp,
        lineHeight = 44.sp,
        letterSpacing = 0.sp,
    ),
    h4 = TextStyle(
        fontFamily = Roboto,
        fontWeight = FontWeight.W400,
        fontSize = 32.sp,
        lineHeight = 40.sp,
        letterSpacing = 0.sp,
    ),
    h5 = TextStyle(
        fontFamily = Roboto,
        fontWeight = FontWeight.W400,
        fontSize = 28.sp,
        lineHeight = 36.sp,
        letterSpacing = 0.sp,
    ),
    h6 = TextStyle(
        fontFamily = Roboto,
        fontWeight = FontWeight.W400,
        fontSize = 24.sp,
        lineHeight = 32.sp,
        letterSpacing = 0.sp,
    ),
    subtitle1 = TextStyle(
        fontFamily = Roboto,
        fontWeight = FontWeight.Normal,
        fontSize = 16.sp,
        lineHeight = 22.sp,
        letterSpacing = 0.1.sp,
    ),
    subtitle2 = TextStyle(
        fontFamily = Roboto,
        fontWeight = FontWeight.Normal,
        fontSize = 14.sp,
        lineHeight = 18.sp,
        letterSpacing = 0.1.sp,
    ),
    body1 = TextStyle(
        fontFamily = Roboto,
        fontWeight = FontWeight.Normal,
        fontSize = 16.sp,
        lineHeight = 22.sp,
        letterSpacing = 0.5.sp
    ),
    body2 = TextStyle(
        fontFamily = Roboto,
        fontWeight = FontWeight.Normal,
        fontSize = 14.sp,
        lineHeight = 20.sp,
        letterSpacing = 0.25.sp
    ),
    button = TextStyle(
        fontFamily = Roboto,
        fontWeight = FontWeight.Medium,
        fontSize = 14.sp,
        lineHeight = 22.sp,
        letterSpacing = 1.25.sp
    ),
    caption = TextStyle(
        fontFamily = Roboto,
        fontWeight = FontWeight.Normal,
        fontSize = 12.sp,
        lineHeight = 20.sp,
        letterSpacing = 0.4.sp
    ),
    overline = TextStyle(
        fontFamily = Roboto,
        fontWeight = FontWeight.Normal,
        fontSize = 10.sp,
        lineHeight = 16.sp,
        letterSpacing = 1.5.sp
    )

    /* Other default text styles to override
    button = TextStyle(
        fontFamily = FontFamily.Default,
        fontWeight = FontWeight.W500,
        fontSize = 14.sp
    ),
    caption = TextStyle(
        fontFamily = FontFamily.Default,
        fontWeight = FontWeight.Normal,
        fontSize = 12.sp
    )
    */
)