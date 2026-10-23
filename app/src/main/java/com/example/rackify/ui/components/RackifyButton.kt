package com.example.rackify.ui.components

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.example.rackify.ui.theme.RackifyBlack
import com.example.rackify.ui.theme.RackifyOrange

@Composable
fun RackifyButton(
    text: String,
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    loading: Boolean = false
){
    var isLoading  = loading
    Button(
        onClick = onClick,
        modifier = modifier,
        colors = ButtonDefaults.buttonColors(
            containerColor = RackifyOrange,
            contentColor = RackifyBlack
        ),
        shape = MaterialTheme.shapes.small,
    ) {
        if (isLoading) {
            CircularProgressIndicator(color = RackifyBlack)
        }else {
            Text(text = text, fontWeight = FontWeight.SemiBold)
        }
    }
}