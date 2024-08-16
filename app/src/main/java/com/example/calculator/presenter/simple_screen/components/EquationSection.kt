package com.example.calculator.presenter.simple_screen.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.calculator.domain.ButtonAction
import com.example.calculator.domain.MathOperation
import com.example.calculator.presenter.simple_screen.CalculatorViewModel

@Composable
fun EquationSection(viewModel: CalculatorViewModel) {

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .clip(RoundedCornerShape(8.dp))
            .background(Color.DarkGray)
            .padding(10.dp),
        horizontalAlignment = Alignment.End
    ) {
        CreateText(viewModel, true)
        CreateText(viewModel, false)
    }
}

@Composable
private fun CreateText(viewModel: CalculatorViewModel, isEquation: Boolean) {
    val state by viewModel.state.collectAsState()

    if (isEquation)
        Text(
            text = state.currentEquation.ifEmpty { " " },
            fontSize = 25.sp
        )
    else
        Text(
            text = state.displayNumber.ifEmpty { " " },
            fontSize = 35.sp
        )
}

@Preview(showBackground = true)
@Composable
private fun EquationSectionPreview() {

    val viewModel: CalculatorViewModel = hiltViewModel()

    viewModel.onButtonClick(ButtonAction.Number(1))
    viewModel.onButtonClick(ButtonAction.Negate)
    viewModel.onButtonClick(ButtonAction.Operation(MathOperation.Addition))
    viewModel.onButtonClick(ButtonAction.Number(3))
    viewModel.onButtonClick(ButtonAction.Decimal)
    viewModel.onButtonClick(ButtonAction.Number(2))

    Box(modifier = Modifier.padding(10.dp)) {
        EquationSection(viewModel)
    }
}