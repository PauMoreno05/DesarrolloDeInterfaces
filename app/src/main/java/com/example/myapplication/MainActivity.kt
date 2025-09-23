package com.example.myapplication

import android.content.res.Configuration.ORIENTATION_LANDSCAPE
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.material.icons.materialIcon
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.myapplication.ui.theme.MyApplicationTheme
import com.example.myapplication.ui.theme.Pink80
import com.example.yourprojectname.NewPlayer

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            MyApplicationTheme {
                val navController = rememberNavController()
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    NavHost(navController = navController, startDestination = "pantalla1", modifier = Modifier.padding(innerPadding)) {
                        composable("Pantalla1") { JugarGames(navController = navController) }
                        composable("Pantalla2") { NewPlayer()}
                    }
                }
            }
        }
    }
}

@Composable
fun JugarGames(modifier: Modifier = Modifier, navController: NavController){
    when(LocalConfiguration.current.orientation){
        ORIENTATION_LANDSCAPE -> {
            Orientacion_Panorama(modifier)
        }
        else -> {
            Orientacion_Retrato(modifier, navController)
        }
    }
}

@Composable
fun Orientacion_Retrato(modifier: Modifier = Modifier, navController : NavController) {
    Column(
        modifier = modifier
            .fillMaxWidth()
            .wrapContentHeight(Alignment.CenterVertically),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(16.dp) // positivo recomendado
    ) {
        Spacer(modifier = Modifier.size(120.dp))
        Text(
            text = stringResource(id = R.string.JugarGames),
            modifier = Modifier
                .height(200.dp)
                .padding(20.dp),
            fontSize = 40.sp,
            fontFamily = FontFamily.Cursive
        )
        Spacer(modifier = Modifier.size(10.dp))
        Button(
            onClick = { /**/ },
            modifier = Modifier
                .width(200.dp)
                .height(70.dp)
                .padding(2.dp),
            colors = ButtonDefaults.buttonColors(),
        ) {
            Text(
                text = "Continuars",
                textAlign = TextAlign.Center
            )
        }
        Button(
            onClick = { navController.navigate("pantalla2")  },
            modifier = Modifier
                .width(200.dp)
                .height(70.dp)
                .padding(2.dp),
            colors = ButtonDefaults.buttonColors(),
        ) {
            Text(
                text = "New Player",
                textAlign = TextAlign.Center
            )
        }
        Button(
            onClick = {/**/},
            modifier = Modifier
                .width(200.dp)
                .height(70.dp)
                .padding(2.dp),
            colors = ButtonDefaults.buttonColors(),
        ) {
            Text(
                text = "Preferences",
                textAlign = TextAlign.Center
            )
        }
        Button(
            onClick = { /**/ },
            modifier = Modifier
                .width(200.dp)
                .height(70.dp)
                .padding(2.dp),
            colors = ButtonDefaults.buttonColors(),
        ) {
            Text(
                text = "About",
                textAlign = TextAlign.Center
            )
        }
    }
}

@Composable
fun Orientacion_Panorama(modifier: Modifier = Modifier) {
    Column(
        modifier = modifier
            .fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center // positivo recomendado
    ) {

        Text(
            text = stringResource(id = R.string.JugarGames),
            modifier = Modifier
//                .height(200.dp)
                .padding(20.dp),
            fontSize = 40.sp,
            fontFamily = FontFamily.Cursive
        )

        Row { Button(
            onClick = { /**/ },
            modifier = Modifier
                .width(200.dp)
                .height(70.dp)
                .padding(2.dp),
            colors = ButtonDefaults.buttonColors(),
        ) {
            Text(
                text = "Continuar",
                textAlign = TextAlign.Center
            )
        }
            Button(
                onClick = { /**/ },
                modifier = Modifier
                    .width(200.dp)
                    .height(70.dp)
                    .padding(2.dp),
                colors = ButtonDefaults.buttonColors(),
            ) {
                Text(
                    text = "New Player",
                    textAlign = TextAlign.Center
                )
            }
        }
        Row { Button(
            onClick = { /**/ },
            modifier = Modifier
                .width(200.dp)
                .height(70.dp)
                .padding(2.dp),
            colors = ButtonDefaults.buttonColors(),
        ) {
            Text(
                text = "Preferences",
                textAlign = TextAlign.Center
            )
        }
            Button(
                onClick = { /**/ },
                modifier = Modifier
                    .width(200.dp)
                    .height(70.dp)
                    .padding(2.dp),
                colors = ButtonDefaults.buttonColors(),
            ) {
                Text(
                    text = "About",
                    textAlign = TextAlign.Center
                )
            }
        }
    }
}


@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    MyApplicationTheme {
//        JugarGames()
    }
}