package com.example.rackify.navigation

sealed class Screen(val route: String){
    data object Login : Screen("login")
    data object Registro : Screen("registro")
    data object Scanner : Screen("scanner")
    data object Resumen : Screen("resumen")
    data object Dashboard : Screen("dashboard")

}
