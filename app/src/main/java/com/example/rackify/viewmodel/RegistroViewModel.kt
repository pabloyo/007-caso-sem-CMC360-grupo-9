package com.example.rackify.viewmodel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.rackify.domain.LoginValidator
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

data class RegistroUiState(
    val nombre: String = "",
    val rol: String = "",
    val usuario: String = "",
    val contrasena: String = "",
    val contrasena2: String = "",
    val contrasenaError: String? = null,
    val Error: String? = null,
    val isLoading: Boolean = false,
    val isCuentaCreada: Boolean = false,
    val usuarioError: String? = null,
    val contrasena1Error: String? = null,
    val nombreError: String? = null
)

class RegistroViewModel : ViewModel() {

    var uiState by mutableStateOf(RegistroUiState())
        private set

    fun onNombreChange(value: String) { uiState = uiState.copy(nombre = value) }
    fun onRolChange(value: String) { uiState = uiState.copy(rol = value) }
    fun onUsuarioChange(value: String) { uiState = uiState.copy(usuario = value) }
    fun onContrasenaChange(value: String) { uiState = uiState.copy(contrasena = value) }
    fun onContrasena2Change(value: String) { uiState = uiState.copy(contrasena2 = value, contrasenaError = null) }

    fun registrar() {
        val nError = LoginValidator.nameError(uiState.nombre)
        val uError = LoginValidator.usernameError(uiState.usuario)
        val c1Error = LoginValidator.passwordError(uiState.contrasena)
        val c2Error = if (uiState.contrasena != uiState.contrasena2) "Las contraseñas no coinciden" else null

        if (nError != null || uError != null || c1Error != null || c2Error != null) {
            uiState = uiState.copy(
                nombreError = nError,
                usuarioError = uError,
                contrasena1Error = c1Error,
                contrasenaError = c2Error
            )
            return
        }

        if (uiState.nombre.isBlank() || uiState.usuario.isBlank() || uiState.contrasena.isBlank()) {
            uiState = uiState.copy(Error = "Por favor complete todos los campos")
            return
        }

        viewModelScope.launch {
            uiState = uiState.copy(
                isLoading = true,
                nombreError = null,
                usuarioError = null,
                contrasena1Error = null,
                contrasenaError = null,
                Error = null
            )

            delay(3000)

            uiState = uiState.copy(
                isLoading = false,
                isCuentaCreada = true
            )
        }
    }
}