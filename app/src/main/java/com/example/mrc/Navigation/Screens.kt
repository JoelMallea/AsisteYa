package com.example.mrc.Navigation

import kotlinx.serialization.Serializable

sealed class Screens(val route: String) {
    object SplashScreen : Screens("splash_screen")
    object MainScreen : Screens("main_screen")
    object LoginScreen : Screens("login_screen")
    object RegisterScreen : Screens("register_screen")
    object HomeScreen : Screens("home_screen")
    object NewSessionScreen : Screens("new_session_screen")
    object QRScannScreen : Screens("qr_scan_screen")
    object QRCodeScreen : Screens("qr_code_screen/{className}/{duration}")
    object HistorialScreen : Screens("historial_screen")
    object ProfileScreen : Screens("profile_screen")
    object EditScreen : Screens("edit_screen")
}
