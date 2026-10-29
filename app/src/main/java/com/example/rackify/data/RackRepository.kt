package com.example.rackify.data

import androidx.compose.runtime.mutableStateListOf
import com.example.rackify.model.Rack
import com.example.rackify.model.RackStatus

object RackRepository {

    var selectedRackId: String? = null

    private val userRacks = mutableStateListOf<Rack>()

    private val templateRacks = listOf(
        Rack("A-01-1", "24-10-2026", RackStatus.PENDIENTE, "Dell PowerEdge R740", "42U", "600 x 1000 x 2000 mm", "1000 kg"),
        Rack("A-01-2", "24-10-2026", RackStatus.VERIFICADO, "HP ProLiant DL380", "48U", "800 x 1200 x 2200 mm", "1200 kg"),
        Rack("A-02-1", "23-10-2026", RackStatus.RECHAZADO, "Cisco UCS C220", "42U", "600 x 1000 x 2000 mm", "900 kg"),
        Rack("A-02-2", "23-10-2026", RackStatus.VERIFICADO, "Lenovo ThinkSystem", "42U", "600 x 1000 x 2000 mm", "1000 kg"),
        Rack("B-01-1", "22-10-2026", RackStatus.PENDIENTE, "APC NetShelter SX", "42U", "600 x 1070 x 1991 mm", "1363 kg")
    )

    fun getRacks(): List<Rack> = userRacks

    fun getRackById(id: String): Rack? = userRacks.find { it.id == id } ?: templateRacks.find { it.id == id }

    fun addScannedRack(): Rack {
        val nextTemplate = templateRacks[userRacks.size % templateRacks.size]
        val newRack = nextTemplate.copy(status = RackStatus.PENDIENTE)
        userRacks.add(0, newRack)
        selectedRackId = newRack.id
        return newRack
    }

    fun updateRackStatus(id: String, newStatus: RackStatus) {
        val index = userRacks.indexOfFirst { it.id == id }
        if (index != -1) {
            userRacks[index] = userRacks[index].copy(status = newStatus)
        }
    }
}
