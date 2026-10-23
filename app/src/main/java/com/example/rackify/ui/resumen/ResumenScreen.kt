package com.example.rackify.ui.resumen

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.rackify.ui.components.RackifyBottomBar
import com.example.rackify.ui.components.RackifyDestination
import com.example.rackify.ui.components.RackifyTopBar
import com.example.rackify.ui.theme.RackifyBlack
import com.example.rackify.ui.theme.RackifyLightGray
import com.example.rackify.ui.theme.RackifyOrange
import com.example.rackify.ui.theme.RackifyWhite

private val summaryFields = listOf(
    "FECHA/HORA",
    "ID DEL DISPOSITIVO",
    "TIPO DE ESTRUCTURA",
    "CAPACIDAD TOTAL",
    "CARGA FISICA",
    "ALIMENTACION ELECTRICA",
    "UBICACION LOGICA"
)

@Composable
fun ResumenScreen(
    username: String,
    onBack: () -> Unit,
    onScanner: () -> Unit,
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
                onBack = onBack,
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
            verticalArrangement = Arrangement.spacedBy(10.dp)
        ) {
            item {
                Text(
                    text = "RESUMEN",
                    color = RackifyOrange,
                    fontSize = 16.sp,
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier.padding(top = 18.dp, bottom = 10.dp)
                )
            }
            items(summaryFields) { field ->
                SummaryField(label = field)
            }
            item {
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(top = 8.dp, bottom = 20.dp),
                    verticalArrangement = Arrangement.spacedBy(8.dp)
                ) {
                    SummaryButton(text = "IMPRIMIR CODIGO", onClick = {})
                    SummaryButton(text = "EDITAR", onClick = {})
                    SummaryButton(text = "GUARDAR", onClick = onDashboard)
                }
            }
        }
    }
}

@Composable
private fun SummaryField(label: String) {
    Column {
        Text(text = label, color = RackifyOrange, fontSize = 12.sp, fontWeight = FontWeight.Bold)
        Text(
            text = "modo prueba",
            color = RackifyWhite,
            fontSize = 14.sp,
            modifier = Modifier.padding(top = 2.dp)
        )
        Text(
            text = "------------------------------",
            color = RackifyLightGray,
            fontSize = 11.sp
        )
    }
}

@Composable
private fun SummaryButton(text: String, onClick: () -> Unit) {
    Button(
        onClick = onClick,
        colors = ButtonDefaults.buttonColors(
            containerColor = RackifyOrange,
            contentColor = RackifyBlack
        ),
        modifier = Modifier.fillMaxWidth()
    ) {
        Text(text = text, fontWeight = FontWeight.Bold)
    }
}
