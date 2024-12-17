package com.example.mrc.Navigation

import LoginScreen
import androidx.compose.runtime.Composable
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.mrc.NewSessionScreen
import com.example.mrc.Screens.QRCodeScreen
import com.example.mrc.Screens.ProfileScreen
import com.example.mrc.Screens.HomeScreen
import com.example.mrc.Screens.MainScreen
import com.example.mrc.Screens.QRScannScreen
import com.example.mrc.Screens.RegisterScreen
import com.example.mrc.SplashScreen
import com.example.mrc.ui.theme.HistorialScreen

@Composable
fun NavigationWrapper() {
    val navController = rememberNavController()

    NavHost(
        navController = navController,
        startDestination = Screens.SplashScreen.route
    ) {
        composable(Screens.SplashScreen.route) { SplashScreen(navController) }
        composable(Screens.MainScreen.route) { MainScreen(navController) }
        composable(Screens.LoginScreen.route) { LoginScreen(navController) }
        composable(Screens.RegisterScreen.route) { RegisterScreen(navController) }
        composable(Screens.HomeScreen.route) { HomeScreen(navController) }
        composable(Screens.NewSessionScreen.route) { NewSessionScreen(navController) }
        composable(Screens.QRScannScreen.route) { QRScannScreen(navController) }
        composable(
            route = Screens.QRCodeScreen.route,
            arguments = listOf(
                navArgument("className") { type = NavType.StringType },
                navArgument("duration") { type = NavType.StringType }
            )
        ) { backStackEntry ->
            val className = backStackEntry.arguments?.getString("className") ?: ""
            val duration = backStackEntry.arguments?.getString("duration") ?: ""

            // Crear el contenido del QR combinando ambos valores
            val qrContent = "Clase: $className," +
                    "Duración:$duration"

            // Llamar a QRCodeScreen pasando el qrContent como parámetro
            QRCodeScreen(navController, qrContent)
        }
        composable(Screens.HistorialScreen.route) { HistorialScreen() }
        composable(Screens.ProfileScreen.route) { ProfileScreen() }
    }
}
