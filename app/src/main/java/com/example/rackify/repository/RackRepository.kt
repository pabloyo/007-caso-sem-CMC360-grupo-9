package com.example.rackify.repository

import com.example.rackify.model.Rack
import com.example.rackify.model.RackStatus
import com.example.rackify.remote.RetrofitInstance
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

object RackRepository {

    var selectedRackId: Int? = null

    private val templateRacks = listOf(
        Rack(rack_code = "A-01-1", date = "24-10-2026", status = RackStatus.PENDIENTE, brand = "Dell PowerEdge R740", unit_capacity = "42U", size = "600 x 1000 x 2000 mm", load_capacity = "1000 kg"),
        Rack(rack_code = "A-01-2", date = "24-10-2026", status = RackStatus.VERIFICADO, brand = "HP ProLiant DL380", unit_capacity = "48U", size = "800 x 1200 x 2200 mm", load_capacity = "1200 kg"),
        Rack(rack_code = "A-02-1", date = "23-10-2026", status = RackStatus.RECHAZADO, brand = "Cisco UCS C220", unit_capacity = "42U", size = "600 x 1000 x 2000 mm", load_capacity = "900 kg"),
        Rack(rack_code = "A-02-2", date = "23-10-2026", status = RackStatus.VERIFICADO, brand = "Lenovo ThinkSystem", unit_capacity = "42U", size = "600 x 1000 x 2000 mm", load_capacity = "1000 kg"),
        Rack(rack_code = "B-01-1", date = "22-10-2026", status = RackStatus.PENDIENTE, brand = "APC NetShelter SX", unit_capacity = "42U", size = "600 x 1070 x 1991 mm", load_capacity = "1363 kg")
    )

    suspend fun addScannedRack(): Rack {
        val response = RetrofitInstance.api.getRacks()
        if (!response.isSuccessful) {
            throw Exception("Failed to get racks")
        }
        val existingRacks = response.body() ?: emptyList()
        val existingCodes = existingRacks.map { it.rack_code }.toSet()

        val nextTemplate = templateRacks.firstOrNull { it.rack_code !in existingCodes }
            ?: templateRacks[existingRacks.size % templateRacks.size]

        return if (nextTemplate.rack_code !in existingCodes) {
            val sdf = SimpleDateFormat("dd-MM-yyyy", Locale.getDefault())
            val todayStr = sdf.format(Date())
            val createResponse = RetrofitInstance.api.createRack(
                nextTemplate.copy(status = RackStatus.PENDIENTE, date = todayStr)
            )
            if (!createResponse.isSuccessful || createResponse.body() == null) {
                throw Exception("Failed to create rack")
            }
            createResponse.body()!!
        } else {
            existingRacks.first { it.rack_code == nextTemplate.rack_code }
        }
    }
}
