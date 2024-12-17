import android.content.res.Configuration
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.tooling.preview.Preview
import com.example.mrc.Screens.Header
import com.example.mrc.ui.theme.MrcTheme

class Screen6Activity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MrcTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = Color.Black
                ) {
                    AttendanceScreen()
                }
            }
        }
    }
}

@Composable
fun AttendanceScreen() {
    // Usamos un Column que se adapta al tamaño de pantalla
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFF333333)),
    ) {
        Header() // Llamar al Header

        // Usamos un Box con Modifier.fillMaxSize para que se adapte a la orientación
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp),
            contentAlignment = Alignment.Center // Centra el contenido
        ) {
            // Usamos una Row que se adapte tanto en vertical como en horizontal
            if (isPortraitMode()) {
                Column(
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    ClaseInfo()
                    Spacer(modifier = Modifier.height(16.dp))
                    EstudiantesList()
                    Spacer(modifier = Modifier.height(16.dp))
                    ActionButtons()
                }
            } else {
                // En orientación horizontal, usamos una Row
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.Center
                ) {
                    ClaseInfo()
                    Spacer(modifier = Modifier.width(16.dp))
                    EstudiantesList()
                }
                Spacer(modifier = Modifier.height(16.dp))
                ActionButtons()
            }
        }
    }
}

@Composable
fun ClaseInfo() {
    // Información de la clase (Nombre y fecha)
    Column(
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = "Clase 01",
            fontSize = 24.sp,
            fontWeight = FontWeight.SemiBold,
            color = Color.White
        )
        Text(
            text = "dd/mm/aaaa",
            fontSize = 18.sp,
            color = Color.White,
            modifier = Modifier.padding(bottom = 16.dp)
        )
    }
}

@Composable
fun EstudiantesList() {
    // Lista de estudiantes
    val estudiantes = listOf(
        "Estudiante 1                                   00:00",
        "Estudiante 2                                   00:01",
        "Estudiante 3                                   00:02",
        "Estudiante 4                                   00:03",
        "Estudiante 5                                   00:04"
    )
    estudiantes.forEach { estudiante ->
        EstudianteCard(estudiante)
    }
}

@Composable
fun EstudianteCard(name: String) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 8.dp)
            .background(Color.Black, shape = RoundedCornerShape(8.dp))
            .padding(16.dp)
    ) {
        Text(
            text = name,
            fontSize = 18.sp,
            color = Color.White,
            modifier = Modifier.align(Alignment.CenterStart)
        )
    }
}

@Composable
fun ActionButtons() {
    // Botones de acción
    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceEvenly
    ) {
        BotonAccion(text = "Generar Reporte", color = Color(0xFF4CAF50)) // Verde
        BotonAccion(text = "Cancelar", color = Color(0xFFF44336)) // Rojo
    }
}

@Composable
fun BotonAccion(text: String, color: Color) {
    Button(
        onClick = { /* Acciones aquí */ },
        colors = ButtonDefaults.buttonColors(containerColor = color),
        modifier = Modifier
            .width(160.dp)
            .height(50.dp)
    ) {
        Text(text = text, color = Color.White, fontSize = 18.sp)
    }
}

@Composable
fun isPortraitMode(): Boolean {
    // Aquí puedes agregar lógica para determinar si el dispositivo está en modo vertical
    return LocalConfiguration.current.orientation == Configuration.ORIENTATION_PORTRAIT
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    MrcTheme {
        AttendanceScreen()
    }
}
