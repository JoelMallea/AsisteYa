package com.example.mrc.Screens

import android.graphics.Bitmap
import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.asImageBitmap
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.mrc.R
import com.google.firebase.firestore.FirebaseFirestore
import com.google.zxing.BarcodeFormat
import com.journeyapps.barcodescanner.BarcodeEncoder

@Composable
fun QRCodeScreen(navController: NavController, qrContent: String) {
    var qrCodeBitmap by remember { mutableStateOf<Bitmap?>(null) }
    val db = FirebaseFirestore.getInstance()

    // Generar el QR con el contenido dinámico
    LaunchedEffect(qrContent) {
        try {
            // Generar QR
            val barcodeEncoder = BarcodeEncoder()
            qrCodeBitmap = barcodeEncoder.encodeBitmap(qrContent, BarcodeFormat.QR_CODE, 400, 400)

            // Registrar en Firebase
            val sessionData = mapOf(
                "qrContent" to qrContent,
                "generatedAt" to System.currentTimeMillis()
            )
            db.collection("sessions")
                .add(sessionData)
                .addOnSuccessListener {
                    Log.d("QRCodeScreen", "Contenido QR registrado correctamente.")
                }
                .addOnFailureListener { e ->
                    Log.e("QRCodeScreen", "Error al registrar contenido QR", e)
                }
        } catch (e: Exception) {
            Log.e("QRCodeScreen", "Error generando QR", e)
        }
    }

    // UI
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFF303030)),
        contentAlignment = Alignment.TopCenter
    ) {
        // Header fuera de la sección QR
        Header()
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp),
        ) {


            Spacer(modifier = Modifier.height(16.dp))

            // Sección QR centrada
            QRSection(qrCodeBitmap, navController)
        }
    }
}

@Composable
fun QRSection(qrCodeBitmap: Bitmap?, navController: NavController) {
    BoxWithConstraints(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp), // Espacio alrededor de la sección QR
        contentAlignment = Alignment.Center
    ) {
        val isPortrait = maxWidth < maxHeight // Determina si la orientación es vertical u horizontal

        Column(
            modifier = Modifier
                .background(Color.Black, shape = RoundedCornerShape(16.dp))
                .padding(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            // Título y descripción
            Text(
                text = "Código QR",
                fontSize = if (isPortrait) 20.sp else 16.sp,
                fontWeight = FontWeight.Bold,
                color = Color.White
            )
            Text(
                text = "Escanear Código QR",
                fontSize = if (isPortrait) 16.sp else 14.sp,
                color = Color.White,
                textAlign = TextAlign.Center,
                modifier = Modifier.padding(vertical = 8.dp)
            )

            // Mostrar el QR generado
            qrCodeBitmap?.let {
                Image(
                    bitmap = it.asImageBitmap(),
                    contentDescription = "Código QR",
                    modifier = Modifier.size(if (isPortrait) 200.dp else 150.dp),
                    contentScale = ContentScale.FillBounds
                )
            }

            Spacer(modifier = Modifier.height(16.dp))

            // Botón para regresar
            Button(
                onClick = { navController.popBackStack() },
                colors = ButtonDefaults.buttonColors(containerColor = Color(0xFFF44336)),
                modifier = Modifier
                    .padding(horizontal = 32.dp)
                    .fillMaxWidth(if (isPortrait) 1f else 0.5f) // Cambia el ancho en horizontal
            ) {
                Text("Regresar", color = Color.White)
            }
        }
    }
}
