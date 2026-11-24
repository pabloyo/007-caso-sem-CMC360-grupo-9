package com.example.rackify.viewmodel

import android.app.Application
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.example.rackify.model.UserLoginRequest
import com.example.rackify.remote.RetrofitInstance
import com.example.rackify.repository.UserRepository
import com.example.rackify.repository.data.AppDatabase
import com.example.rackify.repository.FormValidator
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

            try {
                val request = UserLoginRequest(
                    username = FormValidator.toUsername(uiState.usuario),
                    password = uiState.contrasena
                )
                val response = RetrofitInstance.api.login(request)

                if (response.isSuccessful) {
                    val loginResponse = response.body()
                    if (loginResponse?.authToken != null) {
                        val normalizedUser = uiState.usuario.trim().lowercase().replace("\\s+".toRegex(), "")
                        if (repository.getUserByUsername(normalizedUser) == null) {
                            repository.registerUser(
                                com.example.rackify.repository.data.UserEntity(
                                    username = normalizedUser,
                                    password = "",
                                    role = "",
                                    name = ""
                                )
                            )
                        }
                        repository.saveSession(normalizedUser)
                        uiState = uiState.copy(isLoading = false)
                        onSuccess(normalizedUser)
                    } else {
                        uiState = uiState.copy(
                            isLoading = false,
                            generalError = "Token de sesión no recibido"
                        )
                    }
                } else {
                    val errorBodyString = response.errorBody()?.string()
                    val errorMessage = if (!errorBodyString.isNullOrBlank()) {
                        try {
                            org.json.JSONObject(errorBodyString).getString("message")
                        } catch (e: Exception) {
                            errorBodyString
                        }
                    } else {
                        response.message()
                    }

                    uiState = uiState.copy(
                        isLoading = false,
                        generalError = "Credenciales incorrectas: $errorMessage"
                    )
                }
            } catch (e: Exception) {
                uiState = uiState.copy(
                    isLoading = false,
                    generalError = "Error de conexión: ${e.message}"
                )
            }
        }
    }
}