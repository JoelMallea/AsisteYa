package com.example.mrc.Screens

import android.content.Context
import android.widget.Toast
import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Visibility
import androidx.compose.material.icons.filled.VisibilityOff
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.mrc.Navigation.Screens
import com.example.mrc.R
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseAuthInvalidCredentialsException
import com.google.firebase.auth.FirebaseAuthUserCollisionException
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase

// Custom Input Field
@Composable
fun CustomTextField(
    text: String,
    placeholder: String,
    onValueChange: (String) -> Unit,
    inputColor: Color,
    placeholderColor: Color,
    isPassword: Boolean = false
) {
    var passwordVisible by remember { mutableStateOf(false) }

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .height(56.dp)
            .clip(RoundedCornerShape(12.dp))
            .background(inputColor)
            .padding(horizontal = 8.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        BasicTextField(
            value = text,
            onValueChange = onValueChange,
            textStyle = TextStyle(color = Color.Black, fontSize = 16.sp),
            visualTransformation = if (isPassword && !passwordVisible) PasswordVisualTransformation() else VisualTransformation.None,
            modifier = Modifier.weight(1f)
        ) { innerTextField ->
            if (text.isEmpty()) {
                Text(placeholder, color = placeholderColor, fontSize = 16.sp)
            }
            innerTextField()
        }

        if (isPassword) {
            IconButton(onClick = { passwordVisible = !passwordVisible }) {
                Icon(
                    imageVector = if (passwordVisible) Icons.Default.Visibility else Icons.Default.VisibilityOff,
                    contentDescription = if (passwordVisible) "Ocultar contraseña" else "Mostrar contraseña",
                    tint = Color.Gray
                )
            }
        }
    }
}

// Main Register Screen
@Composable
fun RegisterScreen(navController: NavController) {
    val auth = FirebaseAuth.getInstance()
    val database = FirebaseDatabase.getInstance().reference

    val context = LocalContext.current

    // UI Colors
    val backgroundColor = Color(0xFF333333)
    val cardColor = Color.Black
    val inputColor = Color(0xFFCCCCCC)
    val buttonColor = Color(0xFF444444)
    val textColor = Color.White
    val placeholderColor = Color.Gray

    // Input Fields
    var name by remember { mutableStateOf("") }
    var code by remember { mutableStateOf("") }
    var email by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    var confirmPassword by remember { mutableStateOf("") }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(backgroundColor)
            .verticalScroll(rememberScrollState()), // Scroll vertical
        contentAlignment = Alignment.Center
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier.padding(16.dp)
        ) {
            Text(
                text = "Regístrate en AsisteYa",
                color = textColor,
                fontSize = 28.sp,
                fontWeight = FontWeight.Bold,
                modifier = Modifier.padding(bottom = 24.dp)
            )

            Box(
                modifier = Modifier
                    .clip(RoundedCornerShape(16.dp))
                    .background(cardColor)
                    .padding(24.dp)
            ) {
                Column(horizontalAlignment = Alignment.CenterHorizontally) {
                    Text(
                        text = "Crea tu Cuenta",
                        color = textColor,
                        fontWeight = FontWeight.Bold,
                        fontSize = 18.sp
                    )
                    Spacer(modifier = Modifier.height(16.dp))

                    // Campos
                    CustomTextField(name, "Nombre completo", { name = it }, inputColor, placeholderColor)
                    Spacer(modifier = Modifier.height(12.dp))
                    CustomTextField(code, "Código", { code = it }, inputColor, placeholderColor)
                    Spacer(modifier = Modifier.height(12.dp))
                    CustomTextField(email, "Correo electrónico", { email = it }, inputColor, placeholderColor)
                    Spacer(modifier = Modifier.height(12.dp))
                    CustomTextField(password, "Contraseña", { password = it }, inputColor, placeholderColor, true)
                    Spacer(modifier = Modifier.height(12.dp))
                    CustomTextField(confirmPassword, "Confirmar contraseña", { confirmPassword = it }, inputColor, placeholderColor, true)
                    Spacer(modifier = Modifier.height(24.dp))

                    // Botón Registro
                    Button(
                        onClick = {
                            registerUser(auth, database, name, code, email, password, confirmPassword, navController, context)
                        },
                        colors = ButtonDefaults.buttonColors(containerColor = buttonColor),
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(50.dp)
                    ) {
                        Text("Crear Cuenta", color = textColor)
                    }
                }
            }

            Spacer(modifier = Modifier.height(16.dp))
            Text(
                text = "¿Tienes cuenta? Iniciar Sesión",
                color = Color.Gray,
                fontSize = 12.sp,
                modifier = Modifier.clickable {
                    navController.navigate(Screens.LoginScreen.route)
                }
            )
        }
    }
}

// Función Registro en Firebase
fun registerUser(
    auth: FirebaseAuth,
    database: DatabaseReference,
    name: String,
    code: String,
    email: String,
    password: String,
    confirmPassword: String,
    navController: NavController,
    context: Context
) {
    if (password.isNotEmpty() && password == confirmPassword) {
        auth.createUserWithEmailAndPassword(email, password).addOnCompleteListener { task ->
            if (task.isSuccessful) {
                val userId = auth.currentUser?.uid
                val user = mapOf(
                    "name" to name,
                    "code" to code,
                    "email" to email
                )

                userId?.let {
                    database.child("users").child(it).setValue(user)
                        .addOnSuccessListener {
                            navController.navigate(Screens.LoginScreen.route)
                        }
                        .addOnFailureListener {
                            Toast.makeText(context, "Error al guardar datos", Toast.LENGTH_SHORT).show()
                        }
                }
            } else {
                val exception = task.exception
                when (exception) {
                    is FirebaseAuthUserCollisionException -> Toast.makeText(context, "Correo ya registrado", Toast.LENGTH_SHORT).show()
                    is FirebaseAuthInvalidCredentialsException -> Toast.makeText(context, "Correo inválido", Toast.LENGTH_SHORT).show()
                    else -> Toast.makeText(context, "Error: ${exception?.message}", Toast.LENGTH_SHORT).show()
                }
            }
        }
    } else {
        Toast.makeText(context, "Las contraseñas no coinciden", Toast.LENGTH_SHORT).show()
    }
}
