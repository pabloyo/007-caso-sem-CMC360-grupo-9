package com.example.rackify.viewmodel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import com.example.rackify.data.RackRepository
import com.example.rackify.model.Rack
import com.example.rackify.model.SummaryItem

data class ResumenUiState(
    val rack: Rack? = null,
    val summaryItems: List<SummaryItem> = emptyList()
)

class ResumenViewModel : ViewModel() {

    var uiState by mutableStateOf(ResumenUiState())
        private set

    init {
        loadRack()
    }

    fun loadRack(rackId: String? = null) {
        val currentRack = (if (rackId != null) RackRepository.getRackById(rackId) else null)
            ?: RackRepository.getRacks().first()

        uiState = uiState.copy(
            rack = currentRack,
            summaryItems = currentRack.toSummaryItems()
        )
    }

    private fun Rack.toSummaryItems(): List<SummaryItem> = listOf(
        SummaryItem("ID DEL RACK", id),
        SummaryItem("ÚLTIMA MODIFICACIÓN", date),
        SummaryItem("STATUS", status.label),
        SummaryItem("MARCA Y MODELO", brand),
        SummaryItem("CAPACIDAD DE UNIDADES", unitCapacity),
        SummaryItem("MEDIDAS", size),
        SummaryItem("CAPACIDAD DE CARGA", loadCapacity)
    )
}
