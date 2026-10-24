package com.example.rackify.viewmodel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import com.example.rackify.data.RackRepository
import com.example.rackify.model.Rack

data class DashboardUiState(
    val racks: List<Rack> = emptyList(),
    val isLoading: Boolean = false
)

class DashboardViewModel : ViewModel() {

    var uiState by mutableStateOf(DashboardUiState())
        private set

    init {
        loadRacks()
    }

    private fun loadRacks() {
        uiState = uiState.copy(
            racks = RackRepository.getRacks()
        )
    }
}
