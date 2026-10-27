package com.example.rackify.ui.dashboard

import android.R.attr.bottom
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.rackify.ui.components.RackCard
import com.example.rackify.ui.components.RackifyBottomBar
import com.example.rackify.ui.components.RackifyDestination
import com.example.rackify.ui.components.RackifyTopBar
import com.example.rackify.ui.scanner.ScannerScreen
import com.example.rackify.ui.theme.JetBrainsMono
import com.example.rackify.ui.theme.RackifyBlack
import com.example.rackify.ui.theme.RackifyOrange
import com.example.rackify.ui.theme.RackifyTheme
import com.example.rackify.viewmodel.DashboardViewModel
import com.example.rackify.viewmodel.ProfileViewModel

@Composable
fun DashboardScreen(
    username: String,
    onBack: () -> Unit,
    onScanner: () -> Unit,
    onRackSelected: () -> Unit,
    onLogout: () -> Unit,
    onProfile: () -> Unit,
    viewModel: DashboardViewModel = viewModel(),
    onConfiguration: () -> Unit
) {
    val uiState = viewModel.uiState
    Scaffold(
        containerColor = RackifyBlack,
        topBar = {
            RackifyTopBar(
                username = username,
                onLogout = onLogout,
                onProfile = onProfile
            )
        },
        bottomBar = {
            RackifyBottomBar(
                current = RackifyDestination.DASHBOARD,
                onConfiguration = onConfiguration,
                onScanner = onScanner,
                onDashboard = {}
            )
        }
    ) { paddingValues ->
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
                .padding(horizontal = 20.dp)
                .padding(bottom = 10.dp),
            verticalArrangement = Arrangement.spacedBy(10.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            item {
                Text(
                    text = "PANEL",
                    color = RackifyOrange,
                    fontFamily = JetBrainsMono,
                    fontSize = 30.sp,
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier.padding(top = 18.dp, bottom = 8.dp)
                )
            }
            items(uiState.racks) { rack ->
                RackCard(
                    rack = rack,
                    onClick = onRackSelected
                )
            }
        }
    }
}
@Preview(showBackground = true, showSystemUi = true)
@Composable
fun DashboardScreenPreview() {
    RackifyTheme {
        DashboardScreen(
            onRackSelected = {},
            username = "admin",
            onBack = {},
            onScanner = {},
            onLogout = {},
            onProfile = {},
            onConfiguration = {}
        )
    }
}