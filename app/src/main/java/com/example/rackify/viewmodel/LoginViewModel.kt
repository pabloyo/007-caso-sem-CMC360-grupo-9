package com.example.rackify.viewmodel

import android.app.Application
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.example.rackify.data.UserRepository
import com.example.rackify.data.local.AppDatabase
import com.example.rackify.domain.FormValidator
import kotlinx.coroutines.launch

data class LoginUiState(
    val usuario: String = "",
    val contrasena: String = "",
    val usuarioError: String? = null,
    val contrasenaError: String? = null,
    val generalError: String? = null,
    val isLoading: Boolean = false
)

class LoginViewModel(application: Application) : AndroidViewModel(application) {
    private val database = AppDatabase.getDatabase(application)
    private val repository = UserRepository(database.userDao())

    var uiState by mutableStateOf(LoginUiState())
        private set

    fun onUsuarioChange(value: String) {
        uiState = uiState.copy(usuario = value, usuarioError = null, generalError = null)
    }

    fun onContrasenaChange(value: String) {
        uiState = uiState.copy(contrasena = value, contrasenaError = null, generalError = null)
    }

    fun login(onSuccess: (String) -> Unit) {
        val uError = FormValidator.usernameError(uiState.usuario)
        val pError = FormValidator.passwordError(uiState.contrasena)

        if (uError != null || pError != null) {
            uiState = uiState.copy(
                usuarioError = uError,
                contrasenaError = pError
            )
            return
        }

        viewModelScope.launch {
            uiState = uiState.copy(isLoading = true, generalError = null)

            val existingUser = repository.getUserByUsername(uiState.usuario.trim())

            if (existingUser == null) {
                uiState = uiState.copy(
                    isLoading = false,
                    generalError = "El usuario no existe"
                )
            } else if (existingUser.password != uiState.contrasena) {
                uiState = uiState.copy(
                    isLoading = false,
                    generalError = "Contraseña incorrecta"
                )
            } else {
                uiState = uiState.copy(isLoading = false)
                repository.saveSession(existingUser.username)
                onSuccess(existingUser.username)
            }
        }
    }
}
