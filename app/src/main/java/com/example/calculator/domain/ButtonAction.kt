package com.example.calculator.domain

sealed class ButtonAction {
    data class Number(val number: Int) : ButtonAction()
    data class Operation(val operation: MathOperation): ButtonAction()
    data object Equal: ButtonAction()
    data object Backspace: ButtonAction()
    data object Decimal: ButtonAction()
    data object Negate: ButtonAction()
    data object ClearScreen: ButtonAction()
}