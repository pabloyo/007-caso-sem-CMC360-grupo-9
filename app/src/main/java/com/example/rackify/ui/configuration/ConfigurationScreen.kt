package com.example.rackify.ui.configuration

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.Contrast
import androidx.compose.material.icons.filled.Domain
import androidx.compose.material.icons.filled.Group
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Switch
import androidx.compose.material3.SwitchDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.rackify.ui.components.RackifyBottomBar
import com.example.rackify.ui.components.RackifyDestination
import com.example.rackify.ui.components.RackifyTopBar
import com.example.rackify.ui.profile.ProfileScreen
import com.example.rackify.ui.theme.JetBrainsMono
import com.example.rackify.ui.theme.RackifyBlack
import com.example.rackify.ui.theme.RackifyDarkGray
import com.example.rackify.ui.theme.RackifyLightGray
import com.example.rackify.ui.theme.RackifyOrange
import com.example.rackify.ui.theme.RackifyTheme
import com.example.rackify.ui.theme.Ubuntu

@Composable
fun ConfigurationScreen(
    username: String,
    onScan: () -> Unit,
    onBack: () -> Unit,
    onDashboard: () -> Unit,
    onLogout: () -> Unit,
    onThemeToggle: (Boolean) -> Unit,
    isDarkTheme: Boolean
) {
    Scaffold(
        containerColor = RackifyBlack,
        topBar = {
            RackifyTopBar(username = username, onLogout = onLogout, onProfile = {})
        },
        bottomBar = {
            RackifyBottomBar(
                current = RackifyDestination.CONFIGURACION,
                onConfiguration = { },
                onScanner = onScan,
                onDashboard = onDashboard
            )
        }
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
                .padding(horizontal = 28.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.SpaceBetween
        ) {
            Text(
                text = "CONFIGURACIÓN",
                color = RackifyOrange,
                fontSize = 30.sp,
                fontFamily = JetBrainsMono,
                fontWeight = FontWeight.Bold,
                modifier = Modifier.padding(top = 18.dp, bottom = 8.dp)

            )
            Column(
                verticalArrangement = Arrangement.Center,
                modifier = Modifier.fillMaxWidth()
            ){
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.SpaceBetween,
                ) {
                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.spacedBy(16.dp)
                    ) {
                        Icon(
                            imageVector = Icons.Default.Contrast,
                            contentDescription = "Profile",
                            tint = RackifyOrange,
                            modifier = Modifier
                                .size(30.dp)
                        )
                        Column(
                            verticalArrangement = Arrangement.Center
                        ) {
                            Text(
                                text = "Modo Oscuro",
                                color = RackifyOrange,
                                fontSize = 20.sp,
                                fontWeight = FontWeight.Bold,
                                fontFamily = JetBrainsMono
                            )
                            Text(
                                text = "Cambiar el Tema de la App",
                                color = RackifyLightGray,
                                fontSize = 12.sp,
                                fontWeight = FontWeight.Bold,
                                fontFamily = JetBrainsMono
                            )
                        }
                    }
                    Switch(
                        checked = isDarkTheme,
                        onCheckedChange = { onThemeToggle(it) },
                        colors = SwitchDefaults.colors(
                            checkedThumbColor = RackifyOrange,
                            checkedTrackColor = RackifyDarkGray,
                        )
                    )
                }
                Spacer(modifier = Modifier.height(500.dp))
            }

        }
    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun ConfigurationPreview() {
    RackifyTheme {
        ConfigurationScreen(
            username = "admin",
            onScan = {},
            onBack = {},
            onDashboard = {},
            onLogout = {},
            onThemeToggle = {},
            isDarkTheme = true
        )
    }
}