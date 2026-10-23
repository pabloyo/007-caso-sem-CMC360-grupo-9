package com.example.rackify.ui.registro

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
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
import androidx.compose.material3.IconButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
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
import com.example.rackify.ui.theme.JetBrainsMono
import com.example.rackify.ui.theme.RackifyOrange
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.CheckCircle
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.TextButton
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.rackify.ui.components.RackifyButton
import com.example.rackify.ui.components.RackifyDropdown
import com.example.rackify.ui.components.RackifyTextField
import com.example.rackify.ui.theme.RackifyGreen
import com.example.rackify.ui.theme.RackifyLightGray
import com.example.rackify.ui.theme.RackifyRed
import com.example.rackify.viewmodel.RegistroViewModel


@Composable
fun RegistroScreen(
    onBackClick: () -> Unit,
    viewModel: RegistroViewModel = viewModel()
) {
    val scrollState = rememberScrollState()
    val state = viewModel.uiState

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
                    contentDescription = "Logo",
                    modifier = Modifier
                        .size(50.dp)
                        .padding(horizontal = 10.dp),
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

            if (state.isCuentaCreada) {

                Icon(
                    imageVector = Icons.Default.CheckCircle,
                    contentDescription = "Check",
                    tint = RackifyGreen,
                    modifier = Modifier.size(150.dp)
                )

                Text(
                    text = "CUENTA CREADA",
                    color = RackifyGreen,
                    fontSize = 20.sp,
                    fontWeight = FontWeight.Bold,
                    fontFamily = JetBrainsMono,
                    modifier = Modifier
                        .padding(vertical = 50.dp)
                )
                Spacer(modifier = Modifier.height(16.dp))
                TextButton(
                    onClick = { onBackClick() },
                ) {
                    Text("Volver al inicio de sesión", color = RackifyOrange, textDecoration = TextDecoration.Underline)
                }

            } else {
                RackifyTextField(
                    label = "Nombre",
                    value = state.nombre,
                    onValueChange = { viewModel.onNombreChange(it) },
                    placeholder = "Ingrese su nombre completo",
                    error = state.nombreError
                )

                val roles = listOf("Inspector", "Especialista Técnico", "Supervisor")
                RackifyDropdown(
                    label = "Rol",
                    selectedValue = state.rol,
                    placeholder = "Seleccione su rol",
                    options = roles,
                    onOptionSelected = { viewModel.onRolChange(it) }
                )

                Spacer(modifier = Modifier.height(16.dp))

                RackifyTextField(
                    label = "Usuario",
                    value = state.usuario,
                    onValueChange = { viewModel.onUsuarioChange(it) },
                    placeholder = "Cree su nombre de usuario",
                    error = state.usuarioError
                )

                RackifyTextField(
                    label = "Contraseña",
                    value = state.contrasena,
                    onValueChange = { viewModel.onContrasenaChange(it) },
                    placeholder = "Cree su contraseña",
                    isPassword = true,
                    error = state.contrasena1Error
                )

                RackifyTextField(
                    label = "Repetir Contraseña",
                    value = state.contrasena2,
                    onValueChange = { viewModel.onContrasena2Change(it) },
                    placeholder = "Verifique su contraseña",
                    isPassword = true,
                    error = state.contrasenaError
                )

                if (state.isLoading) {
                    RackifyButton(
                        text = "",
                        onClick = {null},
                        loading = true,
                        modifier = Modifier
                            .padding(top = 34.dp)
                    )

                } else {
                    RackifyButton(
                        text = "CREAR CUENTA",
                        onClick = { viewModel.registrar() },
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(top = 34.dp)
                    )
                }
            }

            if (state.Error != null) {
                Text(text = state.Error, color = RackifyRed, fontSize = 12.sp)
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