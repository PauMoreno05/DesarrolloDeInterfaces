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

    var nombre by remember { mutableStateOf("") }
    var apellidos by remember { mutableStateOf("") }
    var nickname by remember { mutableStateOf("") }
    var telefono by remember { mutableStateOf("") }
    var email by remember { mutableStateOf("") }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(scrollState)
            .padding(16.dp)
    ) {
        InputRow(
            icon = R.drawable.account,
            label = "Nombre",
            value = nombre,
            onValueChange = { nombre = it }
        )

        InputRow(
            icon = R.drawable.img,
            label = "Apellidos",
            value = apellidos,
            onValueChange = { apellidos = it }
        )

        InputRow(
            icon = R.drawable.img,
            label = "Nickname",
            value = nickname,
            onValueChange = { nickname = it }
        )

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 16.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Image(
                painter = painterResource(id = R.drawable.android),
                contentDescription = "Avatar del jugador",
                modifier = Modifier.size(100.dp)
            )
            Spacer(modifier = Modifier.width(16.dp))
            Button(
                onClick = { /* Lógica para cambiar imagen */ },
                modifier = Modifier.weight(1f)
            ) {
                Text("Change")
            }
        }

        InputRow(
            icon = R.drawable.camera,
            label = "Teléfono",
            value = telefono,
            onValueChange = { telefono = it }
        )

        InputRow(
            icon = R.drawable.email,
            label = "Email",
            value = email,
            onValueChange = { email = it }
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
        Image(
            painter = painterResource(id = icon),
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



