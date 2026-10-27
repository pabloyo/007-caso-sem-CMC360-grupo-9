package com.example.rackify.ui.profile

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.Domain
import androidx.compose.material.icons.filled.Face
import androidx.compose.material.icons.filled.Group
import androidx.compose.material3.Icon
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
import com.example.rackify.ui.theme.JetBrainsMono
import com.example.rackify.ui.theme.RackifyBlack
import com.example.rackify.ui.theme.RackifyDarkGray
import com.example.rackify.ui.theme.RackifyLightGray
import com.example.rackify.ui.theme.RackifyOrange
import com.example.rackify.ui.theme.RackifyTheme
import com.example.rackify.ui.theme.Ubuntu
import com.example.rackify.viewmodel.ProfileViewModel

@Composable
fun ProfileScreen(
    username: String,
    onScan: () -> Unit,
    onBack: () -> Unit,
    onDashboard: () -> Unit,
    onLogout: () -> Unit,
    onConfiguration: () -> Unit,
    viewModel: ProfileViewModel = viewModel()
){
    val uiState = viewModel.uiState

    Scaffold(
        containerColor = RackifyBlack,
        topBar = {
            RackifyTopBar(
                username = uiState.user?.username ?: username,
                onLogout = onLogout,
                onProfile = {}
            )
        },
        bottomBar = {
            RackifyBottomBar(
                current = null,
                onConfiguration = onConfiguration,
                onScanner = onScan,
                onDashboard = onDashboard
            )
        }
    ){ paddingValues ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
                .padding(horizontal = 28.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.SpaceBetween
        ) {
            Text(
                text = "PERFIL",
                color = RackifyOrange,
                fontSize = 30.sp,
                fontFamily = JetBrainsMono,
                fontWeight = FontWeight.Bold,
                modifier = Modifier.padding(top = 18.dp)
            )
            Box(
                modifier = Modifier
                    .size(width = 150.dp, height = 150.dp)
                    .background(color = RackifyDarkGray, shape = RoundedCornerShape(16.dp))
                    .border(width = 2.dp, color = RackifyOrange, shape = RoundedCornerShape(16.dp)),
                contentAlignment = Alignment.Center
            ) {
                Text(
                    text = uiState.user?.name?.take(1)?.uppercase() ?: username.take(1).uppercase(),
                    color = RackifyLightGray,
                    fontWeight = FontWeight.Black,
                    fontSize = 100.sp
                )
            }

            Column(
                modifier = Modifier.fillMaxWidth()
            ){
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                ) {
                    Icon(
                        imageVector = Icons.Default.AccountCircle,
                        contentDescription = "Profile",
                        tint = RackifyOrange,
                        modifier = Modifier.size(30.dp)
                    )
                    Column(
                        modifier = Modifier.padding(start = 16.dp)
                    ) {
                        Text(
                            text = "USUARIO",
                            color = RackifyOrange,
                            fontSize = 20.sp,
                            fontWeight = FontWeight.Bold,
                            fontFamily = JetBrainsMono
                        )
                        Text(
                            text = uiState.user?.username ?: username,
                            color = RackifyLightGray,
                            fontSize = 16.sp,

                            fontFamily = Ubuntu
                        )
                    }
                }
                Spacer(modifier = Modifier.height(30.dp))
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                ) {
                    Icon(
                        imageVector = Icons.Default.Face,
                        contentDescription = "Name",
                        tint = RackifyOrange,
                        modifier = Modifier.size(30.dp)
                    )
                    Column(
                        modifier = Modifier.padding(start = 16.dp)
                    ) {
                        Text(
                            text = "NOMBRE",
                            color = RackifyOrange,
                            fontSize = 20.sp,
                            fontWeight = FontWeight.Bold,
                            fontFamily = JetBrainsMono
                        )
                        Text(
                            text = uiState.user?.name?.ifBlank { "Sin nombre" } ?: "Sin nombre",
                            color = RackifyLightGray,
                            fontSize = 16.sp,

                            fontFamily = Ubuntu
                        )
                    }
                }
                Spacer(modifier = Modifier.height(30.dp))
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                ) {
                    Icon(
                        imageVector = Icons.Default.Group,
                        contentDescription = "Profile",
                        tint = RackifyOrange,
                        modifier = Modifier.size(30.dp)
                    )
                    Column(
                        modifier = Modifier.padding(start = 16.dp)
                    ) {
                        Text(
                            text = "ROL",
                            color = RackifyOrange,
                            fontSize = 20.sp,
                            fontWeight = FontWeight.Bold,
                            fontFamily = JetBrainsMono
                        )
                        Text(
                            text = uiState.user?.role?.ifBlank { "Sin rol" } ?: "Sin rol",
                            color = RackifyLightGray,
                            fontSize = 16.sp,

                            fontFamily = Ubuntu
                        )
                    }
                }
                Spacer(modifier = Modifier.height(30.dp))
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                ) {
                    Icon(
                        imageVector = Icons.Default.Domain,
                        contentDescription = "Profile",
                        tint = RackifyOrange,
                        modifier = Modifier.size(30.dp)
                    )
                    Column(
                        modifier = Modifier.padding(start = 16.dp)
                    ) {
                        Text(
                            text = "ORGANIZACIÓN",
                            color = RackifyOrange,
                            fontSize = 20.sp,
                            fontWeight = FontWeight.Bold,
                            fontFamily = JetBrainsMono
                        )
                        Text(
                            text = "CMC360 SPA",
                            color = RackifyLightGray,
                            fontSize = 16.sp,

                            fontFamily = Ubuntu
                        )
                    }
                }
            }
            RackifyButton(
                onClick = onLogout,
                text = "CERRAR SESION",
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
fun ProfileScreenPreview() {
    RackifyTheme {
        ProfileScreen(
            username = "admin",
            onScan = {},
            onBack = {},
            onDashboard = {},
            onLogout = {},
            onConfiguration = {}
        )
    }
}
