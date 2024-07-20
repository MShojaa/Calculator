package com.example.calculator.domain

import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.sp

data class ButtonData(
    val title: String = "",
    val fontSize: TextUnit = 45.sp,
    val textColor: Color = Color.White,
    val backgroundColor: Color = Color.DarkGray,
    val onClick: () -> Unit
)
