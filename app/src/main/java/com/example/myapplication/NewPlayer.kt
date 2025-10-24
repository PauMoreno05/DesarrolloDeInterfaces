package com.example.yourprojectname

import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.myapplication.R

@Composable
fun NewPlayer(modifier: Modifier = Modifier, navController: NavController) {
    val scrollState = rememberScrollState()
    val emails = listOf<String>("paumorenocatalan@gmail.com", "paumorcat2@alu.edu.gva.es.com", "correo1@prueva.com")

    var nombre by remember { mutableStateOf("") }
    var apellidos by remember { mutableStateOf("") }
    var nickname by remember { mutableStateOf("") }
    var telefono by remember { mutableStateOf("") }
    var email by remember { mutableStateOf("") }
    
    
    var expanded by remember { mutableStateOf(false) }


    var intentoGuardar by remember { mutableStateOf(false) }

    val NombreCorrecto = nombre.isNotBlank()
    val NicknameCorrecto = nickname.isNotBlank()
    val EmailCorrecto = email.isNotBlank()
    val CamposCorrectos = NombreCorrecto && NicknameCorrecto && EmailCorrecto

    val MostrarNombreError = intentoGuardar && !NombreCorrecto
    val MostrarNicknameError = intentoGuardar && !NicknameCorrecto
    val MostrarEmailError = intentoGuardar && !EmailCorrecto

    
    val guardarClick = {
        intentoGuardar = true

        if (CamposCorrectos) {
            println("Guardando datos del nuevo jugador: Nombre=$nombre, Nickname=$nickname, Email=$email")
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

        InputRow(
            icon = R.drawable.account,
            label = "Nombre *",
            value = nombre,
            onValueChange = { nombre = it },
            isError = MostrarNombreError,
            errorMens = "El nombre es obligatorio"
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
            isError = MostrarNicknameError,
            errorMens = "El nickname es obligatorio"
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

        
        Column(modifier = Modifier.fillMaxWidth()) {
            InputRow(
                icon = R.drawable.email,
                label = "Email",
                value = email,
                onValueChange = {
                    email = it
                    expanded = false
                },
                isError = MostrarEmailError,
                errorMens = "Introduce un email válido.",
                
                onInteraction = {
                    if (emails.isNotEmpty()) {
                        expanded = true
                    }
                }
            )


            
            DropdownMenu(
                expanded = expanded,
                onDismissRequest = { expanded = false },
                modifier = Modifier.fillMaxWidth(0.9f).padding(start = 64.dp)
            ) {
                emails.forEach { fixedEmail -> 
                    DropdownMenuItem(
                        text = { Text(fixedEmail) },
                        onClick = {
                            email = fixedEmail 
                            expanded = false
                        }
                    )
                }
            }
        }

        Spacer(modifier = Modifier.height(32.dp))

        Button(
            onClick = guardarClick,
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
    errorMens: String? = null,
    onInteraction: () -> Unit = {}
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
                isError = isError,
                modifier = Modifier.weight(1f),
                trailingIcon = {
                    if (label == "Email") {
                        IconButton(onClick = onInteraction) {
                            Icon(Icons.Default.ArrowDropDown, contentDescription = "Mostrar correos usados")
                        }
                    }
                }
            )
        }

        if (isError && errorMens != null) {
            Text(
                text = errorMens,
                color = MaterialTheme.colorScheme.error,
                style = MaterialTheme.typography.bodySmall,
                modifier = Modifier
                    .padding(start = 64.dp)
                    .offset(y = (-4).dp)
            )
        }
    }
}