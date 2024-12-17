package com.example.mrc.Screens

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
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
import kotlinx.coroutines.launch

class MenuActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MrcTheme {
                ScaffoldWithDrawer()
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ScaffoldWithDrawer() {
    val drawerState = rememberDrawerState(DrawerValue.Closed)
    val scope = rememberCoroutineScope()

    ModalNavigationDrawer(
        drawerState = drawerState,
        drawerContent = {
            DrawerContent()
        },
        content = {
            // El contenido principal de tu pantalla
            Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                Button(onClick = {
                    scope.launch {
                        drawerState.open() // Abre el drawer
                    }
                }) {
                    Text("Abrir Menú")
                }
            }
        }
    )
}

@Composable
fun DrawerContent() {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.Black)
            .padding(16.dp),
        horizontalAlignment = Alignment.Start
    ) {
        // Row para el ícono de regreso y el texto
        Row(verticalAlignment = Alignment.CenterVertically) {
            IconButton(onClick = { /* Acción para regresar */ }) {
                Icon(
                    painter = painterResource(id = R.drawable.ic_back), // Icono de regresar
                    contentDescription = "Regresar",
                    tint = Color.White, // Color blanco para el ícono
                    modifier = Modifier.size(40.dp)
                )
            }

            Spacer(modifier = Modifier.width(16.dp)) // Espacio entre ícono y texto

            Text(
                text = "AsisteYa",
                fontSize = 30.sp,
                fontWeight = FontWeight.Bold,
                color = Color.White // Texto blanco
            )
        }

        Spacer(modifier = Modifier.height(32.dp))

        DrawerItem(iconId = R.drawable.ic_home, label = "Inicio")
        DrawerItem(iconId = R.drawable.ic_history, label = "Historial de Asistencias")
        DrawerItem(iconId = R.drawable.ic_profile, label = "Perfil")
        DrawerItem(iconId = R.drawable.ic_logout, label = "Cerrar Sesión")
    }
}

@Composable
fun DrawerItem(iconId: Int, label: String) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier.padding(vertical = 16.dp)
    ) {
        Icon(
            painter = painterResource(id = iconId),
            contentDescription = label,
            tint = Color.White, // Color blanco para el ícono
            modifier = Modifier.size(24.dp)
        )
        Spacer(modifier = Modifier.width(16.dp))
        Text(
            text = label,
            fontSize = 18.sp,
            fontWeight = FontWeight.SemiBold,
            color = Color.White // Texto blanco
        )
    }
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview3() {
        DrawerContent()
}
