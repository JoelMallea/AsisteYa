package com.example.mrc

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.text.input.KeyboardType
import androidx.navigation.NavController
import com.example.mrc.Navigation.Screens
import com.example.mrc.Screens.Header

@Composable
fun NewSessionScreen(navController: NavController) {
    val backgroundColor = Color(0xFF333333)
    val cardColor = Color.Black
    val inputColor = Color(0xFFCCCCCC)
    val buttonColor = Color(0xFF444444)
    val textColor = Color.White
    val placeholderColor = Color.Gray

    var className by remember { mutableStateOf("") }
    var duration by remember { mutableStateOf("") }

    // Verificar si ambos campos están llenos
    val isFormValid = className.isNotEmpty() && duration.isNotEmpty()

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(backgroundColor),
        contentAlignment = Alignment.Center
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier.padding(16.dp)
        ) {
            Text(
                text = "Crear Nueva Sesión",
                color = textColor,
                fontSize = 24.sp,
                fontWeight = FontWeight.Bold
            )
            Spacer(modifier = Modifier.height(40.dp))

            Box(
                modifier = Modifier
                    .clip(RoundedCornerShape(16.dp))
                    .background(cardColor)
                    .padding(16.dp)
            ) {
                Column(horizontalAlignment = Alignment.CenterHorizontally) {
                    Text(
                        text = "Ingresa los datos de la sesión",
                        color = textColor,
                        fontWeight = FontWeight.Bold,
                        fontSize = 18.sp
                    )
                    Spacer(modifier = Modifier.height(16.dp))

                    // Nombre de la clase
                    CustomTextField(
                        text = className,
                        placeholder = "Nombre de la clase",
                        onValueChange = { className = it },
                        inputColor = inputColor,
                        placeholderColor = placeholderColor,
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(56.dp)
                    )
                    Spacer(modifier = Modifier.height(8.dp))

                    // Duración en minutos
                    CustomTextField(
                        text = duration,
                        placeholder = "Duración en minutos",
                        onValueChange = {
                            if (it.all { char -> char.isDigit() }) {
                                duration = it
                            }
                        },
                        inputColor = inputColor,
                        placeholderColor = placeholderColor,
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(56.dp)
                    )
                    Spacer(modifier = Modifier.height(16.dp))

                    // Botón para generar QR
                    Button(
                        onClick = {
                            if (isFormValid) {
                                navController.navigate(
                                    "qr_code_screen/${className}/${duration}"
                                )
                            }
                        },
                        colors = ButtonDefaults.buttonColors(containerColor = buttonColor),
                        modifier = Modifier.fillMaxWidth(),
                        enabled = isFormValid // Deshabilitar el botón si los campos están vacíos
                    ) {
                        Text("Generar código QR", color = textColor)
                    }
                }
            }
        }
    }
}

@Composable
fun CustomTextField(
    text: String,
    placeholder: String,
    onValueChange: (String) -> Unit,
    inputColor: Color,
    placeholderColor: Color,
    modifier: Modifier = Modifier
) {
    Row(
        modifier = modifier
            .clip(RoundedCornerShape(12.dp))
            .background(inputColor)
            .padding(horizontal = 8.dp, vertical = 4.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        BasicTextField(
            value = text,
            onValueChange = onValueChange,
            textStyle = TextStyle(color = Color.Black),
            modifier = Modifier.weight(1f)
        ) { innerTextField ->
            if (text.isEmpty()) {
                Text(text = placeholder, color = placeholderColor)
            }
            innerTextField()
        }
    }
}
