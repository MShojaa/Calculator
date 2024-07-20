package com.example.calculator.presenter.simple_screen.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
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

@Composable
fun HistorySection(modifier: Modifier, equationHistory: List<String>) {
    LazyColumn(
        modifier = modifier
            .fillMaxWidth()
            .clip(RoundedCornerShape(8.dp))
            .background(Color.DarkGray)
            .padding(10.dp)
    ) {
        items(equationHistory) { historyItem ->
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
                Text(text = historyItem, fontSize = 25.sp, maxLines = 1, overflow = TextOverflow.Ellipsis)
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun HistorySectionPreview() {
    Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
        Column(
            modifier = Modifier
                .padding(innerPadding)
                .padding(10.dp)
        ) {
            HistorySection(Modifier.weight(1f), listOf("1+2", "5+6=11"))
        }
    }
}