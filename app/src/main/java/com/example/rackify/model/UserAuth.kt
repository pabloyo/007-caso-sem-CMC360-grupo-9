package com.example.rackify.model

import com.google.gson.annotations.SerializedName

// Para signup
data class UserSignupRequest(
    val name: String,
    val username: String,
    val password: String
)

// Para login
data class UserLoginRequest(
    val username: String,
    val password: String
)

// Respuesta para signup y login (Xano suele devolver el authToken)
data class UserLoginResponse(
    @SerializedName("authToken")
    val authToken: String,
    // Opcional si xano devuelve los datos del usuario en la raiz
    val name: String? = null,
    val email: String? = null
)
