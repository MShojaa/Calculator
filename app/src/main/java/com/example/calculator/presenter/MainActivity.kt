package com.example.calculator.presenter

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.calculator.presenter.simple_screen.CalculatorViewModel
import com.example.calculator.presenter.simple_screen.SimpleScreen
import com.example.calculator.ui.theme.CalculatorTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    private lateinit var viewModel: CalculatorViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            CalculatorTheme {
                viewModel = hiltViewModel()
                App(viewModel)
            }
        }
    }
}

@Composable
fun App(viewModel: CalculatorViewModel = hiltViewModel()) {
    SimpleScreen(viewModel) { action -> viewModel.onButtonClick(action) }
}

@Preview(showBackground = true)
@Composable
private fun AppPreview() {
    App()
}