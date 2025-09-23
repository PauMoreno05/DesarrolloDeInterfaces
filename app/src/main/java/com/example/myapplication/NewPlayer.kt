package com.example.yourprojectname

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Phone
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.example.myapplication.R

@Composable
fun NewPlayer() {
    val scrollState = rememberScrollState()

    Column(
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(scrollState)
            .padding(16.dp)
    ) {
        // Campos de texto y avatares, de arriba a abajo.

        // Fila para el Nombre
        InputRow(
            icon = Icons.Default.Person,
            label = "Nombre",
            value = "",
            onValueChange = {}
        )

        // Fila para los Apellidos
        InputRow(
            icon = Icons.Default.Person,
            label = "Apellidos",
            value = "",
            onValueChange = {}
        )

        // Fila para el Nickname
        InputRow(
            icon = Icons.Default.Person,
            label = "Nickname",
            value = "",
            onValueChange = {}
        )

        // Fila para el Avatar y el botón
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 16.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            // Placeholder para la imagen del avatar. Reemplaza "R.drawable.tu_imagen"
            Image(
                painter = painterResource(id = R.drawable.account),
                contentDescription = "Avatar del jugador",
                modifier = Modifier.size(100.dp)
            )
            Spacer(modifier = Modifier.width(16.dp))
            Button(
                onClick = { /* Lógica para cambiar la imagen */ },
                modifier = Modifier.weight(1f)
            ) {
                Text("Change")
            }
        }

        // Fila para el Teléfono
        InputRow(
            icon = Icons.Default.Phone,
            label = "Teléfono",
            value = "",
            onValueChange = {}
        )

        // Fila para el Email
        InputRow(
            icon = Icons.Default.Email,
            label = "Email",
            value = "",
            onValueChange = {}
        )
    }
}


@Composable
fun InputRow(icon: ImageVector, label: String, value: String, onValueChange: (String) -> Unit) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 8.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Icon(
            imageVector = icon,
            contentDescription = null,
            modifier = Modifier.size(48.dp)
        )
        Spacer(modifier = Modifier.width(8.dp))
        OutlinedTextField(
            value = value,
            onValueChange = onValueChange,
            label = { Text(label) },
            modifier = Modifier.weight(1f)
        )
    }
}