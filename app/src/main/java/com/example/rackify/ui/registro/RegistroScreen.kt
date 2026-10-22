package com.example.rackify.ui.registro

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.imePadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.widthIn
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.rackify.ui.theme.RackifyBlack
import androidx.compose.material3.Text
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import com.example.rackify.ui.theme.JetBrainsMono
import com.example.rackify.ui.theme.RackifyOrange
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.Icon
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import com.example.rackify.ui.components.RackifyDropdown
import com.example.rackify.ui.components.RackifyTextField
import com.example.rackify.ui.theme.RackifyDarkGray
import com.example.rackify.ui.theme.RackifyLightGray
import com.example.rackify.ui.theme.RackifyWhite

import com.example.rackify.ui.theme.Ubuntu

@Composable
fun RegistroScreen(
    onBackClick: () -> Unit
) {
    val scrollState = rememberScrollState()

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(RackifyBlack)
            .imePadding()
            .padding(horizontal = 32.dp),
        contentAlignment = Alignment.TopStart
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .widthIn(max = 340.dp)
                .verticalScroll(scrollState),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.spacedBy(14.dp)
        ) {
            Box(
                modifier = Modifier.fillMaxWidth()
            ){
                IconButton(
                    onClick = onBackClick,
                    modifier = Modifier
                        .align(Alignment.TopStart)
                        .padding(top = 16.dp)
                ) {
                    Icon(
                        imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                        contentDescription = "Volver",
                        tint = RackifyOrange
                    )
                }
            }

            Row(
                horizontalArrangement = Arrangement.Center,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Image(
                    painter = painterResource(id = com.example.rackify.R.drawable.icon),
                    contentDescription = "Logo de Rackify",
                    modifier = Modifier
                        .size(50.dp)
                        .padding(bottom = 15.dp),
                    contentScale = ContentScale.Fit
                )
                Text(
                    text = "CREAR CUENTA",
                    color = RackifyOrange,
                    fontFamily = JetBrainsMono,
                    fontSize = 28.sp,
                    fontWeight = FontWeight.Bold
                )
            }

            Spacer(modifier = Modifier.height(16.dp))

            var nombre by remember { mutableStateOf("") }
            RackifyTextField(
                label = "Nombre",
                value = nombre,
                onValueChange = { nombre = it },
                placeholder = "Ingrese su nombre completo"
            )

            var rolSeleccionado by remember { mutableStateOf("") }
            val roles = listOf("Inspector", "Especialista Técnico", "Supervisor")
            RackifyDropdown(
                label = "Rol",
                selectedValue = rolSeleccionado,
                placeholder = "Seleccione su rol",
                options = roles,
                onOptionSelected = { rolSeleccionado = it }
            )

            Spacer(modifier = Modifier.height(16.dp))

            var usuario by remember { mutableStateOf("") }
            RackifyTextField(
                label = "Usuario",
                value = usuario,
                onValueChange = { usuario = it },
                placeholder = "Cree su nombre de usuario"
            )

            var contraseña by remember { mutableStateOf("") }
            RackifyTextField(
                label = "Contraseña",
                value = contraseña,
                onValueChange = { contraseña = it },
                placeholder = "Cree su contraseña",
                isPassword = true
            )

            var contraseña2 by remember { mutableStateOf("") }
            RackifyTextField(
                label = "Repetir Contraseña",
                value = contraseña2,
                onValueChange = { contraseña2 = it },
                placeholder = "Verifique su contraseña",
                isPassword = true
            )

            Button(
                onClick = {
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
                Text("CREAR CUENTA", fontWeight = FontWeight.SemiBold)
            }
        }
    }
}


@Preview(showBackground = true, showSystemUi = true)
@Composable
fun RegistroScreenPreview() {
    RegistroScreen(
        onBackClick = {}
    )
}