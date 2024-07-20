package com.example.calculator.presenter.simple_screen.components

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.calculator.domain.ButtonAction
import com.example.calculator.domain.ButtonData
import com.example.calculator.domain.MathOperation
import com.example.calculator.ui.theme.WarmGreen

@Composable
fun Keypad(onClick: (ButtonAction) -> Unit) {

    val rows = listOf(
        // First Row
        listOf(
            ButtonData(title = "C", textColor = Color.Red) { onClick(ButtonAction.ClearScreen) },
            ButtonData(title = "<<", textColor = Color.Green) { onClick(ButtonAction.Backspace) },
            ButtonData(title = "${MathOperation.Remainder.operator}", textColor = Color.Green) {
                onClick(ButtonAction.Operation(MathOperation.Remainder))
            },
            ButtonData(
                title = "${MathOperation.Division.operator}",
                textColor = Color.Green,
                fontSize = 65.sp
            ) { onClick(ButtonAction.Operation(MathOperation.Division)) },
        ),
        // Second Row
        listOf(
            ButtonData(title = "7") { onClick(ButtonAction.Number(7)) },
            ButtonData(title = "8") { onClick(ButtonAction.Number(8)) },
            ButtonData(title = "9") { onClick(ButtonAction.Number(9)) },
            ButtonData(
                title = "${MathOperation.Multiplication.operator}",
                textColor = Color.Green,
                fontSize = 65.sp
            ) { onClick(ButtonAction.Operation(MathOperation.Multiplication)) },
        ),
        // Third Row
        listOf(
            ButtonData(title = "4") { onClick(ButtonAction.Number(4)) },
            ButtonData(title = "5") { onClick(ButtonAction.Number(5)) },
            ButtonData(title = "6") { onClick(ButtonAction.Number(6)) },
            ButtonData(
                title = "${MathOperation.Subtraction.operator}",
                textColor = Color.Green,
                fontSize = 65.sp
            ) { onClick(ButtonAction.Operation(MathOperation.Subtraction)) },
        ),
        // Fourth Row
        listOf(
            ButtonData(title = "1") { onClick(ButtonAction.Number(1)) },
            ButtonData(title = "2") { onClick(ButtonAction.Number(2)) },
            ButtonData(title = "3") { onClick(ButtonAction.Number(3)) },
            ButtonData(
                title = "${MathOperation.Addition.operator}",
                textColor = Color.Green,
                fontSize = 65.sp
            ) { onClick(ButtonAction.Operation(MathOperation.Addition)) },
        ),
        // Fifth Row
        listOf(
            ButtonData(
                title = "${MathOperation.Addition.operator}/${MathOperation.Subtraction.operator}",
                fontSize = 30.sp
            ) {
                onClick(ButtonAction.Negate)
            },
            ButtonData(title = "0") { onClick(ButtonAction.Number(0)) },
            ButtonData(title = ".", fontSize = 65.sp) { onClick(ButtonAction.Decimal) },
            ButtonData(
                title = "=",
                backgroundColor = WarmGreen,
                fontSize = 65.sp
            ) { onClick(ButtonAction.Equal) },
        ),
    )

    Column(
        modifier = Modifier
            .clip(RoundedCornerShape(8.dp))
            .background(Color.Gray)
            .padding(top = 5.dp)
    ) {
        for (row in rows) {
            RowBuilder(row)
        }
    }
}

@Composable
private fun RowBuilder(buttons: List<ButtonData>) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(bottom = 5.dp),
        horizontalArrangement = Arrangement.SpaceEvenly
    ) {
        for (button in buttons) {
            Box(
                modifier = Modifier
                    .height(85.dp)
                    .aspectRatio(1f)
                    .clip(CircleShape)
                    .background(button.backgroundColor)
                    .clickable { button.onClick() },
                contentAlignment = Alignment.Center
            ) {
                Text(text = button.title, fontSize = button.fontSize, color = button.textColor)
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun KeypadPreview() {
    Box(modifier = Modifier.padding(10.dp)) {
        Keypad {}
    }
}