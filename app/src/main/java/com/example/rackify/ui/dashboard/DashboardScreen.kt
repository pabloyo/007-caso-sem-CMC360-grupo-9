package com.example.rackify.ui.dashboard

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.rackify.ui.components.RackifyBottomBar
import com.example.rackify.ui.components.RackifyDestination
import com.example.rackify.ui.components.RackifyTopBar
import com.example.rackify.ui.theme.RackifyBlack
import com.example.rackify.ui.theme.RackifyDarkGray
import com.example.rackify.ui.theme.RackifyGreen
import com.example.rackify.ui.theme.RackifyLightGray
import com.example.rackify.ui.theme.RackifyOrange
import com.example.rackify.ui.theme.RackifyRed
import com.example.rackify.ui.theme.RackifyWhite

private data class DemoRack(val code: String, val date: String, val status: String, val color: Color)

private val demoRacks = listOf(
    DemoRack("RACK-001", "modo prueba", "PENDIENTE", RackifyOrange),
    DemoRack("RACK-002", "modo prueba", "VERIFICADO", RackifyGreen)
)

@Composable
fun DashboardScreen(
    username: String,
    onBack: () -> Unit,
    onScanner: () -> Unit,
    onRackSelected: () -> Unit,
    onLogout: () -> Unit
) {
    Scaffold(
        containerColor = RackifyBlack,
        topBar = {
            RackifyTopBar(username = username, onLogout = onLogout, onProfile = {})
        },
        bottomBar = {
            RackifyBottomBar(
                current = RackifyDestination.DASHBOARD,
                onBack = onBack,
                onScanner = onScanner,
                onDashboard = {}
            )
        }
    ) { paddingValues ->
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
                .padding(horizontal = 20.dp),
            verticalArrangement = Arrangement.spacedBy(10.dp)
        ) {
            item {
                Text(
                    text = "DASHBOARD",
                    color = RackifyOrange,
                    fontSize = 16.sp,
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier.padding(top = 18.dp, bottom = 8.dp)
                )
            }
            items(demoRacks) { rack ->
                RackCard(rack = rack, onClick = onRackSelected)
            }
        }
    }
}

@Composable
private fun RackCard(rack: DemoRack, onClick: () -> Unit) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .background(RackifyDarkGray, RoundedCornerShape(8.dp))
            .clickable(onClick = onClick)
            .padding(14.dp)
    ) {
        Text(text = rack.code, color = RackifyWhite, fontWeight = FontWeight.Bold)
        Text(
            text = rack.date,
            color = RackifyLightGray,
            fontSize = 12.sp,
            modifier = Modifier.padding(top = 4.dp)
        )
        Text(
            text = rack.status,
            color = rack.color,
            fontSize = 12.sp,
            fontWeight = FontWeight.Bold,
            modifier = Modifier.padding(top = 8.dp)
        )
    }
}
