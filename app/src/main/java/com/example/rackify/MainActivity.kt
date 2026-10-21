package com.example.rackify

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.SystemBarStyle
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.rackify.navigation.NavigationEvent
import com.example.rackify.navigation.Screen
import com.example.rackify.ui.login.LoginScreen
import com.example.rackify.ui.registro.RegistroScreen
import com.example.rackify.ui.theme.RackifyBlack
import com.example.rackify.ui.theme.RackifyTheme
import com.example.rackify.viewmodel.MainViewModel
import kotlinx.coroutines.flow.collectLatest

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge(
            statusBarStyle = SystemBarStyle.dark(RackifyBlack.toArgb()),
            navigationBarStyle = SystemBarStyle.dark(RackifyBlack.toArgb())
        )
        setContent {
            RackifyTheme {
                RackifyApp()
            }
        }
    }
}

@Composable
private fun RackifyApp(
    mainViewModel: MainViewModel = viewModel()
) {
    val navController = rememberNavController()

    // Escuchar los eventos emitidos por el MainViewModel
    LaunchedEffect(Unit) {
        mainViewModel.navigationEvents.collectLatest { event ->
            when (event) {
                is NavigationEvent.NavigateTo -> {
                    navController.navigate(event.route.route) {
                        event.popupToRoute?.let { target ->
                            popUpTo(target.route) {
                                inclusive = event.inclusive
                            }
                        }
                        launchSingleTop = event.singleTop
                    }
                }
                is NavigationEvent.PopBackStack -> navController.popBackStack()
                is NavigationEvent.NavigateUp -> navController.navigateUp()
            }
        }
    }

    Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
        NavHost(
            navController = navController,
            startDestination = Screen.Login.route,
            modifier = Modifier.padding(innerPadding)
        ) {
            // Ruta de Login
            composable(Screen.Login.route) {
                LoginScreen(
                    onLoginSuccess = { username ->
                        mainViewModel.navigateTo(Screen.Dashboard)
                    },
                    onNavigateToRegistro = {
                        mainViewModel.navigateTo(Screen.Registro)
                    }
                )
            }

            // Ruta de Registro (Tu vista)
            composable(Screen.Registro.route) {
                RegistroScreen()
            }

            // Ruta temporal para Dashboard
            composable(Screen.Dashboard.route) {
                ScannerPlaceholder(username = "Usuario")
            }
        }
    }
}

@Composable
private fun ScannerPlaceholder(username: String) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFF777777))
            .padding(32.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text(text = "RACKIFY", color = Color(0xFFF6B21A))
        Text(
            text = "Bienvenido, $username",
            color = Color.White,
            modifier = Modifier.padding(top = 16.dp)
        )
        Text(
            text = "todavia no implementado.",
            color = Color.White,
            modifier = Modifier.padding(top = 8.dp)
        )
    }
}