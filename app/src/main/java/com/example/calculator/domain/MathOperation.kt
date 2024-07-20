package com.example.calculator.domain

sealed class MathOperation(val operator: Char) {
    data object Addition : MathOperation('+')
    data object Subtraction : MathOperation('−')
    data object Multiplication : MathOperation('×')
    data object Division : MathOperation('÷')
    data object Remainder : MathOperation('%')
}