package com.example.mrc.Screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.mrc.Navigation.Screens
import com.example.mrc.R

@Composable
fun MainScreen(navController: NavController) {
    BoxWithConstraints(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.Black)
            .padding(horizontal = 16.dp)
    ) {
        val isLandscape = maxWidth > maxHeight

        // Contenido principal
        Column(
            modifier = Modifier
                .fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            // Imagen del logo con tamaño dinámico
            Image(
                painter = painterResource(id = R.drawable.logo),
                contentDescription = "Logo AsisteYa",
                modifier = Modifier
                    .size(if (isLandscape) 150.dp else 200.dp)
                    .padding(bottom = if (isLandscape) 8.dp else 16.dp),
                contentScale = ContentScale.Fit
            )

            // Texto "AsisteYa"
            Text(
                text = "AsisteYa",
                fontSize = if (isLandscape) 28.sp else 36.sp,
                fontWeight = FontWeight.Bold,
                color = Color.White,
                modifier = Modifier.padding(bottom = if (isLandscape) 16.dp else 32.dp)
            )

            // Botones
            CustomButton(
                text = "Iniciar Sesión",
                onClick = { navController.navigate(Screens.LoginScreen.route) }
            )
            Spacer(modifier = Modifier.height(16.dp))

            CustomButton(
                text = "Registrarse",
                onClick = { navController.navigate(Screens.RegisterScreen.route) }
            )
        }
    }
}

@Composable
fun CustomButton(text: String, onClick: () -> Unit) {
    Button(
        onClick = onClick,
        colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF333333)),
        shape = RoundedCornerShape(8.dp),
        modifier = Modifier
            .fillMaxWidth()
            .height(50.dp)
    ) {
        Text(
            text = text,
            fontSize = 18.sp,
            fontWeight = FontWeight.Medium,
            color = Color.White
        )
    }
}