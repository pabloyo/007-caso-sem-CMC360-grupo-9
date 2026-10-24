package com.example.rackify.model

import android.graphics.drawable.Icon
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.DoNotDisturbOn
import androidx.compose.material.icons.filled.Pending
import androidx.compose.material.icons.filled.Verified
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.tooling.preview.Preview
import com.example.rackify.ui.dashboard.DashboardScreen
import com.example.rackify.ui.theme.RackifyGreen
import com.example.rackify.ui.theme.RackifyOrange
import com.example.rackify.ui.theme.RackifyRed
import com.example.rackify.ui.theme.RackifyTheme

enum class RackStatus(val label: String, val color: Color, val icon: ImageVector) {
    PENDIENTE("PENDIENTE", RackifyOrange, Icons.Default.Pending),
    VERIFICADO("VERIFICADO", RackifyGreen, Icons.Default.Verified),
    RECHAZADO("RECHAZADO", RackifyRed, Icons.Default.DoNotDisturbOn),
}

data class Rack(
    val code: String,
    val date: String,
    val status: RackStatus,
)
