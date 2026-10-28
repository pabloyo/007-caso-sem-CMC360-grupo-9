package com.example.rackify.ui.resumen

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
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
import com.example.rackify.ui.components.RackifyBottomBar
import com.example.rackify.ui.components.RackifyButton
import com.example.rackify.ui.components.RackifyDestination
import com.example.rackify.ui.components.RackifyTopBar
import com.example.rackify.ui.components.RackifySummary
import com.example.rackify.ui.theme.JetBrainsMono
import com.example.rackify.ui.theme.RackifyBlack
import com.example.rackify.ui.theme.RackifyOrange
import com.example.rackify.ui.theme.RackifyTheme
import com.example.rackify.viewmodel.ResumenViewModel

@Composable
fun ResumenScreen(
    username: String,
    onBack: () -> Unit,
    onScanner: () -> Unit,
    onDashboard: () -> Unit,
    onLogout: () -> Unit,
    onProfile: () -> Unit,
    onConfiguration: () -> Unit,
    viewModel: ResumenViewModel = viewModel()
) {
    val uiState = viewModel.uiState

    Scaffold(
        containerColor = RackifyBlack,
        topBar = {
            RackifyTopBar(
                username = username,
                onLogout = onLogout,
                onProfile = onProfile,

            )
        },
        bottomBar = {
            RackifyBottomBar(
                current = RackifyDestination.ESCANER,
                onConfiguration = onConfiguration,
                onScanner = onScanner,
                onDashboard = onDashboard
            )
        }
    ) { paddingValues ->
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
                .padding(horizontal = 24.dp),
            verticalArrangement = Arrangement.spacedBy(10.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            item {
                Text(
                    text = "RESUMEN",
                    color = RackifyOrange,
                    fontFamily = JetBrainsMono,
                    fontSize = 30.sp,
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier.padding(top = 18.dp, bottom = 8.dp)
                )
            }
            items(uiState.summaryItems) { item ->
                RackifySummary(
                    label = item.label,
                    value = item.value
                )
            }
            item {
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(top = 8.dp, bottom = 20.dp),
                    verticalArrangement = Arrangement.spacedBy(8.dp)
                ) {
                    RackifyButton(text = "IMPRIMIR CODIGO", onClick = {}, modifier = Modifier.fillMaxWidth())
                    RackifyButton(text = "EDITAR", onClick = {}, modifier = Modifier.fillMaxWidth())
                    RackifyButton(text = "GUARDAR", onClick = onDashboard, modifier = Modifier.fillMaxWidth())
                }
            }
        }
    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun ResumenScreenPreview() {
    RackifyTheme {
        ResumenScreen(
            username = "admin",
            onBack = {},
            onScanner = {},
            onDashboard = {},
            onLogout = {},
            onProfile = {},
            onConfiguration = {}
        )
    }
}
