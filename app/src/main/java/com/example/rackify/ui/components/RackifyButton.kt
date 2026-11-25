package com.example.rackify.ui.components

import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import com.example.rackify.ui.theme.RackifyBlack
import com.example.rackify.ui.theme.RackifyOrange

@Composable
fun RackifyButton(
    text: String,
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    loading: Boolean = false,
    containerColor: Color = RackifyOrange,
    contentColor: Color = RackifyBlack
) {
    Button(
        onClick = onClick,
        modifier = modifier,
        colors = ButtonDefaults.buttonColors(
            containerColor = containerColor,
            contentColor = contentColor
        ),
        shape = MaterialTheme.shapes.small,
    ) {
        if (loading) {
            CircularProgressIndicator(color = contentColor)
        } else {
            Text(text = text, fontWeight = FontWeight.SemiBold)
        }
    }
}
