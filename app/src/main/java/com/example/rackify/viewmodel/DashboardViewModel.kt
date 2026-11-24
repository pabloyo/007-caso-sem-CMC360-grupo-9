package com.example.rackify.viewmodel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.rackify.model.Rack
import com.example.rackify.remote.RetrofitInstance
import kotlinx.coroutines.launch

data class DashboardUiState(
    val racks: List<Rack> = emptyList(),
    val isLoading: Boolean = false,
    val error: String? = null
)

class DashboardViewModel : ViewModel() {

    var uiState by mutableStateOf(DashboardUiState())
        private set

    fun loadRacks() {
        viewModelScope.launch {
            uiState = uiState.copy(isLoading = true, error = null)
            try {
                val response = RetrofitInstance.api.getRacks()
                if (response.isSuccessful) {
                    uiState = uiState.copy(
                        racks = response.body() ?: emptyList(),
                        isLoading = false
                    )
                } else {
                    uiState = uiState.copy(
                        isLoading = false,
                        error = "Error al cargar los racks: ${response.message()}"
                    )
                }
            } catch (e: Exception) {
                uiState = uiState.copy(
                    isLoading = false,
                    error = "Error de conexión: ${e.message}"
                )
            }
        }
    }
}
