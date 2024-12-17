package com.example.mrc.Screens

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.mrc.R
import com.example.mrc.ui.theme.MrcTheme

@Composable
fun QRScannScreen(navController: NavController) {
    var showSuccessDialog by remember { mutableStateOf(false) }

    // Simulación de escaneo de QR, esta parte se puede reemplazar por la lógica de escaneo real
    LaunchedEffect(Unit) {
        // El mensaje de éxito solo se muestra si se ha escaneado correctamente
        // Aquí podrías reemplazarlo con la lógica del escaneo real del QR
        // Simulación: después de 2 segundos, mostrar el diálogo de éxito
        kotlinx.coroutines.delay(2000)
        showSuccessDialog = true // Esto se debería activar cuando el QR se escanee correctamente
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFF303030)),
    ) {
        Header()

        // Sección QR centrada
        QRSection2(showSuccessDialog, navController) // Pasamos el NavController aquí
    }
}

@Composable
fun QRSection2(showSuccessDialog: Boolean, navController: NavController) {
    val scrollState = rememberScrollState() // Estado para el desplazamiento

    Column(
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(scrollState) // Hace que la columna sea desplazable
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Column(
            modifier = Modifier
                .background(Color.Black, shape = RoundedCornerShape(16.dp))
                .padding(16.dp), // Padding interno del cuadro
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            // Texto superior
            Text(
                text = "Código QR",
                fontSize = 20.sp,
                fontWeight = FontWeight.Bold,
                color = Color.White
            )
            Text(
                text = "Escanear Código QR",
                fontSize = 16.sp,
                color = Color.White,
                textAlign = TextAlign.Center,
                modifier = Modifier.padding(vertical = 8.dp)
            )
            // Imagen QR personalizada
            Image(
                painter = painterResource(id = R.drawable.ic_qr_code_scanner), // Reemplaza con la imagen de escaneo adecuada
                contentDescription = "Código QR",
                modifier = Modifier.size(200.dp),
                contentScale = ContentScale.FillBounds
            )
            Spacer(modifier = Modifier.height(16.dp))
            // Botón de cancelar
            Button(
                onClick = { navController.popBackStack() }, // Volver atrás con popBackStack()
                colors = ButtonDefaults.buttonColors(containerColor = Color(0xFFF44336)),
                modifier = Modifier.fillMaxWidth()
            ) {
                Text(text = "Cancelar", color = Color.White)
            }

            // Mostrar el mensaje de éxito si es necesario
            if (showSuccessDialog) {
                // Eliminar las pantallas de mensajes de éxito o error por ahora
                // Mostrar mensaje de éxito solo si showSuccessDialog es true
                Text(
                    text = "Asistencia Registrada con Éxito",
                    fontSize = 20.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color.White,
                    modifier = Modifier.padding(top = 16.dp)
                )
            }
        }
    }
}