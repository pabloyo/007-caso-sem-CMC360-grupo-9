package com.example.rackify.viewmodel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel

class ThemeViewModel : ViewModel() {
    @set:JvmName("setIsDarkTheme")
    var isDarkTheme by mutableStateOf(value = true)
        private set

    fun toggleTheme() {
        isDarkTheme = !isDarkTheme
    }

    fun setDarkTheme(isDark: Boolean) {
        isDarkTheme = isDark
    }
}
