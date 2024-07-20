package com.example.calculator.presenter.simple_screen

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.calculator.domain.ButtonAction
import com.example.calculator.presenter.simple_screen.components.EquationSection
import com.example.calculator.presenter.simple_screen.components.HistorySection
import com.example.calculator.presenter.simple_screen.components.Keypad

@Composable
fun SimpleScreen(onClick: (ButtonAction) -> Unit) {

    val spacingPadding = 5.dp

    Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
        Column(
            modifier = Modifier
                .padding(innerPadding)
                .background(Color.LightGray)
                .padding(spacingPadding)
        ) {
            HistorySection(modifier = Modifier.weight(1f), equationHistory = emptyList())
            Spacer(modifier = Modifier.height(spacingPadding))
            EquationSection(equation = "", result = "")
            Spacer(modifier = Modifier.height(spacingPadding))
            Keypad { action -> onClick(action) }
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun SimpleScreenPreview() {
    SimpleScreen {}
}