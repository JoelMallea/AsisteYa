package com.example.mrc.Screens

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.mrc.R
import com.example.mrc.ui.theme.MrcTheme


class Screen7Activity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MrcTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = Color.Black
                ) {
                    ProfileScreen()
                }
            }
        }
    }
}

@Composable
fun ProfileScreen() {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFF333333)),
    ) {
        Header() // Header sin padding

        // Contenedor para el contenido del perfil
        ProfileContent()
    }
}

@Composable
fun ProfileContent() {
    Box(
        modifier = Modifier
            .fillMaxSize() // Ocupa todo el tamaño disponible
            .background(Color(0xFF333333)) // Color de fondo del contenido
            .padding(16.dp), // Padding alrededor del contenido
        contentAlignment = Alignment.Center // Centra el contenido
    ) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .background(Color.Black, shape = RoundedCornerShape(16.dp))
                .padding(16.dp), // Espaciado interno del cuadro negro
            contentAlignment = Alignment.Center // Centra el contenido interno
        ) {
            Column(
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Spacer(modifier = Modifier.height(16.dp))

                Text(
                    text = "Tu Perfil",
                    fontSize = 32.sp,
                    fontWeight = FontWeight.SemiBold,
                    color = Color.White,
                    modifier = Modifier.padding(bottom = 16.dp)
                )

                // Icono del perfil
                Image(
                    painter = painterResource(id = R.drawable.ic_profile), // Asegúrate de que este icono existe
                    contentDescription = "Ícono del Perfil",
                    modifier = Modifier
                        .size(120.dp)
                        .padding(vertical = 16.dp) // Espaciado alrededor del ícono
                )

                // Campos de perfil
                ProfileField(label = "Nombre Completo")
                Spacer(modifier = Modifier.height(12.dp))
                ProfileField(label = "Correo Electrónico")
                Spacer(modifier = Modifier.height(24.dp))

                // Botones
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceEvenly
                ) {
                    BotonAccion(text = "Editar", color = Color(0xFFFFFF00)) // Amarillo
                }
                Spacer(modifier = Modifier.height(12.dp))
                // Botones
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceEvenly
                ) {
                    BotonAccion(text = "Volver", color = Color(0xFFF44336)) // Rojo
                }
            }
        }
    }
}



@Composable
fun ProfileField(label: String) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 8.dp)
    ) {
        Text(
            text = label,
            fontSize = 18.sp,
            fontWeight = FontWeight.SemiBold,
            color = Color.White
        )
        Spacer(modifier = Modifier.height(8.dp))
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(40.dp)
                .background(Color(0xFFBDBDBD), shape = RoundedCornerShape(8.dp))
        )
    }
}

@Composable
fun BotonAccion(text: String, color: Color) {
    Button(
        onClick = { /* Acciones aquí */ },
        colors = ButtonDefaults.buttonColors(containerColor = color),
        modifier = Modifier
            .fillMaxWidth()
            .height(50.dp)
    ) {
        Text(text = text, color = Color.Black, fontSize = 18.sp)
    }
}


@Preview(showBackground = true)
@Composable
fun DefaultPreview1() {
    MrcTheme {
        ProfileScreen()
    }
}
