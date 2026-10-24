package com.example.rackify.viewmodel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import com.example.rackify.model.Rack
import com.example.rackify.model.RackStatus

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
            racks = listOf(
                Rack("A-01-1", "24-10-2026", RackStatus.PENDIENTE),
                Rack("A-01-2", "24-10-2026", RackStatus.VERIFICADO),
                Rack("A-02-1", "23-10-2026", RackStatus.RECHAZADO),
                Rack("A-02-2", "23-10-2026", RackStatus.VERIFICADO),
                Rack("B-01-1", "22-10-2026", RackStatus.PENDIENTE),
                Rack("B-01-2", "22-10-2026", RackStatus.RECHAZADO),
                Rack("B-02-1", "21-10-2026", RackStatus.VERIFICADO),
                Rack("B-02-2", "21-10-2026", RackStatus.PENDIENTE),
                Rack("C-01-1", "20-10-2026", RackStatus.VERIFICADO),
                Rack("C-01-2", "20-10-2026", RackStatus.RECHAZADO),
                Rack("C-02-1", "19-10-2026", RackStatus.PENDIENTE),
                Rack("C-02-2", "19-10-2026", RackStatus.VERIFICADO),
                Rack("D-01-1", "18-10-2026", RackStatus.RECHAZADO),
                Rack("D-01-2", "18-10-2026", RackStatus.PENDIENTE),
                Rack("D-02-1", "17-10-2026", RackStatus.VERIFICADO)
            )
        )
    }
}
