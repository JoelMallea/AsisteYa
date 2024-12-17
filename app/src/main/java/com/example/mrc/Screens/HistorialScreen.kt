package com.example.mrc.ui.theme

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
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
import com.example.mrc.Screens.Header
import com.example.mrc.R

class Screen2Activity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MrcTheme {
                HistorialScreen()
            }
        }
    }
}

@Composable
fun HistorialScreen() {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFF333333)) // Fondo oscuro
    ) {
        Header() // Header sin padding
        MainContent() // Contenido principal con padding
    }
}

@Composable
fun MainContent() {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp) // Padding para todo el contenido
    ) {
        Spacer(modifier = Modifier.height(16.dp))
        SearchBar()
        Spacer(modifier = Modifier.height(16.dp))
        RecentClassesList(
            listOf("Clase 01", "Clase 02", "Clase 03", "Clase 04")
        )
    }
}




@Composable
fun SearchBar() {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier.fillMaxWidth()
    ) {
        TextField(
            value = "", // Inicialmente vacío, puedes agregar lógica aquí
            onValueChange = { /* Manejar cambios aquí */ },
            modifier = Modifier
                .weight(1f)
                .background(Color(0xFFCCCCCC)),
            colors = TextFieldDefaults.colors(
                focusedContainerColor = Color(0xFFCCCCCC),
                unfocusedContainerColor = Color(0xFFCCCCCC),
                focusedTextColor = Color.Black,
                unfocusedTextColor = Color.Black
            )
        )
        IconButton(onClick = { /* Acción de búsqueda */ }) {
            Icon(
                painter = painterResource(id = R.drawable.ic_search), // Reemplaza con tu ícono
                contentDescription = null,
                tint = Color.Black,
                modifier = Modifier.size(24.dp)
            )
        }
    }
}

@Composable
fun RecentClassesList(classes: List<String>) {
    Text(
        text = "Historial de Asistencias",
        color = Color.White,
        fontSize = 20.sp,
        fontWeight = FontWeight.Bold
    )
    Spacer(modifier = Modifier.height(8.dp))
    LazyColumn {
        items(classes) { clase ->
            RecentClassItem(clase)
            Spacer(modifier = Modifier.height(8.dp))
        }
    }
}

@Composable
fun RecentClassItem(className: String) {
    Card(
        colors = CardDefaults.cardColors(containerColor = Color(0xFF444444)),
        modifier = Modifier.fillMaxWidth()
    ) {
        Row(
            modifier = Modifier.padding(16.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Column {
                Text(
                    text = className,
                    color = Color.White,
                    fontWeight = FontWeight.Bold,
                    fontSize = 18.sp
                )
                Text(
                    text = "dd/mm/aaaa",
                    color = Color.White
                )
            }
            Icon(
                painter = painterResource(id = R.drawable.ic_more_vert), // Reemplaza con tu ícono
                contentDescription = null,
                tint = Color.White
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    MrcTheme {
        HistorialScreen()
    }
}
