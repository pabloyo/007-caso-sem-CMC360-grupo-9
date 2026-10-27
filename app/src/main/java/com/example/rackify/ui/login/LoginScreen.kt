package com.example.rackify.ui.login

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
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel

import com.example.rackify.ui.components.RackifyButton
import com.example.rackify.ui.components.RackifyTextField
import com.example.rackify.ui.theme.RackifyTheme
import com.example.rackify.viewmodel.LoginViewModel

@Composable
fun LoginScreen(
    onLoginSuccess: (String) -> Unit,
    onNavigateToRegistro: () -> Unit,
    viewModel: LoginViewModel = viewModel()
) {
    val uiState = viewModel.uiState

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.background)
            .padding(horizontal = 32.dp),
        contentAlignment = Alignment.Center
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .widthIn(max = 340.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.spacedBy(14.dp)
        ) {
            Image(
                painter = painterResource(id = com.example.rackify.R.drawable.rackify),
                contentDescription = "Logo de Rackify",
                modifier = Modifier
                    .size(200.dp)
                    .padding(bottom = 0.dp),
                contentScale = ContentScale.Fit
            )

            RackifyTextField(
                label = "Usuario",
                value = uiState.usuario,
                onValueChange = { viewModel.onUsuarioChange(it) },
                placeholder = "Ingrese su usuario",
                error = uiState.usuarioError
            )

            RackifyTextField(
                label = "Contraseña",
                value = uiState.contrasena,
                onValueChange = { viewModel.onContrasenaChange(it) },
                placeholder = "Ingrese su contraseña",
                isPassword = true,
                error = uiState.contrasenaError
            )

            RackifyButton(
                text = "ENTRAR",
                onClick = {
                    viewModel.login(onSuccess = onLoginSuccess)
                },
                loading = uiState.isLoading,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 10.dp)
            )

            if (uiState.generalError != null) {
                Text(
                    text = uiState.generalError,
                    color = MaterialTheme.colorScheme.error,
                    fontSize = 12.sp,
                    modifier = Modifier.padding(top = 4.dp)
                )
            }

            Row(
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text("¿No tienes una cuenta?", color = MaterialTheme.colorScheme.onSurfaceVariant)
                TextButton(
                    onClick = { onNavigateToRegistro() },
                ) {
                    Text("Regístrate", color = MaterialTheme.colorScheme.primary)
                }
            }
        }
    }
}

@Preview(showBackground = true, widthDp = 360, heightDp = 720)
@Composable
private fun LoginScreenPreview() {
    RackifyTheme {
        LoginScreen(onLoginSuccess = {},
            onNavigateToRegistro = {})
    }
}
