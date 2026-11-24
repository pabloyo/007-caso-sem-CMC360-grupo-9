package com.example.rackify.remote

import com.example.rackify.model.Rack
import com.example.rackify.model.RackStatus
import kotlinx.coroutines.test.runTest
import okhttp3.mockwebserver.MockResponse
import okhttp3.mockwebserver.MockWebServer
import org.junit.After
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class RackApiTest {
    private lateinit var servidor: MockWebServer
    private lateinit var api: ApiService

    @Before
    fun preparar() {
        servidor = MockWebServer()
        servidor.start()
        api = Retrofit.Builder()
            .baseUrl(servidor.url("/").toString())
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(ApiService::class.java)
    }

    @After
    fun limpiar() {
        servidor.shutdown()
    }

    @Test
    fun `consulta el endpoint y convierte la respuesta`() = runTest {
        println("INICIO: comprobar la solicitud y la conversion del JSON.")
        println("PREPARACION: el servidor local simulado respondera con un rack.")
        servidor.enqueue(MockResponse().setBody(
            """[{"id":1,"created_at":1794796390174,"rack_code":"A-01-1","date":"24-11-2026","status":"PENDIENTE","brand":"Dell PowerEdge R740","unit_capacity":"42U","size":"600 x 1000 x 2000 mm","load_capacity":"1000 kg"}]"""
        ))

        println("ACCION: la API llama al servidor local, no a Xano.")
        val resultado = api.getRacks()

        println("COMPROBACION: el JSON debe convertirse en un rack y usar GET /rack.")
        assertEquals(
            listOf(Rack(id = 1, rack_code = "A-01-1", date = "24-11-2026", status = RackStatus.PENDIENTE,
                brand = "Dell PowerEdge R740", unit_capacity = "42U",
                size = "600 x 1000 x 2000 mm", load_capacity = "1000 kg")),
            resultado.body()
        )
        val solicitud = servidor.takeRequest()
        assertEquals("GET", solicitud.method)
        assertEquals("/rack", solicitud.path)
        println("CORRECTO: la solicitud y el rack son los esperados.")
    }
}
