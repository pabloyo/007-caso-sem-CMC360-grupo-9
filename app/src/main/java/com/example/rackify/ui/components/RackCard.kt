package com.example.rackify.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.QrCodeScanner
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.rackify.model.Rack
import com.example.rackify.ui.dashboard.DashboardScreen
import com.example.rackify.ui.theme.JetBrainsMono
import com.example.rackify.ui.theme.RackifyDarkGray
import com.example.rackify.ui.theme.RackifyLightGray
import com.example.rackify.ui.theme.RackifyTheme
import com.example.rackify.ui.theme.RackifyWhite

@Composable
fun RackCard(
    rack: Rack,
    onClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    Row(
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically,
        modifier = modifier
            .fillMaxWidth()
            .background(RackifyDarkGray, RoundedCornerShape(8.dp))
            .clickable(onClick = onClick)
            .padding(14.dp)
    ) {
        Column(
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = "Rack",
                color = RackifyLightGray,
                fontSize = 12.sp,
                modifier = Modifier.padding(top = 4.dp)
            )
            Text(
                text = rack.id,
                color = RackifyWhite,
                fontWeight = FontWeight.Bold,
                fontFamily = JetBrainsMono,
                modifier = Modifier.padding(top = 5.dp)
            )
        }
        Column(
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = "Último cambio",
                color = RackifyLightGray,
                fontSize = 12.sp,
                modifier = Modifier.padding(top = 4.dp)
            )
            Text(
                text = rack.date,
                color = RackifyLightGray,
                fontSize = 12.sp,
                fontFamily = JetBrainsMono,
                modifier = Modifier.padding(top = 5.dp)
            )
        }
        Column(
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Icon(
                imageVector = rack.status.icon,
                contentDescription = "Scanner",
                tint = rack.status.color,
                modifier = Modifier
                    .size(30.dp)
            )
            Text(
                text = rack.status.label,
                color = rack.status.color,
                fontSize = 10.sp,
                fontFamily = JetBrainsMono,
                fontWeight = FontWeight.Bold,
                modifier = Modifier.padding(top = 8.dp)
            )
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