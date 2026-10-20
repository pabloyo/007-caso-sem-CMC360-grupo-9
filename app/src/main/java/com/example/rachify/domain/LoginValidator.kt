package com.example.rachify.domain


object LoginValidator {
    fun usernameError(username: String): String? = when {
        username.isBlank() -> "aqui uno ingresa usario"
        username.trim().length < 3 -> "el nombre del usuario no puede ser muy pequeño"
        else -> null
    }

    fun passwordError(password: String): String? = when {
        password.isBlank() -> "aqui va la contraseña"
        password.length < 4 -> "igual no puede ser muy corto"
        else -> null
    }

    fun isValid(username: String, password: String): Boolean =
        usernameError(username) == null && passwordError(password) == null
}
