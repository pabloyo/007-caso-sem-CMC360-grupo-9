package com.example.rackify.remote

import com.example.rackify.model.Rack
import com.example.rackify.model.UserLoginRequest
import com.example.rackify.model.UserLoginResponse
import com.example.rackify.model.UserSignupRequest
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.DELETE
import retrofit2.http.GET
import retrofit2.http.PATCH
import retrofit2.http.POST
import retrofit2.http.Path

interface ApiService {

    // --- Users (Autenticación) ---
    @POST("auth/signup")
    suspend fun signup(@Body request: UserSignupRequest): Response<UserLoginResponse>

    @POST("auth/login")
    suspend fun login(@Body request: UserLoginRequest): Response<UserLoginResponse>

    // --- Racks ---
    @GET("rack")
    suspend fun getRacks(): Response<List<Rack>>

    @GET("rack/{rack_id}")
    suspend fun getRackById(@Path("rack_id") rackId: Int): Response<Rack>

    @POST("rack")
    suspend fun createRack(@Body rack: Rack): Response<Rack>

    @PATCH("rack/{rack_id}")
    suspend fun updateRackStatus(
        @Path("rack_id") rackId: Int,
        @Body rack: Rack
    ): Response<Rack>

    @DELETE("rack/{rack_id}")
    suspend fun deleteRack(@Path("rack_id") rackId: Int): Response<Unit>
}
