package com.example.yourprojectname

import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
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


    var attemptedSave by remember { mutableStateOf(false) }

    val isNombreValid = nombre.isNotBlank()
    val isNicknameValid = nickname.isNotBlank()
    val allFieldsValid = isNombreValid && isNicknameValid // Para la lógica de guardado

    val showNombreError = attemptedSave && !isNombreValid
    val showNicknameError = attemptedSave && !isNicknameValid

    // Lógica al pulsar el botón de guardar
    val onSaveClicked = {
        attemptedSave = true

        if (allFieldsValid) {
            println("Guardando datos del nuevo jugador: Nombre=$nombre, Nickname=$nickname")
        } else {
            println("Fallo el guardado. Campos obligatorios marcados.")
        }
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(scrollState)
            .padding(16.dp)
    ) {
        // --- Campos de Entrada ---
        InputRow(
            icon = R.drawable.account,
            label = "Nombre *",
            value = nombre,
            onValueChange = { nombre = it },
            isError = showNombreError, // Se marca el error al intentar guardar si está vacío
            errorMessage = "El nombre es obligatorio"
        )

        InputRow(
            icon = R.drawable.img,
            label = "Apellidos",
            value = apellidos,
            onValueChange = { apellidos = it }
        )

        InputRow(
            icon = R.drawable.img,
            label = "Nickname *",
            value = nickname,
            onValueChange = { nickname = it },
            isError = showNicknameError, // Se marca el error al intentar guardar si está vacío
            errorMessage = "El nickname es obligatorio"
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
                onClick = { },
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


        Spacer(modifier = Modifier.height(32.dp))

        Button(
            onClick = onSaveClicked,
            modifier = Modifier
                .fillMaxWidth()
                .height(50.dp)
        ) {
            Text("Guardar Cambios")
        }
    }
}


@Composable
fun InputRow(
    icon: Int,
    label: String,
    value: String,
    onValueChange: (String) -> Unit,
    isError: Boolean = false,
    errorMessage: String? = null
) {
    Column(
        modifier = Modifier.fillMaxWidth()
    ) {
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
                isError = isError, // Aquí se marca el borde rojo
                modifier = Modifier.weight(1f)
            )
        }

        if (isError && errorMessage != null) {
            Text(
                text = errorMessage,
                color = MaterialTheme.colorScheme.error,
                style = MaterialTheme.typography.bodySmall,
                modifier = Modifier
                    .padding(start = 64.dp)
                    .offset(y = (-4).dp)
            )
        }
    }
}