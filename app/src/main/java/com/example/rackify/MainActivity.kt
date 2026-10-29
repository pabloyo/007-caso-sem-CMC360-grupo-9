package com.example.rackify

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.SystemBarStyle
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.text.font.FontWeight
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.rackify.navigation.NavigationEvent
import com.example.rackify.navigation.Screen
import com.example.rackify.ui.configuration.ConfigurationScreen
import com.example.rackify.ui.dashboard.DashboardScreen
import com.example.rackify.ui.loading.LoadingScreen
import com.example.rackify.ui.login.LoginScreen
import com.example.rackify.ui.profile.ProfileScreen
import com.example.rackify.ui.registro.RegistroScreen
import com.example.rackify.ui.resumen.ResumenScreen
import com.example.rackify.ui.scanner.ScannerScreen
import com.example.rackify.ui.theme.JetBrainsMono
import com.example.rackify.ui.theme.RackifyBlack
import com.example.rackify.ui.theme.RackifyDarkGray
import com.example.rackify.ui.theme.RackifyLightGray
import com.example.rackify.ui.theme.RackifyOrange
import com.example.rackify.ui.theme.RackifyTheme
import com.example.rackify.ui.theme.RackifyWhite
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
    val activeUsername = mainViewModel.currentUser?.username ?: "Usuario"
    var mostrarLogoutDialog by remember { mutableStateOf(false) }

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
            composable(Screen.Login.route) {
                LoginScreen(
                    onLoginSuccess = { username ->
                        mainViewModel.loadCurrentUser()
                        mainViewModel.navigateTo(
                            screen = Screen.Scanner,
                            popUpTo = Screen.Login,
                            inclusive = true
                        )
                    },
                    onNavigateToRegistro = {
                        mainViewModel.navigateTo(Screen.Registro)
                    }
                )
            }
            composable(Screen.Registro.route) {
                RegistroScreen(
                    onBackClick = { navController.popBackStack() }
                )
            }

            composable(Screen.Dashboard.route) {
                DashboardScreen(
                    username = activeUsername,
                    onBack = { mainViewModel.navigateBack() },
                    onScanner = {
                        mainViewModel.navigateTo(
                            screen = Screen.Scanner,
                            popUpTo = Screen.Scanner,
                            singleTop = true
                        )
                    },
                    onRackSelected = { rackId ->
                        com.example.rackify.data.RackRepository.selectedRackId = rackId
                        mainViewModel.navigateTo(Screen.Resumen)
                    },
                    onLogout = { mostrarLogoutDialog = true },
                    onProfile = { mainViewModel.navigateTo(Screen.Profile) },
                    onConfiguration = { mainViewModel.navigateTo(Screen.Configuration) }
                )
            }
            composable(Screen.Scanner.route) {
                ScannerScreen(
                    username = activeUsername,
                    onScan = {
                        com.example.rackify.data.RackRepository.addScannedRack()
                        mainViewModel.navigateTo(Screen.Resumen)
                    },
                    onBack = { mainViewModel.navigateBack() },
                    onDashboard = { mainViewModel.navigateTo(Screen.Dashboard) },
                    onProfile = { mainViewModel.navigateTo(Screen.Profile) },
                    onLogout = { mostrarLogoutDialog = true },
                    onConfiguration = { mainViewModel.navigateTo(Screen.Configuration) },
                    onLoading = { mainViewModel.navigateTo(Screen.Loading) }
                )
            }
            composable(Screen.Resumen.route) {
                ResumenScreen(
                    username = activeUsername,
                    onBack = { mainViewModel.navigateBack() },
                    onScanner = {
                        mainViewModel.navigateTo(
                            screen = Screen.Scanner,
                            popUpTo = Screen.Scanner,
                            singleTop = true
                        )
                    },
                    onDashboard = { mainViewModel.navigateTo(Screen.Dashboard) },
                    onLogout = { mostrarLogoutDialog = true },
                    onProfile = { mainViewModel.navigateTo(Screen.Profile) },
                    onConfiguration = { mainViewModel.navigateTo(Screen.Configuration) }
                )
            }
            composable(Screen.Profile.route) {
                ProfileScreen(
                    username = activeUsername,
                    onScan = { mainViewModel.navigateTo(Screen.Scanner) },
                    onBack = { mainViewModel.navigateBack() },
                    onDashboard = { mainViewModel.navigateTo(Screen.Dashboard) },
                    onLogout = { mostrarLogoutDialog = true },
                    onConfiguration = { mainViewModel.navigateTo(Screen.Configuration) }
                )
            }

            composable(Screen.Configuration.route) {
                ConfigurationScreen(
                    username = activeUsername,
                    onScan = { mainViewModel.navigateTo(Screen.Scanner) },
                    onBack = { mainViewModel.navigateBack() },
                    onDashboard = { mainViewModel.navigateTo(Screen.Dashboard) },
                    onProfile = { mainViewModel.navigateTo(Screen.Profile) },
                    onLogout = { mostrarLogoutDialog = true }
                )
            }

            composable(Screen.Loading.route) {
                LoadingScreen(
                    username = activeUsername,
                    onScan = { mainViewModel.navigateTo(Screen.Scanner) },
                    onBack = { mainViewModel.navigateBack() },
                    onDashboard = { mainViewModel.navigateTo(Screen.Dashboard) },
                    onProfile = { mainViewModel.navigateTo(Screen.Profile) },
                    onLogout = { mostrarLogoutDialog = true },
                    onConfiguration = { mainViewModel.navigateTo(Screen.Configuration) },
                    textos = listOf()
                )
            }
        }
    }

    if (mostrarLogoutDialog) {
        AlertDialog(
            onDismissRequest = { mostrarLogoutDialog = false },
            title = {
                Text(
                    text = "CERRAR SESIÓN",
                    color = RackifyOrange,
                    fontFamily = JetBrainsMono,
                    fontWeight = FontWeight.Bold
                )
            },
            text = {
                Text(
                    text = "¿Estás seguro de que deseas cerrar sesión?",
                    color = RackifyWhite
                )
            },
            confirmButton = {
                TextButton(
                    onClick = {
                        mostrarLogoutDialog = false
                        mainViewModel.logout()
                    }
                ) {
                    Text(
                        text = "CONFIRMAR",
                        color = RackifyOrange,
                        fontWeight = FontWeight.Bold
                    )
                }
            },
            dismissButton = {
                TextButton(
                    onClick = { mostrarLogoutDialog = false }
                ) {
                    Text(
                        text = "CANCELAR",
                        color = RackifyLightGray
                    )
                }
            },
            containerColor = RackifyDarkGray
        )
    }
}
