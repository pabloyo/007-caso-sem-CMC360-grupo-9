package com.example.rackify.ui.components

import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Article
import androidx.compose.material.icons.filled.CameraAlt
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material.icons.outlined.Article
import androidx.compose.material.icons.outlined.CameraAlt
import androidx.compose.material.icons.outlined.Settings
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.example.rackify.ui.theme.JetBrainsMono
import com.example.rackify.ui.theme.RackifyDarkGray
import com.example.rackify.ui.theme.RackifyLightGray
import com.example.rackify.ui.theme.RackifyOrange

enum class RackifyDestination {
    ESCANER,
    DASHBOARD,
    CONFIGURACION
}

@Composable
fun RackifyBottomBar(
    current: RackifyDestination?,
    onConfiguration: () -> Unit,
    onScanner: () -> Unit,
    onDashboard: () -> Unit
) {
    NavigationBar(
        containerColor = RackifyDarkGray,
        contentColor = RackifyOrange,
        modifier = Modifier
            .clip(RoundedCornerShape(topStart = 16.dp, topEnd = 16.dp))
    ) {
        val isConfigSelected = current == RackifyDestination.CONFIGURACION
        NavigationBarItem(
            selected = isConfigSelected,
            onClick = onConfiguration,
            icon = {
                Icon(
                    imageVector = if (isConfigSelected) Icons.Filled.Settings else Icons.Outlined.Settings,
                    contentDescription = "Configuracion",
                    modifier = Modifier.size(30.dp)
                )
            },
            label = { Text(text = "CONFIG", fontFamily = JetBrainsMono) },
            colors = rackifyNavigationColors()
        )
        val isScannerSelected = current == RackifyDestination.ESCANER
        NavigationBarItem(
            selected = isScannerSelected,
            onClick = onScanner,
            icon = {
                Icon(
                    imageVector = if (isScannerSelected) Icons.Filled.CameraAlt else Icons.Outlined.CameraAlt,
                    contentDescription = "Scanner",
                    modifier = Modifier.size(30.dp)
                )
            },
            label = { Text(text = "ESCÁNER", fontFamily = JetBrainsMono) },
            colors = rackifyNavigationColors()
        )
        val isDashboardSelected = current == RackifyDestination.DASHBOARD
        NavigationBarItem(
            selected = isDashboardSelected,
            onClick = onDashboard,
            icon = {
                Icon(
                    imageVector = if (isDashboardSelected) Icons.Filled.Article else Icons.Outlined.Article,
                    contentDescription = "Dashboard",
                    modifier = Modifier.size(30.dp)
                )
            },
            label = { Text(text = "PANEL", fontFamily = JetBrainsMono) },
            colors = rackifyNavigationColors()
        )
    }
}

@Composable
private fun rackifyNavigationColors() = NavigationBarItemDefaults.colors(
    selectedIconColor = RackifyOrange,
    selectedTextColor = RackifyOrange,
    indicatorColor = Color.Transparent,
    unselectedIconColor = RackifyLightGray,
    unselectedTextColor = RackifyLightGray
)
