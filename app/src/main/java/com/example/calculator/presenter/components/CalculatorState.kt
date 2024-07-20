package com.example.calculator.presenter.components

import com.example.calculator.domain.MathOperation

data class CalculatorState(
    val history: List<String> = emptyList(),
    val currentEquation: String = "",
    val displayNumber: String = "",
    val operation: MathOperation? = null,
    val firstNumber: String = "",
)
