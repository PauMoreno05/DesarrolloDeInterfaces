package com.example.myapplication

import android.content.Context
import android.widget.Toast
import androidx.annotation.DrawableRes
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
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
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Divider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController

data class Player(
    val nombre: String,
    val puntos: Int,
    @DrawableRes val imageResId: Int 
)

val players = listOf(
    Player("María Mata", 2014, R.drawable.image1),
    Player("Antonio Sanz", 2056, R.drawable.image2),
    Player("Carlos Pérez", 5231, R.drawable.image3),
    Player("Beatriz Martos", 1892, R.drawable.image4),
    Player("Sandra Alegre", 3400, R.drawable.image5),
    Player("Alex Serrat", 5874, R.drawable.image6),
    Player("Ana Peris", 2238, R.drawable.image7),
    Player("Leonardo Fabian", 1012252, R.drawable.image8),

    )


@Composable
fun PlayersFun(player: Player, context: Context) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .clickable {
                Toast.makeText(context,player.nombre,Toast.LENGTH_SHORT).show()
            }
            .padding(vertical = 8.dp, horizontal = 16.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        
        Image(
            painter = painterResource(id = player.imageResId),
            contentDescription = "Avatar de ${player.nombre}",
            contentScale = ContentScale.Crop,
            modifier = Modifier
                .size(100.dp) 
                .clip(CircleShape)
        )

        Spacer(modifier = Modifier.width(18.dp))

        Column(
            modifier = Modifier.weight(1f), 
            verticalArrangement = Arrangement.Center
        ) {
            Text(
                text = player.nombre,
                fontSize = 18.sp,
                fontWeight = FontWeight.Bold,
                color = MaterialTheme.colorScheme.onBackground
            )
            Text(
                text = "Puntos: ${player.puntos}",
                fontSize = 14.sp,
                color = MaterialTheme.colorScheme.onBackground.copy(alpha = 0.7f)
            )
        }
    }
}

@Composable
fun About(navController: NavController) {
    val context = LocalContext.current

    Column(
        modifier = Modifier
            .fillMaxSize()
            
    ) {

        Divider(color = MaterialTheme.colorScheme.primary, thickness = 2.dp)

        LazyColumn(
            modifier = Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.spacedBy(0.dp)
        ) {
            items(players) { player ->
                PlayersFun(player = player, context = context)

                Divider(
                    color = Color.LightGray.copy(alpha = 0.7f),
                    thickness = 1.dp,
                    modifier = Modifier.padding(horizontal = 18.dp)
                )
            }
        }
    }
}
