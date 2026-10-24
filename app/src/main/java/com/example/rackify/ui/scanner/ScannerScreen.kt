package com.example.rackify.ui.scanner

import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.rackify.ui.components.RackifyBottomBar
import com.example.rackify.ui.components.RackifyButton
import com.example.rackify.ui.components.RackifyDestination
import com.example.rackify.ui.components.RackifyTopBar
import com.example.rackify.ui.theme.JetBrainsMono
import com.example.rackify.ui.theme.RackifyTheme
import com.example.rackify.ui.theme.RackifyBlack
import com.example.rackify.ui.theme.RackifyLightGray
import com.example.rackify.ui.theme.RackifyOrange
import com.example.rackify.ui.theme.RackifyWhite

@Composable
fun ScannerScreen(
    username: String,
    onScan: () -> Unit,
    onBack: () -> Unit,
    onDashboard: () -> Unit,
    onLogout: () -> Unit
) {
    Scaffold(
        containerColor = RackifyBlack,
        topBar = {
            RackifyTopBar(username = username, onLogout = onLogout, onProfile = {})
        },
        bottomBar = {
            RackifyBottomBar(
                current = RackifyDestination.ESCANER,
                onConfiguration = { },
                onScanner = {},
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
                text = "ESCANER",
                color = RackifyOrange,
                fontSize = 30.sp,
                fontFamily = JetBrainsMono,
                fontWeight = FontWeight.Bold,
                modifier = Modifier.padding(top = 18.dp, bottom = 8.dp)

            )
            Box(
                modifier = Modifier
                    .size(width = 270.dp, height = 270.dp)
                    .border(width = 2.dp, color = RackifyOrange),
                contentAlignment = Alignment.Center
            ) {
                Text(text = "Cámara", color = RackifyLightGray)
            }
            RackifyButton(
                onClick = onScan,
                text = "ESCANEAR",
                modifier = Modifier
                    .height(52.dp)
                    .padding(top = 12.dp)
            )
            Spacer(modifier = Modifier.height(10.dp))
        }
    }
}
@Preview(showBackground = true, showSystemUi = true)
@Composable
fun ScannerScreenPreview() {
    RackifyTheme {
        ScannerScreen(
            username = "admin",
            onScan = {},
            onBack = {},
            onDashboard = {},
            onLogout = {}
        )
    }
}