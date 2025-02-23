package com.example.botoes

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.material3.Button
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.TextButton
import androidx.compose.material3.FilledTonalButton
import androidx.compose.material3.ElevatedButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import kotlinx.coroutines.launch
import com.example.botoes.ui.theme.BotoesTheme
import kotlinx.coroutines.withTimeout

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            BotoesTheme {
                Botoes()
            }
        }
    }
}

@Composable
fun Botoes() {
    val snackbarHostState = remember { SnackbarHostState() }
    val coroutineScope = rememberCoroutineScope()

    Scaffold(
        containerColor = Color(0xFF795548),
        snackbarHost = { SnackbarHost(snackbarHostState) }
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
                .padding(16.dp),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            val botaoClick: (String) -> Unit = { nomeBotao ->
                coroutineScope.launch {
                    withTimeout(1000) {
                        snackbarHostState.showSnackbar("Você clicou no botão $nomeBotao")
                    }
                }
            }

            Button(
                onClick = { botaoClick("Filled") },
                colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF6D4C41))
            ) {
                Text("Filled", color = Color.White)
            }

            Spacer(modifier = Modifier.height(8.dp))

            FilledTonalButton(
                onClick = { botaoClick("Tonal") },
                colors = ButtonDefaults.filledTonalButtonColors(containerColor = Color(0xFF8D6E63))
            ) {
                Text("Tonal", color = Color.White)
            }

            Spacer(modifier = Modifier.height(8.dp))

            OutlinedButton(
                onClick = { botaoClick("Outlined") },
                colors = ButtonDefaults.outlinedButtonColors(contentColor = Color(0xFF5D4037))
            ) {
                Text("Outlined")
            }

            Spacer(modifier = Modifier.height(8.dp))

            ElevatedButton(
                onClick = { botaoClick("Elevated") },
                colors = ButtonDefaults.elevatedButtonColors(containerColor = Color(0xFF4E342E))
            ) {
                Text("Elevated", color = Color.White)
            }

            Spacer(modifier = Modifier.height(8.dp))

            TextButton(
                onClick = { botaoClick("Text Button") },
                colors = ButtonDefaults.textButtonColors(contentColor = Color(0xFF3E2723))
            ) {
                Text("Text Button")
            }
        }
    }
}