package com.example.calculator.presenter.simple_screen.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
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

@Composable
fun EquationSection(equation: String, result: String) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .clip(RoundedCornerShape(8.dp))
            .background(Color.DarkGray)
            .padding(10.dp),
        horizontalAlignment = Alignment.End
    ) {
        Text(text = equation.ifEmpty { " " }, fontSize = 25.sp)
        Text(text = result.ifEmpty { " " }, fontSize = 35.sp)
    }
}

@Preview(showBackground = true)
@Composable
private fun EquationSectionPreview() {
    Box(modifier = Modifier.padding(10.dp)){
        EquationSection("1+2=3", "-5")
    }
}