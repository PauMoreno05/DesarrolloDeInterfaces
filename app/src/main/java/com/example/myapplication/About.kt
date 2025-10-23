package com.example.yourprojectname

import android.content.Context
import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Info
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.myapplication.R // Asegúrate de que esta sea la R de tu módulo

// ----------------------------------------------------------------------
// 1. CLASE DE DATOS
// ----------------------------------------------------------------------

data class Player(
    val id: Int,
    val name: String,
    val points: Int,
    val avatarResId: Int // ID del recurso Drawable (ej. R.drawable.avatar_mario)
)

// ----------------------------------------------------------------------
// 2. DATOS DE EJEMPLO (simulando los de la imagen)
// ----------------------------------------------------------------------

val dummyPlayers = listOf(
    Player(1, "Mario Mata", 3814, R.drawable.image1), // Asume R.drawable.avatar_1 existe
    Player(2, "Antonio Sanz", 3056, R.drawable.image2),
    Player(3, "Carlos Perez", 5231, R.drawable.image3),
    Player(4, "Beatriz Moreno", 1892, R.drawable.image3),
    Player(5, "Sandra Alegre", 2409, R.drawable.image3),
    Player(6, "Alex Serrat", 5874, R.drawable.image3),
    Player(7, "Ana Peris", 2218, R.drawable.image3),
    Player(8, "Paco Ortiz", 3350, R.drawable.image3)
)


// ----------------------------------------------------------------------
// 3. COMPONENTE DE LA LISTA (PlayerListScreen)
// ----------------------------------------------------------------------

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PlayerListScreen(modifier: Modifier = Modifier, navController: NavController? = null) {
    val context = LocalContext.current

    // Función para mostrar el Toast al hacer clic
    val onPlayerClick: (Player) -> Unit = { player ->
        Toast.makeText(context, "Jugador seleccionado: ${player.name}", Toast.LENGTH_SHORT).show()
    }

    // Acción para el botón "About"
    val onAboutClick: () -> Unit = {
        Toast.makeText(context, "Acerca de pulsado", Toast.LENGTH_SHORT).show()
    }

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Jugadores", fontWeight = FontWeight.Bold) },
                actions = {
                    // Item "About" (Acerca de)
                    IconButton(onClick = onAboutClick) {
                        Icon(Icons.Filled.Info, contentDescription = "Acerca de")
                    }
                }
            )
        },
        content = { paddingValues ->
            LazyColumn(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(paddingValues),
                contentPadding = PaddingValues(16.dp),
                // 💡 No usamos Arrangement.spacedBy aquí, sino Spacers dentro del LazyColumn
            ) {
                items(dummyPlayers) { player ->
                    PlayerListItem(player = player, onClick = onPlayerClick)
                    // 💡 AGREGAMOS EL SPACER DESPUÉS DE CADA ITEM para la separación visual
                    Spacer(modifier = Modifier.height(16.dp))
                }
            }
        }
    )
}

// ----------------------------------------------------------------------
// 4. COMPONENTE DE ÍTEM DE JUGADOR MODIFICADO (PlayerListItem)
// ----------------------------------------------------------------------

@Composable
fun PlayerListItem(player: Player, onClick: (Player) -> Unit) {
    // 💡 Eliminamos el contenedor Card. Usamos un Row simple.
    Row(
        modifier = Modifier
            .fillMaxWidth()
            // 💡 El color de fondo y el padding se aplican directamente al Row
            .background(MaterialTheme.colorScheme.surfaceContainerLow) // Un color sutil de fondo
            .clickable { onClick(player) }
            .padding(vertical = 12.dp, horizontal = 8.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        // Avatar del Jugador (Imagen Circular)
        Image(
            painter = painterResource(id = player.avatarResId),
            contentDescription = player.name,
            contentScale = ContentScale.Crop,
            modifier = Modifier
                .size(64.dp)
                .clip(CircleShape)
                .background(Color.LightGray)
        )

        Spacer(modifier = Modifier.width(16.dp))

        // Información de Texto
        Column(
            modifier = Modifier.weight(1f)
        ) {
            Text(
                text = player.name,
                style = MaterialTheme.typography.titleMedium,
                fontWeight = FontWeight.SemiBold,
                color = MaterialTheme.colorScheme.onSurface
            )

            Spacer(modifier = Modifier.height(4.dp))

            Text(
                text = "Puntos: ${player.points}",
                fontSize = 14.sp,
                color = MaterialTheme.colorScheme.onSurfaceVariant
            )
        }

    }
}