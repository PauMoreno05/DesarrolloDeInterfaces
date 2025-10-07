package com.example.myapplication

import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.clickable
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Done
import androidx.compose.material.icons.filled.Star
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.myapplication.ui.theme.MyApplicationTheme

// Componente simulado de Rating Bar para usar estrellas como en la imagen
@Composable
fun RatingBar(
    modifier: Modifier = Modifier,
    rating: Int,
    stars: Int = 10,
    onRatingChange: (Int) -> Unit
) {
    Row(modifier = modifier) {
        repeat(stars) { index ->
            val starIndex = index + 1
            val isSelected = starIndex <= rating

            // Usamos el color de MaterialTheme.colorScheme.primary para la estrella seleccionada
            val tintColor = if (isSelected) MaterialTheme.colorScheme.primary else Color.Gray

            Icon(
                imageVector = Icons.Filled.Star,
                contentDescription = "Star $starIndex",
                tint = tintColor,
                modifier = Modifier
                    .size(24.dp) // Ajustar tamaño si es necesario
                    .clickable { onRatingChange(starIndex) }
                    .padding(horizontal = 2.dp)
            )
        }
    }
}


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

    val platforms = listOf("PS4", "XBOX", "3DS", "WII", "WIIU")

    // Estado para la sección de Juegos
    var selectedGame by remember { mutableStateOf<String?>(null) }
    // El rating ahora es Int para contar las estrellas (0 a 10)
    var starRating by remember { mutableStateOf(0) } // <-- Puntuación de la barra de estrellas

    // Volvemos a añadir el rating para el Slider (Float de 0.0 a 10.0)
    var sliderRating by remember { mutableStateOf(0f) } // <-- Puntuación del Slider

    // Estado para la sección de Plataformas
    var selectedPlatform by remember { mutableStateOf<String?>(null) }

    Scaffold(
        modifier = Modifier.fillMaxSize(),
        bottomBar = {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp),
                horizontalArrangement = Arrangement.End
            ) {
                // FAB PEQUEÑO para la votación de estrellas (Ejercicio 3)
                SmallFloatingActionButton(
                    onClick = {
                        if (selectedGame != null && starRating > 0) {
                            Toast.makeText(
                                context,
                                // Usamos starRating para el Toast del botón estrella
                                "Has seleccionado $selectedGame con una puntuación de $starRating.0",
                                Toast.LENGTH_LONG
                            ).show()
                        } else if (selectedGame != null) {
                            Toast.makeText(
                                context,
                                "Has seleccionado $selectedGame, pero no has votado con estrellas",
                                Toast.LENGTH_LONG
                            ).show()
                        } else {
                            Toast.makeText(
                                context,
                                "No has pulsado ninguna opción de juego",
                                Toast.LENGTH_LONG
                            ).show()
                        }
                    },
                    modifier = Modifier.align(Alignment.CenterVertically)
                ) {
                    Icon(Icons.Filled.Star, contentDescription = "Votar")
                }

                Spacer(modifier = Modifier.width(8.dp))

                // FAB GRANDE (del Ejercicio 1/anterior)
                FloatingActionButton(onClick = {
                    if (selectedGame != null) {
                        // Usamos sliderRating para el Toast del botón "✓" (del Slider original)
                        Toast.makeText(
                            context,
                            "Has seleccionado $selectedGame con una puntuación de ${"%.1f".format(sliderRating)} (del Slider)",
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
            // --- SECCIÓN 1: JUEGOS Y SLIDER ---
            Text("Elige una opción:", style = MaterialTheme.typography.titleMedium)

            Spacer(modifier = Modifier.height(8.dp))

            // RadioButtons para selección de juegos
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

            Spacer(modifier = Modifier.height(16.dp))

            // --- SLIDER REAÑADIDO ---
            // Slider de 0 a 10
            Slider(
                value = sliderRating,
                onValueChange = { sliderRating = it },
                valueRange = 0f..10f,
                steps = 9, // pasos entre 0 y 10 para valores de 1 en 1 (0.0, 1.0, 2.0...)
                modifier = Modifier.fillMaxWidth()
            )
            Spacer(modifier = Modifier.height(24.dp))


            // --- SECCIÓN 2: BARRA DE ESTRELLAS ---
            RatingBar(
                rating = starRating,
                stars = 10, // 10 estrellas
                onRatingChange = { starRating = it },
                modifier = Modifier.fillMaxWidth()
            )




            Spacer(modifier = Modifier.height(24.dp))
            Text("Plataformas:", style = MaterialTheme.typography.titleMedium)

            Row(
                modifier = Modifier.fillMaxWidth().horizontalScroll(rememberScrollState()),
                horizontalArrangement = Arrangement.Start,
                verticalAlignment = Alignment.CenterVertically
            ) {
                platforms.forEach { platform ->
                    FilterChip(
                        selected = (selectedPlatform == platform),
                        onClick = {
                            selectedPlatform = platform
                            // Mostrar Toast con la plataforma seleccionada (Ejercicio 2)
                            Toast.makeText(
                                context,
                                "Has seleccionado $platform",
                                Toast.LENGTH_SHORT
                            ).show()
                        },
                        label = { Text(platform) },
                        leadingIcon = if (selectedPlatform == platform) {
                            {
                                Icon(
                                    imageVector = Icons.Filled.Done,
                                    contentDescription = "Selected",
                                    modifier = Modifier.size(FilterChipDefaults.IconSize)
                                )
                            }
                        } else null,
                        modifier = Modifier.padding(end = 8.dp)
                    )
                }
            }
            // --- FIN SECCIÓN 3 ---
        }
    }
}