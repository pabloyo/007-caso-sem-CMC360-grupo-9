package com.example.rachify.ui.login

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
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

import com.example.rachify.domain.LoginValidator
import com.example.rachify.ui.theme.RachifyTheme

import com.example.rachify.ui.theme.RackifyBlack
import com.example.rachify.ui.theme.RackifyDarkGray
import com.example.rachify.ui.theme.RackifyGreen
import com.example.rachify.ui.theme.RackifyOrange
import com.example.rachify.ui.theme.RackifyRed
import com.example.rachify.ui.theme.RackifyWhite
import com.example.rachify.ui.theme.RackifyLightGray

@Composable
fun LoginScreen(onLoginSuccess: (String) -> Unit) {
    var username by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    var attemptedLogin by remember { mutableStateOf(false) }

    val usernameError = if (attemptedLogin) LoginValidator.usernameError(username) else null
    val passwordError = if (attemptedLogin) LoginValidator.passwordError(password) else null

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(RackifyBlack)
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
                painter = painterResource(id = com.example.rachify.R.drawable.rackify),
                contentDescription = "Logo de Rackify",
                modifier = Modifier
                    .size(200.dp)
                    .padding(bottom = 0.dp),
                contentScale = ContentScale.Fit
            )

            LoginField(
                label = "Usuario",
                value = username,
                onValueChange = {
                    username = it
                    attemptedLogin = false
                },
                error = usernameError
            )

            LoginField(
                label = "Contraseña",
                value = password,
                onValueChange = {
                    password = it
                    attemptedLogin = false
                },
                error = passwordError,
                isPassword = true
            )

            Button(
                onClick = {
                    attemptedLogin = true
                    if (LoginValidator.isValid(username, password)) {
                        onLoginSuccess(username.trim())
                    }
                },
                colors = ButtonDefaults.buttonColors(
                    containerColor = RackifyOrange,
                    contentColor = RackifyBlack
                ),
                shape = MaterialTheme.shapes.small,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 10.dp)
            ) {
                Text("ENTRAR", fontWeight = FontWeight.SemiBold)
            }

            Row(
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text("¿No tienes una cuenta?", color = RackifyLightGray)
                TextButton(
                    onClick = { /*pagina de registro*/ },
                ) {
                    Text("Regístrate", color = RackifyOrange)
                }

            }
        }
    }
}

@Composable
private fun LoginField(
    label: String,
    value: String,
    onValueChange: (String) -> Unit,
    error: String?,
    isPassword: Boolean = false
) {
    Column(modifier = Modifier.fillMaxWidth()) {
        Text(
            text = label,
            color = RackifyLightGray,
            fontSize = 12.sp,
            modifier = Modifier.padding(start = 4.dp, bottom = 4.dp)
        )
        OutlinedTextField(
            value = value,
            onValueChange = onValueChange,
            singleLine = true,
            isError = error != null,
            visualTransformation = if (isPassword) PasswordVisualTransformation() else androidx.compose.ui.text.input.VisualTransformation.None,
            colors = OutlinedTextFieldDefaults.colors(
                focusedContainerColor = RackifyDarkGray,
                unfocusedContainerColor = RackifyDarkGray,
                errorContainerColor = RackifyDarkGray,
                focusedBorderColor = RackifyOrange,
                unfocusedBorderColor = Color.Transparent,
                errorBorderColor = RackifyRed,
                focusedTextColor = RackifyWhite,
                unfocusedTextColor = RackifyWhite,
                cursorColor = RackifyOrange
            ),
            modifier = Modifier.fillMaxWidth()
        )
        if (error != null) {
            Text(
                text = error,
                color = RackifyRed,
                style = MaterialTheme.typography.labelSmall,
                modifier = Modifier.padding(start = 4.dp, top = 4.dp)
            )
        }
    }
}

@Preview(showBackground = true, widthDp = 360, heightDp = 720)
@Composable
private fun LoginScreenPreview() {
    RachifyTheme {
        LoginScreen(onLoginSuccess = {})
    }
}