import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.draw.clip
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.ui.input.key.Key.Companion.Home
import androidx.compose.ui.res.painterResource
import androidx.navigation.NavController
import com.example.mrc.Navigation.Screens
import com.example.mrc.R
import com.example.mrc.Screens.CustomTextField
import com.google.firebase.auth.FirebaseAuth

@Composable
fun LoginScreen(navController: NavController) {
    val auth = FirebaseAuth.getInstance()
    val backgroundColor = Color(0xFF333333)
    val cardColor = Color.Black
    val inputColor = Color(0xFFCCCCCC)
    val buttonColor = Color(0xFF444444)
    val textColor = Color.White
    val placeholderColor = Color.Gray

    BoxWithConstraints( // Detecta tamaño y orientación
        modifier = Modifier
            .fillMaxSize()
            .background(backgroundColor)
    ) {
        val isLandscape = maxWidth > maxHeight // Comprueba orientación

        Row(
            modifier = Modifier.fillMaxSize(),
            horizontalArrangement = if (isLandscape) Arrangement.Center else Arrangement.Start,
            verticalAlignment = Alignment.CenterVertically
        ) {
            if (isLandscape) {
                Spacer(modifier = Modifier.weight(1f)) // Espaciador lateral
            }

            // Contenedor principal
            Box(
                modifier = Modifier
                    .clip(RoundedCornerShape(16.dp))
                    .background(cardColor)
                    .padding(16.dp)
                    .widthIn(max = if (isLandscape) 400.dp else 600.dp)
            ) {
                Column(
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Text(
                        text = "¡Bienvenido a AsisteYa!",
                        color = textColor,
                        fontSize = if (isLandscape) 20.sp else 24.sp,
                        fontWeight = FontWeight.Bold
                    )
                    Spacer(modifier = Modifier.height(16.dp))

                    Text(
                        text = "Inicia Sesión",
                        color = textColor,
                        fontWeight = FontWeight.Bold,
                        fontSize = if (isLandscape) 16.sp else 18.sp
                    )
                    Spacer(modifier = Modifier.height(16.dp))

                    var email by remember { mutableStateOf("") }
                    var password by remember { mutableStateOf("") }

                    // Email Field
                    CustomTextField(
                        text = email,
                        placeholder = "Correo electrónico",
                        onValueChange = { email = it },
                        inputColor = inputColor,
                        placeholderColor = placeholderColor
                    )
                    Spacer(modifier = Modifier.height(8.dp))

                    // Password Field
                    PasswordTextField(
                        password = password,
                        placeholder = "Contraseña",
                        onValueChange = { password = it },
                        inputColor = inputColor,
                        placeholderColor = placeholderColor,
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(56.dp)
                    )
                    Spacer(modifier = Modifier.height(8.dp))

                    // Login Button
                    Button(
                        onClick = {
                            auth.signInWithEmailAndPassword(email, password)
                                .addOnCompleteListener { task ->
                                    if (task.isSuccessful) {
                                        navController.navigate(Screens.HomeScreen.route)
                                    } else {
                                        Toast.makeText(
                                            navController.context,
                                            "Error al iniciar sesión: ${task.exception?.message}",
                                            Toast.LENGTH_SHORT
                                        ).show()
                                    }
                                }
                        },
                        colors = ButtonDefaults.buttonColors(containerColor = buttonColor),
                        modifier = Modifier.fillMaxWidth()
                    ) {
                        Text(text = "Iniciar Sesión", color = textColor)
                    }
                    Spacer(modifier = Modifier.height(16.dp))

                    // Register Text
                    Text(
                        text = "¿No tienes cuenta? Regístrate aquí",
                        color = Color.Gray,
                        fontSize = 12.sp,
                        modifier = Modifier.clickable {
                            navController.navigate(Screens.RegisterScreen.route)
                        }
                    )
                }
            }

            if (isLandscape) {
                Spacer(modifier = Modifier.weight(1f)) // Espaciador lateral
            }
        }
    }
}


@Composable
fun PasswordTextField(
    password: String,
    placeholder: String,
    onValueChange: (String) -> Unit,
    inputColor: Color,
    placeholderColor: Color,
    modifier: Modifier = Modifier // Modificador para personalización
) {
    var isPasswordVisible by remember { mutableStateOf(false) }

    Row(
        modifier = modifier
            .clip(RoundedCornerShape(12.dp)) // Bordes redondeados para el campo de texto
            .background(inputColor)
            .padding(horizontal = 8.dp, vertical = 4.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        BasicTextField(
            value = password,
            onValueChange = onValueChange,
            textStyle = TextStyle(color = Color.Black),
            visualTransformation = if (isPasswordVisible) VisualTransformation.None else PasswordVisualTransformation(),
            modifier = Modifier.weight(1f) // Ocupa el espacio restante
        ) { innerTextField ->
            if (password.isEmpty()) {
                Text(text = placeholder, color = placeholderColor)
            }
            innerTextField()
        }

        // Botón del ojito para mostrar/ocultar contraseña
        IconButton(onClick = { isPasswordVisible = !isPasswordVisible }) {
            Icon(
                painter = painterResource(
                    id = if (isPasswordVisible) R.drawable.ic_visibility else R.drawable.ic_visibility_off
                ),
                contentDescription = if (isPasswordVisible) "Ocultar contraseña" else "Mostrar contraseña",
                tint = Color.Black
            )
        }
    }
}