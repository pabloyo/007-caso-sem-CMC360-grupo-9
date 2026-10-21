package com.example.rackify.ui.registro

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.widthIn
import androidx.compose.material3.OutlinedTextField
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.rackify.ui.theme.RackifyBlack
import androidx.compose.material3.Text
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.text.style.LineHeightStyle
import androidx.compose.ui.tooling.preview.Preview
import com.example.rackify.ui.login.LoginScreen
import com.example.rackify.ui.theme.JetBrainsMono
import com.example.rackify.ui.theme.RackifyTheme


private val RackifyBlack = Color(0xFF1E1E1E)
private val RackifyDarkGray = Color(0xFF2A2A2A)
private val RackifyOrange = Color(0xFFFF6800)
private val RackifyLightGray = Color(0xFFF5F5F5)
private val RackifyMidGray = Color(0xFF8A8A8A)
private val RackifyRed = Color(0xFFFF453A)
private val RackifyGreen = Color(0xFF32D74B)

@Composable
fun RegistroScreen(

) {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(RackifyBlack)
            .padding(horizontal = 32.dp),
        contentAlignment = Alignment.Center
    ){
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .widthIn(max = 340.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.spacedBy(14.dp)
        ){
            Row(
                horizontalArrangement = Arrangement.Center
            ) {
                Image(
                    painter = painterResource(id = com.example.rackify.R.drawable.icon),
                    contentDescription = "Logo de Rackify",
                    modifier = Modifier
                        .size(50.dp)
                        .padding(bottom = 15.dp),
                    contentScale = ContentScale.Fit
                )
                Text(
                    text = "CREAR CUENTA",
                    color = RackifyOrange,
                    fontFamily = JetBrainsMono,
                    fontSize = 28.sp,
                    fontWeight = FontWeight.Bold
                )
            }
            var nombre by remember { mutableStateOf("") }
            OutlinedTextField(
                value = "",
                onValueChange = { nombre = it},
                label = { Text("Nombre") }
            )
        }

    }
}
@Preview(showBackground = true, widthDp = 360, heightDp = 720)
@Composable
private fun RegistroScreenPreview() {
    RackifyTheme {
        RegistroScreen()
    }
}