package com.example.rackify.ui.loading

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.rackify.ui.components.RackifyTopBar
import com.example.rackify.ui.theme.JetBrainsMono
import com.example.rackify.ui.theme.RackifyBlack
import com.example.rackify.ui.theme.RackifyLightGray
import com.example.rackify.ui.theme.RackifyTheme
import kotlinx.coroutines.delay
import androidx.compose.material3.Text
import androidx.compose.ui.unit.sp


@Composable
fun LoadingScreen(
    username: String,
    textos: List<String>,
    onScan: () -> Unit,
    onBack: () -> Unit,
    onDashboard: () -> Unit,
    onLogout: () -> Unit,
    onConfiguration: () -> Unit,
    onProfile: () -> Unit,

){
    var comentarios by remember { mutableIntStateOf(0) }
    LaunchedEffect(Unit) {
        for (i in textos.indices) {
            comentarios = i
            delay(1500)
        }
        onScan()
    }

    Scaffold(
        containerColor = RackifyBlack,
        topBar = {
            RackifyTopBar(
                username = username,
                onLogout = onLogout,
                onProfile = {}
            )
        },

    ){ paddingValues ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
                .padding(horizontal = 28.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ){
            Image(
                painter = painterResource(id = com.example.rackify.R.drawable.icon),
                contentDescription = "Logo de Rackify",
                modifier = Modifier
                    .size(200.dp)
                    .padding(bottom = 0.dp),
                contentScale = ContentScale.Fit
            )
            Text(
                text = textos[comentarios],
                color = RackifyLightGray,
                fontSize = 16.sp,
                fontFamily = JetBrainsMono,
                fontWeight = FontWeight.Bold,
                modifier = Modifier
                    .padding(top = 18.dp, bottom = 8.dp)
            )

        }
    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun LoadingScreenPreview() {
    RackifyTheme {
        LoadingScreen(
            username = "admin",
            textos = listOf(),
            onScan = {},
            onBack = {},
            onDashboard = {},
            onLogout = {},
            onConfiguration = {},
            onProfile = {}
        )
    }
}