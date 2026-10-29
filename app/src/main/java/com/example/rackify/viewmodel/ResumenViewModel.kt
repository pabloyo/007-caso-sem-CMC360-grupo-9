package com.example.rackify.viewmodel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import com.example.rackify.data.RackRepository
import com.example.rackify.model.Rack
import com.example.rackify.model.RackStatus
import com.example.rackify.model.SummaryItem
import com.example.rackify.ui.components.ItemDecision

data class ResumenUiState(
    val rack: Rack? = null,
    val summaryItems: List<SummaryItem> = emptyList(),
    val decisions: Map<String, ItemDecision> = emptyMap()
)

class ResumenViewModel : ViewModel() {

    var uiState by mutableStateOf(ResumenUiState())
        private set

    init {
        loadRack(RackRepository.selectedRackId)
    }

    fun loadRack(rackId: String? = null) {
        val currentRack = (if (rackId != null) RackRepository.getRackById(rackId) else null)
            ?: RackRepository.getRacks().firstOrNull()

        uiState = uiState.copy(
            rack = currentRack,
            summaryItems = currentRack?.toSummaryItems() ?: emptyList()
        )
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

        val updatedRack = uiState.rack?.copy(status = newStatus)
        if (updatedRack != null) {
            RackRepository.updateRackStatus(updatedRack.id, newStatus)
        }

        uiState = uiState.copy(
            rack = updatedRack,
            decisions = newDecisions,
            summaryItems = updatedRack?.toSummaryItems() ?: emptyList()
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
