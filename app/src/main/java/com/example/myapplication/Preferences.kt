package com.example.myapplication

import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.myapplication.ui.theme.MyApplicationTheme
import java.util.prefs.Preferences


@Composable
fun Preferences(modifier: Modifier = Modifier, navController: NavController) {
    val context = LocalContext.current

    val games = listOf(
        "Angry Birds",
        "Dragon Fly",
        "Hill Climbing Racing",
        "Pocket Soccer",
        "Radiant Defense",
        "Ninja Jump",
        "Air Control"
    )

    var selectedGame by remember { mutableStateOf<String?>(null) }
    var rating by remember { mutableStateOf(0f) }

    Scaffold(
        modifier = Modifier.fillMaxSize(),
        bottomBar = {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp),
                horizontalArrangement = Arrangement.End
            ) {
                FloatingActionButton(onClick = {
                    if (selectedGame != null) {
                        Toast.makeText(
                            context,
                            "Has seleccionado $selectedGame con una puntuación de $rating",
                            Toast.LENGTH_LONG
                        ).show()
                    } else {
                        Toast.makeText(
                            context,
                            "No has pulsado ninguna opción",
                            Toast.LENGTH_LONG
                        ).show()
                    }
                }) {
                    Text("✓")
                }
            }
        }
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .padding(innerPadding)
                .padding(16.dp)
                .fillMaxSize(),
            verticalArrangement = Arrangement.Top,
            horizontalAlignment = Alignment.Start
        ) {
            Text("Elige una opción:", style = MaterialTheme.typography.titleMedium)

            Spacer(modifier = Modifier.height(8.dp))

            games.forEach { game ->
                Row(
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    RadioButton(
                        selected = (selectedGame == game),
                        onClick = { selectedGame = game }
                    )
                    Text(text = game)
                }
            }

            Spacer(modifier = Modifier.height(24.dp))

            // Slider de 0 a 10
            Slider(
                value = rating,
                onValueChange = { rating = it },
                valueRange = 0f..10f,
                steps = 9, // pasos entre 0 y 10
                modifier = Modifier.fillMaxWidth()
            )
        }
    }
}
