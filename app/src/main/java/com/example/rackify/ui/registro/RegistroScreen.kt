package com.example.rackify.ui.registro

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
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
        Row(

        ) {
            Image(
                painter = painterResource(id = com.example.rackify.R.drawable.rackify),
                contentDescription = "Logo de Rackify",
                modifier = Modifier
                    .size(20.dp)
                    .padding(bottom = 0.dp),
                contentScale = ContentScale.Fit
            )
            Text(
                text = "Crear Cuenta",
                color = RackifyOrange,
                fontSize = 28.sp,
                fontWeight = FontWeight.Bold
            )
        }
    }
}