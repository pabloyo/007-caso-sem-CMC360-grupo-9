package com.example.rackify.ui.components

import android.R.attr.scaleX
import androidx.compose.foundation.gestures.snapping.SnapPosition
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ExitToApp
import androidx.compose.material.icons.automirrored.filled.Logout
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.rackify.ui.theme.JetBrainsMono
import com.example.rackify.ui.theme.RackifyOrange


@Composable
fun RackifyTopBar(
    username: String,
    onLogout: () -> Unit,
    onProfile: () -> Unit
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(start = 20.dp, end = 8.dp, top = 10.dp),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(
            text = "RACKIFY",
            color = RackifyOrange,
            fontFamily = JetBrainsMono,
            fontWeight = FontWeight.Bold,
            letterSpacing = 3.sp
        )
        Row(verticalAlignment = Alignment.CenterVertically) {
            TextButton(onClick = onLogout) {
                Icon(
                    imageVector = Icons.AutoMirrored.Default.Logout,
                    contentDescription = "LogOut",
                    modifier = Modifier
                        .size(20.dp)
                )
            }
            TextButton(onClick = onProfile) {
                Row(
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Icon(
                        imageVector = Icons.Default.AccountCircle,
                        tint = RackifyOrange,
                        contentDescription = "Perfil"
                    )
                    Spacer(modifier = Modifier.width(4.dp))
                    Text(text = username, color = RackifyOrange, fontSize = 11.sp)
                }
            }
        }
    }
}
