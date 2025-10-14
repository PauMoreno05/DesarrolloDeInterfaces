package com.example.myapplication


import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize // Necesario para llenar la pantalla
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme // Opcional, para dar un color de fondo
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp // Usa .sp para tamaños de fuente
import androidx.navigation.NavController
import kotlinx.coroutines.delay

@Composable
fun CustomSplashScreen(navController: NavController){
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.background),
        contentAlignment = Alignment.Center
    ) {


        Text(
            text = "Create by: Pau Moreno Catalan",
            fontSize = 12.sp,
            modifier = Modifier
                .align(Alignment.BottomCenter)
                .padding(16.dp),
            textAlign = TextAlign.Center
        )
    }

    LaunchedEffect(key1 = true) {
        delay(2000)
        navController.navigate("pantalla1")
    }
}