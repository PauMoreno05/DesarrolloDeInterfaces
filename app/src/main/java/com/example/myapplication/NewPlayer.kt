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
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.myapplication.R

@Composable
fun NewPlayer(modifier: Modifier = Modifier, navController: NavController) {
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
            icon = R.drawable.account,
            label = "Nombre",
            value = "",
            onValueChange = {}
        )

        // Fila para los Apellidos
        InputRow(
            icon = R.drawable.img,
            label = "Apellidos",
            value = "",
            onValueChange = {}
        )

        // Fila para el Nickname
        InputRow(
            icon = R.drawable.img,
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
                painter = painterResource(id = R.drawable.android),
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
            icon = R.drawable.camera,
            label = "Teléfono",
            value = "",
            onValueChange = {}
        )

        // Fila para el Email
        InputRow(
            icon = R.drawable.email,
            label = "Email",
            value = "",
            onValueChange = {}
        )
    }
}


@Composable
fun InputRow(icon: Int, label: String, value: String, onValueChange: (String) -> Unit) {
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

private fun RowScope.Icon(
    imageVector: Int,
    contentDescription: Nothing?,
    modifier: Modifier
) {
}
