package com.example.rackify.viewmodel

import com.example.rackify.model.Rack
import com.example.rackify.model.RackStatus
import com.example.rackify.model.UserLoginRequest
import com.example.rackify.model.UserLoginResponse
import com.example.rackify.model.UserSignupRequest
import com.example.rackify.remote.ApiService
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.StandardTestDispatcher
import kotlinx.coroutines.test.advanceUntilIdle
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.runTest
import kotlinx.coroutines.test.setMain
import org.junit.After
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test
import retrofit2.Response

@OptIn(ExperimentalCoroutinesApi::class)
class DashboardViewModelTest {
    private val dispatcher = StandardTestDispatcher()

    @Before
    fun preparar() {
        Dispatchers.setMain(dispatcher)
    }

    @After
    fun limpiar() {
        Dispatchers.resetMain()
    }

    @Test
    fun `carga los racks y los publica`() = runTest {
        println("INICIO: comprobar la carga de racks.")
        println("PREPARACION: la API falsa devolvera un rack; no se usa internet.")
        val lista = listOf(
            Rack(id = 1, rack_code = "A-01-1", date = "24-11-2026", status = RackStatus.PENDIENTE,
                brand = "Dell PowerEdge R740", unit_capacity = "42U",
                size = "600 x 1000 x 2000 mm", load_capacity = "1000 kg")
        )
        val apiFalsa = object : ApiService {
            override suspend fun signup(request: UserSignupRequest): Response<UserLoginResponse> = TODO()
            override suspend fun login(request: UserLoginRequest): Response<UserLoginResponse> = TODO()
            override suspend fun getRacks(): Response<List<Rack>> = Response.success(lista)
            override suspend fun getRackById(rackId: Int): Response<Rack> = TODO()
            override suspend fun createRack(rack: Rack): Response<Rack> = TODO()
            override suspend fun updateRackStatus(rackId: Int, rack: Rack): Response<Rack> = TODO()
            override suspend fun deleteRack(rackId: Int): Response<Unit> = TODO()
        }
        val viewModel = DashboardViewModel(apiFalsa)

        println("ACCION: iniciar la carga y ejecutar las corrutinas pendientes.")
        assertEquals(emptyList<Rack>(), viewModel.uiState.racks)
        viewModel.loadRacks()
        advanceUntilIdle()

        println("COMPROBACION: la lista publicada debe ser la que devolvio la API falsa.")
        assertEquals(lista, viewModel.uiState.racks)
        println("CORRECTO: se publicaron los racks esperados.")
    }
}
