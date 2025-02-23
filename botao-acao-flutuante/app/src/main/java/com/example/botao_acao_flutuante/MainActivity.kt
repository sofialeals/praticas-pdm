package com.example.botao_acao_flutuante

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import com.example.botao_acao_flutuante.ui.theme.BotaoAcaoFlutuanteTheme
import androidx.compose.foundation.layout.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.LargeFloatingActionButton
import androidx.compose.material3.ExtendedFloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.Refresh

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            BotaoAcaoFlutuanteTheme {
                BotoesAcaoFlutuante()
            }
        }
    }
}

@OptIn(ExperimentalLayoutApi::class)
@Composable
fun BotoesAcaoFlutuante() {
    var curtidas by remember { mutableIntStateOf(0) }
    var botoesCurtida by remember { mutableStateOf(listOf(0)) }
    val marromEscuro = Color(0xFF5D4037)
    val marromClaro = Color(0xFFD7CCC8)
    val marromIntermediario = Color(0xFF8D6E63)

    Scaffold(
        modifier = Modifier.padding(16.dp),
        containerColor = marromClaro
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding),
            verticalArrangement = Arrangement.SpaceBetween,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Column(
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(
                    text = "Curtidas",
                    fontSize = 20.sp,
                    fontWeight = FontWeight.Bold,
                    color = marromEscuro
                )
                Text(
                    text = "$curtidas",
                    fontSize = 26.sp,
                    fontWeight = FontWeight.Bold,
                    color = marromEscuro
                )

                FlowRow(
                    modifier = Modifier.padding(top = 16.dp),
                    horizontalArrangement = Arrangement.spacedBy(12.dp),
                    verticalArrangement = Arrangement.spacedBy(12.dp)
                ) {
                    botoesCurtida.forEach { _ ->
                        LargeFloatingActionButton(
                            onClick = { curtidas++ },
                            containerColor = marromIntermediario,
                            contentColor = Color.White
                        ) {
                            Icon(Icons.Default.Favorite, contentDescription = "Curtir")
                        }
                    }
                }
            }

            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.spacedBy(16.dp)
            ) {

                Row(
                    horizontalArrangement = Arrangement.spacedBy(16.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    FloatingActionButton(
                        onClick = {
                            botoesCurtida = botoesCurtida + (botoesCurtida.size)
                        },
                        containerColor = marromEscuro,
                        contentColor = Color.White
                    ) {
                        Icon(Icons.Default.Add, contentDescription = "Adicionar Curtida")
                    }

                    FloatingActionButton(
                        onClick = {
                            if (botoesCurtida.isNotEmpty()) {
                                botoesCurtida = botoesCurtida.dropLast(1)
                            }
                        },
                        containerColor = marromEscuro,
                        contentColor = Color.White
                    ) {
                        Icon(Icons.Default.Delete, contentDescription = "Remover Curtida")
                    }
                }

                ExtendedFloatingActionButton(
                    onClick = {
                        curtidas = 0
                        botoesCurtida = listOf(0)
                    },
                    icon = { Icon(Icons.Default.Refresh, contentDescription = "Reiniciar") },
                    text = { Text("Reiniciar") },
                    containerColor = marromEscuro,
                    contentColor = Color.White
                )
            }
        }
    }
}