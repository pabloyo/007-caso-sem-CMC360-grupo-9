package com.example.rackify.repository

object FormValidator {
    fun usernameError(username: String): String? = when {
        username.isBlank() -> "Debe ingresar un usuario"
        username.trim().length < 5 -> "El usuario debe tener al menos 5 caracteres"
        else -> null
    }

    fun passwordError(password: String): String? = when {
        password.isBlank() -> "Debe ingresar una contraseña"
        password.length < 8 -> "La contraseña debe tener al menos 8 caracteres"
        else -> null
    }

    fun toUsername(username: String): String {
        return username.trim().lowercase().replace("\\s+".toRegex(), "")
    }

    fun isValid(username: String, password: String): Boolean =
        usernameError(username) == null && passwordError(password) == null

    fun nameError(nombre: String): String? = when {
        nombre.isBlank() -> "Debe ingresar su nombre"
        nombre.trim().length < 2 -> "El nombre debe tener al menos 2 caracteres"
        !nombre.matches("^[a-zA-ZñÑáéíóúÁÉÍÓÚüÜ ]+$".toRegex()) -> "El nombre solo puede contener letras"
        else -> null
    }
}
