package com.example.rachify.ui.login

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.widthIn
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.rachify.domain.LoginValidator
import com.example.rachify.ui.theme.RachifyTheme

private val ScreenGray = Color(0xFF777777)
private val RackifyYellow = Color(0xFFF6B21A)
private val FieldBackground = Color(0xFFF4F8FA)
private val ErrorRed = Color(0xFFFFD4D1)

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
            .background(ScreenGray)
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
            Text(
                text = "RACKIFY",
                color = RackifyYellow,
                fontSize = 25.sp,
                fontWeight = FontWeight.Bold,
                letterSpacing = 6.sp,
                modifier = Modifier.padding(bottom = 54.dp)
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
                    containerColor = RackifyYellow,
                    contentColor = Color(0xFF262626)
                ),
                shape = MaterialTheme.shapes.small,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 10.dp)
            ) {
                Text("ENTRAR", fontWeight = FontWeight.SemiBold)
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
            color = RackifyYellow,
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
                focusedContainerColor = FieldBackground,
                unfocusedContainerColor = FieldBackground,
                errorContainerColor = FieldBackground,
                focusedBorderColor = RackifyYellow,
                unfocusedBorderColor = Color.Transparent,
                errorBorderColor = ErrorRed,
                focusedTextColor = Color(0xFF202020),
                unfocusedTextColor = Color(0xFF202020),
                cursorColor = RackifyYellow
            ),
            modifier = Modifier.fillMaxWidth()
        )
        if (error != null) {
            Text(
                text = error,
                color = ErrorRed,
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
