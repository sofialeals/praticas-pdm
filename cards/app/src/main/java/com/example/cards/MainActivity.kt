package com.example.cards

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import com.example.cards.ui.theme.CardsTheme
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import kotlinx.coroutines.launch


class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            CardsTheme {
                Cards()
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Cards() {
    val snackbarHostState = remember { SnackbarHostState() }
    val coroutineScope = rememberCoroutineScope()
    val marromEscuro = Color(0xFF5D4037)
    val marromClaro = Color(0xFFD7CCC8)
    val marromIntermediario = Color(0xFF8D6E63)

    var tituloLivro by remember { mutableStateOf("") }
    var comentarioLivro by remember { mutableStateOf("") }
    var listaLivros by remember { mutableStateOf(listOf<Pair<String, String>>()) }

    Scaffold(
        modifier = Modifier.padding(16.dp),
        snackbarHost = { SnackbarHost(snackbarHostState) },
        containerColor = marromClaro
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = "Livros Concluídos",
                fontSize = 22.sp,
                fontWeight = FontWeight.Bold,
                color = marromEscuro,
                modifier = Modifier.padding(bottom = 16.dp)
            )

            ElevatedCard(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp),
                colors = CardDefaults.elevatedCardColors(containerColor = marromIntermediario)
            ) {
                Column(
                    modifier = Modifier.padding(16.dp),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    OutlinedTextField(
                        value = tituloLivro,
                        onValueChange = { tituloLivro = it },
                        label = { Text("Título do Livro") },
                        colors = TextFieldDefaults.outlinedTextFieldColors(
                            focusedBorderColor = marromEscuro,
                            cursorColor = marromEscuro
                        ),
                        modifier = Modifier.fillMaxWidth()
                    )

                    Spacer(modifier = Modifier.height(8.dp))

                    OutlinedTextField(
                        value = comentarioLivro,
                        onValueChange = { comentarioLivro = it },
                        label = { Text("Comentário") },
                        colors = TextFieldDefaults.outlinedTextFieldColors(
                            focusedBorderColor = marromEscuro,
                            cursorColor = marromEscuro
                        ),
                        modifier = Modifier.fillMaxWidth()
                    )

                    Spacer(modifier = Modifier.height(16.dp))

                    Button(
                        onClick = {
                            if (tituloLivro.isNotBlank() && comentarioLivro.isNotBlank()) {
                                listaLivros = listaLivros + (tituloLivro to comentarioLivro)
                                tituloLivro = ""
                                comentarioLivro = ""
                            } else {
                                coroutineScope.launch {
                                    snackbarHostState.showSnackbar(
                                        "Preencha todos os campos!",
                                        duration = SnackbarDuration.Short
                                    )
                                }
                            }
                        },
                        colors = ButtonDefaults.buttonColors(containerColor = marromEscuro)
                    ) {
                        Text("Adicionar Livro", color = Color.White)
                    }
                }
            }

            LazyColumn(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp),
                verticalArrangement = Arrangement.spacedBy(12.dp)
            ) {
                items(listaLivros) { (titulo, comentario) ->
                    Card(
                        modifier = Modifier.fillMaxWidth(),
                        colors = CardDefaults.cardColors(containerColor = marromIntermediario)
                    ) {
                        Column(modifier = Modifier.padding(16.dp)) {
                            Text(titulo, fontWeight = FontWeight.Bold, fontSize = 18.sp, color = Color.White)
                            Text(comentario, color = Color.White.copy(alpha = 0.8f))
                        }
                    }
                }
            }
        }
    }
}