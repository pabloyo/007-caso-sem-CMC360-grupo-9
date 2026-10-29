package com.example.rackify.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.rackify.ui.resumen.ResumenScreen
import com.example.rackify.ui.theme.JetBrainsMono
import com.example.rackify.ui.theme.RackifyDarkGray
import com.example.rackify.ui.theme.RackifyOrange
import com.example.rackify.ui.theme.RackifyTheme
import com.example.rackify.ui.theme.RackifyWhite

@Composable
fun RackifySummary(
    label: String,
    value: String,
    modifier: Modifier = Modifier,
    isEditable: Boolean = true,
    decision: ItemDecision = ItemDecision.UNSET,
    onConfirm: () -> Unit = {},
    onEdit: () -> Unit = {},
    onReject: () -> Unit = {}
) {
    Row(
        modifier = modifier
            .fillMaxWidth()
            .background(RackifyDarkGray, RoundedCornerShape(8.dp))
            .padding(14.dp),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Column(
            modifier = Modifier.weight(1f)
        ) {
            Text(
                text = label,
                color = RackifyOrange,
                fontSize = 12.sp,
                fontWeight = FontWeight.Bold,
                fontFamily = JetBrainsMono
            )
            Text(
                text = value,
                fontFamily = JetBrainsMono,
                color = RackifyWhite,
                fontSize = 14.sp,
                modifier = Modifier.padding(top = 2.dp)
            )
        }
        if (isEditable) {
            RackifyValidators(
                decision = decision,
                onEdit = onEdit,
                onConfirm = onConfirm,
                onReject = onReject
            )
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
