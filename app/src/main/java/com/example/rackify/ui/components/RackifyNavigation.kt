package com.example.rackify.ui.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.rackify.ui.theme.RackifyBlack
import com.example.rackify.ui.theme.RackifyDarkGray
import com.example.rackify.ui.theme.RackifyLightGray
import com.example.rackify.ui.theme.RackifyOrange

enum class RackifyDestination {
    ESCANER,
    DASHBOARD
}

@Composable
fun RackifyTopBar(
    username: String,
    onLogout: () -> Unit,
    onProfile: () -> Unit
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(start = 20.dp, end = 8.dp, top = 10.dp),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(
            text = "RACKIFY",
            color = RackifyOrange,
            fontWeight = FontWeight.Bold,
            letterSpacing = 3.sp
        )
        Row(verticalAlignment = Alignment.CenterVertically) {
            Text(text = username, color = RackifyLightGray, fontSize = 11.sp)
            TextButton(onClick = onLogout) {
                Text(text = "SALIR", color = RackifyOrange, fontSize = 11.sp)
            }
            TextButton(onClick = onProfile) {
                Text(text = "PERFIL", color = RackifyOrange, fontSize = 11.sp)
            }
        }
    }
}

@Composable
fun RackifyBottomBar(
    current: RackifyDestination,
    onBack: () -> Unit,
    onScanner: () -> Unit,
    onDashboard: () -> Unit
) {
    NavigationBar(
        containerColor = RackifyDarkGray,
        contentColor = RackifyOrange
    ) {
        NavigationBarItem(
            selected = false,
            onClick = onBack,
            icon = { Text(text = "<", fontSize = 24.sp) },
            label = { Text(text = "ATRAS") },
            colors = rackifyNavigationColors()
        )
        NavigationBarItem(
            selected = current == RackifyDestination.ESCANER,
            onClick = onScanner,
            icon = { Text(text = "SCAN", fontSize = 11.sp) },
            label = { Text(text = "ESCANER") },
            colors = rackifyNavigationColors()
        )
        NavigationBarItem(
            selected = current == RackifyDestination.DASHBOARD,
            onClick = onDashboard,
            icon = { Text(text = "[]", fontSize = 18.sp) },
            label = { Text(text = "DASHBOARD") },
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
