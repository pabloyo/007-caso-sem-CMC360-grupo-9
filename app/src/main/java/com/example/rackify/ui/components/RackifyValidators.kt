package com.example.rackify.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Check
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.rackify.ui.theme.RackifyBlack
import com.example.rackify.ui.theme.RackifyGreen
import com.example.rackify.ui.theme.RackifyOrange
import com.example.rackify.ui.theme.RackifyRed

enum class ItemDecision {
    UNSET,
    CONFIRMED,
    REJECTED
}

@Composable
fun RackifyValidators(
    decision: ItemDecision = ItemDecision.UNSET,
    onEdit: () -> Unit = {},
    onConfirm: () -> Unit = {},
    onReject: () -> Unit = {}
) {
    Row(
        horizontalArrangement = Arrangement.spacedBy(6.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Box(
            modifier = Modifier
                .size(30.dp)
                .background(RackifyBlack, RoundedCornerShape(6.dp))
                .clickable(onClick = onEdit),
            contentAlignment = Alignment.Center
        ) {
            Icon(
                imageVector = Icons.Default.Edit,
                contentDescription = "Editar",
                tint = RackifyOrange,
                modifier = Modifier.size(16.dp)
            )
        }

        val isConfirmed = decision == ItemDecision.CONFIRMED
        Box(
            modifier = Modifier
                .size(30.dp)
                .background(if (isConfirmed) RackifyGreen else RackifyBlack, RoundedCornerShape(6.dp))
                .clickable(onClick = onConfirm),
            contentAlignment = Alignment.Center
        ) {
            Icon(
                imageVector = Icons.Default.Check,
                contentDescription = "Confirmar",
                tint = if (isConfirmed) RackifyBlack else RackifyGreen,
                modifier = Modifier.size(16.dp)
            )
        }

        val isRejected = decision == ItemDecision.REJECTED
        Box(
            modifier = Modifier
                .size(30.dp)
                .background(if (isRejected) RackifyRed else RackifyBlack, RoundedCornerShape(6.dp))
                .clickable(onClick = onReject),
            contentAlignment = Alignment.Center
        ) {
            Icon(
                imageVector = Icons.Default.Close,
                contentDescription = "Rechazar",
                tint = if (isRejected) RackifyBlack else RackifyRed,
                modifier = Modifier.size(16.dp)
            )
        }
    }
}
