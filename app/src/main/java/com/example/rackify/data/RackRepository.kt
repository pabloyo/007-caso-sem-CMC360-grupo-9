package com.example.rackify.data

import com.example.rackify.model.Rack
import com.example.rackify.model.RackStatus

object RackRepository {

    private val racks = listOf(
        Rack("A-01-1", "24-10-2026", RackStatus.PENDIENTE, "Dell PowerEdge R740", "42U", "600 x 1000 x 2000 mm", "1000 kg"),
        Rack("A-01-2", "24-10-2026", RackStatus.VERIFICADO, "HP ProLiant DL380", "48U", "800 x 1200 x 2200 mm", "1200 kg"),
        Rack("A-02-1", "23-10-2026", RackStatus.RECHAZADO, "Cisco UCS C220", "42U", "600 x 1000 x 2000 mm", "900 kg"),
        Rack("A-02-2", "23-10-2026", RackStatus.VERIFICADO, "Lenovo ThinkSystem", "42U", "600 x 1000 x 2000 mm", "1000 kg"),
        Rack("B-01-1", "22-10-2026", RackStatus.PENDIENTE, "APC NetShelter SX", "42U", "600 x 1070 x 1991 mm", "1363 kg"),
        Rack("B-01-2", "22-10-2026", RackStatus.RECHAZADO, "Tripp Lite SR42UB", "42U", "600 x 1000 x 2000 mm", "1360 kg"),
        Rack("B-02-1", "21-10-2026", RackStatus.VERIFICADO, "Eaton RS Rack", "42U", "600 x 1000 x 2000 mm", "1000 kg"),
        Rack("B-02-2", "21-10-2026", RackStatus.PENDIENTE, "Rittal TS IT", "47U", "800 x 1000 x 2200 mm", "1500 kg"),
        Rack("C-01-1", "20-10-2026", RackStatus.VERIFICADO, "Dell PowerEdge R640", "42U", "600 x 1000 x 2000 mm", "1000 kg"),
        Rack("C-01-2", "20-10-2026", RackStatus.RECHAZADO, "HP ProLiant DL360", "36U", "600 x 900 x 1800 mm", "800 kg"),
        Rack("C-02-1", "19-10-2026", RackStatus.PENDIENTE, "Supermicro SuperServer", "42U", "600 x 1000 x 2000 mm", "1100 kg"),
        Rack("C-02-2", "19-10-2026", RackStatus.VERIFICADO, "IBM System x3650", "42U", "600 x 1000 x 2000 mm", "1000 kg"),
        Rack("D-01-1", "18-10-2026", RackStatus.RECHAZADO, "Fujitsu PRIMERGY", "42U", "600 x 1000 x 2000 mm", "950 kg"),
        Rack("D-01-2", "18-10-2026", RackStatus.PENDIENTE, "Oracle Server X8-2", "42U", "600 x 1000 x 2000 mm", "1050 kg"),
        Rack("D-02-1", "17-10-2026", RackStatus.VERIFICADO, "Huawei FusionServer", "42U", "600 x 1000 x 2000 mm", "1000 kg")
    )

    fun getRacks(): List<Rack> = racks

    fun getRackById(id: String): Rack? = racks.find { it.id == id }
}
