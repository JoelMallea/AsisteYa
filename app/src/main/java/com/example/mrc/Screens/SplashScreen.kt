package com.example.mrc

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.mrc.Navigation.Screens
import kotlinx.coroutines.delay

@Composable
fun SplashScreen(navController: NavController){
    //Simulamos carga de 2 seg
    LaunchedEffect(key1 = true) {
        delay(2000)
        navController.navigate(Screens.MainScreen.route){
            popUpTo(Screens.SplashScreen.route){ inclusive = true }
        }
    }
    Splash()
}

@Composable
fun Splash() {
    BoxWithConstraints( // Detecta el tamaño disponible
        modifier = Modifier
            .fillMaxSize()
            .background(Color.Black),
        contentAlignment = Alignment.Center
    ) {
        val isLandscape = maxWidth > maxHeight // Determina si la pantalla está en horizontal

        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = if (isLandscape) Arrangement.Center else Arrangement.spacedBy(16.dp),
            modifier = Modifier.padding(
                horizontal = if (isLandscape) 32.dp else 0.dp // Ajusta margen en horizontal
            )
        ) {
            Image(
                painter = painterResource(id = R.drawable.logo),
                contentDescription = "Logo",
                modifier = Modifier.size(if (isLandscape) 150.dp else 200.dp) // Redimensiona en horizontal
            )
            if (!isLandscape) { // Evita mostrar el texto demasiado grande en horizontal
                Spacer(modifier = Modifier.height(16.dp))
            }
            Text(
                text = "AsisteYa",
                color = Color.White,
                fontSize = if (isLandscape) 36.sp else 48.sp, // Ajusta el tamaño del texto
                fontWeight = FontWeight.Bold
            )
        }
    }
}
