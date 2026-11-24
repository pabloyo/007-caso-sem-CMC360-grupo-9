package com.example.rackify.viewmodel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.rackify.repository.RackRepository
import com.example.rackify.model.Rack
import com.example.rackify.model.RackStatus
import com.example.rackify.model.SummaryItem
import com.example.rackify.remote.RetrofitInstance
import com.example.rackify.ui.components.ItemDecision
import kotlinx.coroutines.launch

data class ResumenUiState(
    val rack: Rack? = null,
    val summaryItems: List<SummaryItem> = emptyList(),
    val decisions: Map<String, ItemDecision> = emptyMap(),
    val isLoading: Boolean = false,
    val error: String? = null
)

class ResumenViewModel : ViewModel() {

    var uiState by mutableStateOf(ResumenUiState())
        private set

    init {
        loadRack(RackRepository.selectedRackId)
    }

    fun loadRack(rackId: Int? = null) {
        val targetId = rackId ?: RackRepository.selectedRackId

        if (targetId == null) {
            uiState = uiState.copy(error = "No se ha seleccionado ningún rack")
            return
        }

        viewModelScope.launch {
            uiState = uiState.copy(isLoading = true, error = null)
            try {
                val response = RetrofitInstance.api.getRackById(targetId)
                if (response.isSuccessful) {
                    val currentRack = response.body()
                    uiState = uiState.copy(
                        rack = currentRack,
                        summaryItems = currentRack?.toSummaryItems() ?: emptyList(),
                        isLoading = false
                    )
                } else {
                    uiState = uiState.copy(
                        isLoading = false,
                        error = "Error al cargar el rack: ${response.message()}"
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

    fun onConfirmItem(label: String) {
        val current = uiState.decisions[label] ?: ItemDecision.UNSET
        val newDecision = if (current == ItemDecision.CONFIRMED) ItemDecision.UNSET else ItemDecision.CONFIRMED
        updateDecision(label, newDecision)
    }

    fun onRejectItem(label: String) {
        val current = uiState.decisions[label] ?: ItemDecision.UNSET
        val newDecision = if (current == ItemDecision.REJECTED) ItemDecision.UNSET else ItemDecision.REJECTED
        updateDecision(label, newDecision)
    }

    private fun updateDecision(label: String, decision: ItemDecision) {
        val newDecisions = uiState.decisions.toMutableMap()
        newDecisions[label] = decision

        val editableLabels = listOf("MARCA Y MODELO", "CAPACIDAD DE UNIDADES", "MEDIDAS", "CAPACIDAD DE CARGA")
        val itemDecisions = editableLabels.map { newDecisions[it] ?: ItemDecision.UNSET }

        val newStatus = when {
            itemDecisions.any { it == ItemDecision.REJECTED } -> RackStatus.RECHAZADO
            itemDecisions.all { it == ItemDecision.CONFIRMED } -> RackStatus.VERIFICADO
            else -> RackStatus.PENDIENTE
        }

        val currentRackId = uiState.rack?.id ?: return

        viewModelScope.launch {
            try {
                // Formatting current date as dd-MM-yyyy
                val sdf = java.text.SimpleDateFormat("dd-MM-yyyy", java.util.Locale.getDefault())
                val todayStr = sdf.format(java.util.Date())

                val currentRack = uiState.rack ?: return@launch
                val updatedRackRequest = currentRack.copy(status = newStatus, date = todayStr)
                
                val response = RetrofitInstance.api.updateRackStatus(currentRackId, updatedRackRequest)

                if (response.isSuccessful) {
                    val updatedRack = response.body() ?: updatedRackRequest
                    uiState = uiState.copy(
                        rack = updatedRack,
                        decisions = newDecisions,
                        summaryItems = updatedRack.toSummaryItems()
                    )
                } else {
                    // Si falla (ej. red intermitente), revertimos la decisión visual
                    uiState = uiState.copy(error = "No se pudo guardar la decisión")
                }
            } catch (e: Exception) {
                uiState = uiState.copy(error = "Error de conexión")
            }
        }
    }

    private fun Rack.toSummaryItems(): List<SummaryItem> = listOf(
        SummaryItem("ID DEL RACK", rack_code),
        SummaryItem("ÚLTIMA MODIFICACIÓN", date),
        SummaryItem("STATUS", status.label),
        SummaryItem("MARCA Y MODELO", brand),
        SummaryItem("CAPACIDAD DE UNIDADES", unit_capacity),
        SummaryItem("MEDIDAS", size),
        SummaryItem("CAPACIDAD DE CARGA", load_capacity)
    )
}