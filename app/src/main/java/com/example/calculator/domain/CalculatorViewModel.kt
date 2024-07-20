package com.example.calculator.domain

import androidx.core.text.isDigitsOnly
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.calculator.presenter.components.CalculatorState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class CalculatorViewModel : ViewModel() {
    private val _state = MutableStateFlow(CalculatorState())
    val state = _state.asStateFlow()

    fun onButtonClick(action: ButtonAction) {
        viewModelScope.launch {
            when (action) {
                is ButtonAction.Equal -> calculate()
                is ButtonAction.Negate -> negate()
                is ButtonAction.Decimal -> decimal()
                is ButtonAction.Backspace -> backspace()
                is ButtonAction.ClearScreen -> clearScreen()
                is ButtonAction.Number -> addNumber(action.number)
                is ButtonAction.Operation -> addOperation(action.operation)
            }
        }
    }

    private fun String.removeDecimal(): String {
        return if (this[lastIndex - 1] == '.' && this[lastIndex] == '0')
            this.dropLast(2)
        else if (this[lastIndex] == '.')
            this.dropLast(1)
        else
            this.dropLast(0)
    }

    private fun calculate(currentState: CalculatorState): String {
        if (currentState.operation == null)
            return "null"

        val firstNumber = currentState.firstNumber.toDouble()
        val secondNumber = currentState.displayNumber.toDouble()

        val result = when (currentState.operation) {
            is MathOperation.Addition -> firstNumber + secondNumber
            is MathOperation.Subtraction -> firstNumber - secondNumber
            is MathOperation.Multiplication -> firstNumber * secondNumber
            is MathOperation.Division -> if (secondNumber != 0.0) firstNumber / secondNumber else "inf"
            is MathOperation.Remainder -> if (secondNumber != 0.0) firstNumber % secondNumber else "inf"
        }.toString()

        return result.removeDecimal()
    }

    private fun calculate() {
        val currentState = _state.value

        if (currentState.displayNumber.isEmpty() || currentState.currentEquation.isEmpty())
            return

        var currentEquation =
            currentState.currentEquation + currentState.displayNumber.removeDecimal() + " = "
        val displayNumber = calculate(currentState)
        currentEquation += displayNumber

        _state.value = currentState.copy(
            history = currentState.history.plus(currentEquation),
            displayNumber = displayNumber,
            firstNumber = "",
            operation = null,
            currentEquation = ""
        )
    }

    // Changes the sign of the number to negative or positive
    private fun negate() {
        val currentState = _state.value

        _state.value =
            if (currentState.displayNumber[0].isDigit()) {  // displayNumber is positive -> negative

                if (currentState.displayNumber.isDigitsOnly())  // Integer number
                    currentState.copy(displayNumber = (-currentState.displayNumber.toInt()).toString())
                else  // Floating number
                    currentState.copy(displayNumber = (-currentState.displayNumber.toDouble()).toString())

            } else {  // displayNumber is negative -> positive
                currentState.copy(displayNumber = currentState.displayNumber.drop(1))
            }
    }

    // Adds a decimal point to the displayNumber
    private fun decimal() {
        val currentState = _state.value

        // Adds a decimal point to the displayNumber if there is no decimal in the displayNumber
        if (currentState.displayNumber.isDigitsOnly())
            _state.value = currentState.copy(displayNumber = currentState.displayNumber + '.')
    }

    // Clears input one by one
    private fun backspace() {
        val currentState = _state.value

        _state.value =
            if (currentState.displayNumber.isEmpty()) {  // Removes the operator and moves the firstNumber to displayNumber

                // There is nothing left to remove
                if (currentState.operation == null)
                    return

                // Clears the operator and moves the first number to display in order to edit that
                currentState.copy(
                    operation = null,
                    displayNumber = currentState.firstNumber,
                    firstNumber = "",
                    currentEquation = ""
                )
            } else {  // Drops the last character in the displayNumber
                currentState.copy(displayNumber = currentState.displayNumber.dropLast(1))
            }
    }

    // Clears displayNumber and currentEquation on the first call
    // Clears history on the second call
    private fun clearScreen() {
        val currentState = _state.value

        _state.value =
            if (currentState.currentEquation.isEmpty() && currentState.displayNumber.isEmpty()) { // Clears displayNumber and currentEquation on the first call
                currentState.copy(history = emptyList())
            } else {  // Clears history on the second call
                currentState.copy(currentEquation = "", displayNumber = "")
            }
    }

    // Adds a number to display
    private fun addNumber(number: Int) {
        val currentState = _state.value

        val displayNumber =
            if (currentState.displayNumber == "inf" || currentState.displayNumber == "null")
                ""
            else
                currentState.displayNumber

        _state.value =
            currentState.copy(displayNumber = displayNumber + number.toString())
    }

    // Adds an operation to the currentEquation
    private fun addOperation(operation: MathOperation) {
        val currentState = _state.value

        val displayNumber =
            if (currentState.operation == null)
                currentState.displayNumber.removeDecimal()
            else
                calculate(currentState)

        val currentEquation = displayNumber + ' ' + operation.operator + ' '
        val historyEquation =
            currentState.currentEquation + currentState.displayNumber + " = " + displayNumber

        _state.value =
            if (displayNumber == "inf" || displayNumber == "null")
                currentState.copy(
                    operation = null,
                    currentEquation = "",
                    firstNumber = "",
                    displayNumber = displayNumber,
                    history = currentState.history.plus(historyEquation)
                )
            else if (currentState.operation == null)
                currentState.copy(
                    operation = operation,
                    currentEquation = currentEquation,
                    firstNumber = displayNumber,
                    displayNumber = "",
                )
            else
                currentState.copy(
                    operation = operation,
                    currentEquation = currentEquation,
                    firstNumber = displayNumber,
                    displayNumber = "",
                    history = currentState.history.plus(historyEquation)
                )
    }
}