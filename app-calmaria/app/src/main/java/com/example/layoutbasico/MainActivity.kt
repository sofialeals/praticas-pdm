package com.example.layoutbasico

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.layoutbasico.ui.screens.BarraNavegacao
import com.example.layoutbasico.ui.screens.HomeScreen
import com.example.layoutbasico.ui.screens.ProfileScreen
import com.example.layoutbasico.ui.theme.LayoutBasicoTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            CalmariaApp()
        }
    }
}

//Scaffold
@Composable
fun CalmariaApp() {
    val navController = rememberNavController()

    LayoutBasicoTheme {
        Scaffold(bottomBar = { BarraNavegacao(navController) }) { padding ->
            NavHost(
                navController = navController,
                startDestination = "home",
                modifier = Modifier.padding(padding)
            ) {
                composable("home") { HomeScreen() }
                composable("profile") { ProfileScreen() }
            }
        }
    }
}
