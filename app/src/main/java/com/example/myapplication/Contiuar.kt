package com.example.myapplication

import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController

@Composable
fun Continuar(modifier: Modifier = Modifier, navController: NavController) {
    val context = LocalContext.current
    val scrollState = rememberScrollState()

    val games = mapOf(
        "Angry Birds" to R.drawable.games_angrybirds,
        "Dragon Fly" to R.drawable.games_dragonfly,
        "Hill Climbing Racing" to R.drawable.games_hillclimbingracing,
        "Radiant Defense" to R.drawable.games_radiantdefense,
        "Pocket Soccer" to R.drawable.games_pocketsoccer,
        "Ninja Jump" to R.drawable.games_ninjump,
        "Air Control" to R.drawable.games_aircontrol
    )

    var selectedGames by remember { mutableStateOf(setOf<String>()) }

    Scaffold(
        modifier = Modifier.fillMaxSize(),
        floatingActionButton = {
            FloatingActionButton(onClick = {
                val message = if (selectedGames.isEmpty()) {
                    "No has seleccionado ningún juego"
                } else {
                    val list = selectedGames.toList()
                    val formattedList = when (list.size) {
                        0 -> ""
                        1 -> list.first()
                        else -> list.dropLast(1).joinToString(", ") + " y " + list.last()
                    }
                    "Has seleccionado $formattedList"
                }

                Toast.makeText(context, message, Toast.LENGTH_LONG).show()
            }) {
                Text("V", modifier = Modifier.padding(4.dp))
            }
        },
        floatingActionButtonPosition = FabPosition.End
    ) { innerPadding ->

        Column(
            modifier = Modifier
                .padding(innerPadding)
                .fillMaxSize()
                .verticalScroll(scrollState)
                .padding(top = 8.dp, start = 16.dp, end = 16.dp)
        ) {
            games.forEach { (game, imageResId) ->
                val isSelected = game in selectedGames

                GameRow(
                    game = game,
                    imageResId = imageResId,
                    isSelected = isSelected,
                    onToggle = { checked ->
                        selectedGames = if (checked) {
                            selectedGames + game
                        } else {
                            selectedGames - game
                        }
                    }
                )
            }
        }
    }
}

@Composable
fun GameRow(game: String, imageResId: Int, isSelected: Boolean, onToggle: (Boolean) -> Unit) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .height(64.dp)
            .clickable { onToggle(!isSelected) },
        verticalAlignment = Alignment.CenterVertically
    ) {
        Image(
            painter = painterResource(id = imageResId),
            contentDescription = game,
            modifier = Modifier
                .size(80.dp)
                .padding(end = 16.dp)
        )

        Checkbox(
            checked = isSelected,
            onCheckedChange = onToggle,
            modifier = Modifier.padding(end = 8.dp)
        )

        Text(
            text = game,
            modifier = Modifier.weight(1f)
        )
    }
}