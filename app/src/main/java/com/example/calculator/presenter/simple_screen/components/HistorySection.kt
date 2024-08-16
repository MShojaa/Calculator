package com.example.calculator.presenter.simple_screen.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.calculator.domain.ButtonAction
import com.example.calculator.domain.MathOperation
import com.example.calculator.presenter.simple_screen.CalculatorViewModel

@Composable
fun HistorySection(modifier: Modifier, viewModel: CalculatorViewModel) {

    val state by viewModel.state.collectAsState()

    LazyColumn(
        modifier = modifier
            .fillMaxWidth()
            .clip(RoundedCornerShape(8.dp))
            .background(Color.DarkGray)
            .padding(10.dp)
    ) {
        items(state.history) { historyItem ->
            Box(modifier = Modifier
                .fillMaxWidth()
                .drawBehind {
                    val offset = 65f
                    val y = size.height

                    drawLine(
                        color = Color.Black,
                        start = Offset(0f + offset, y),
                        end = Offset(size.width - offset, y),
                        strokeWidth = 4f
                    )
                }
                .padding(5.dp), contentAlignment = Alignment.CenterEnd) {
                Text(
                    text = historyItem,
                    fontSize = 25.sp,
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis
                )
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun HistorySectionPreview() {

    val viewModel: CalculatorViewModel = hiltViewModel()

    viewModel.onButtonClick(ButtonAction.Number(1))
    viewModel.onButtonClick(ButtonAction.Operation(MathOperation.Addition))
    viewModel.onButtonClick(ButtonAction.Number(3))
    viewModel.onButtonClick(ButtonAction.Equal)
    viewModel.onButtonClick(ButtonAction.Backspace)

    viewModel.onButtonClick(ButtonAction.Number(5))
    viewModel.onButtonClick(ButtonAction.Operation(MathOperation.Multiplication))
    viewModel.onButtonClick(ButtonAction.Number(4))
    viewModel.onButtonClick(ButtonAction.Equal)
    viewModel.onButtonClick(ButtonAction.Backspace)
    viewModel.onButtonClick(ButtonAction.Backspace)

    viewModel.onButtonClick(ButtonAction.Number(1))
    viewModel.onButtonClick(ButtonAction.Decimal)
    viewModel.onButtonClick(ButtonAction.Number(3))
    viewModel.onButtonClick(ButtonAction.Operation(MathOperation.Subtraction))
    viewModel.onButtonClick(ButtonAction.Number(2))
    viewModel.onButtonClick(ButtonAction.Equal)

    Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
        Column(
            modifier = Modifier
                .padding(innerPadding)
                .padding(10.dp)
        ) {
            HistorySection(
                modifier = Modifier.weight(1f),
                viewModel = viewModel
            )
            Box(modifier = Modifier.height(500.dp))
        }
    }
}