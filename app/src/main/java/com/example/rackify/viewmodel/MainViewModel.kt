package com.example.rackify.viewmodel

import android.app.Application
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.example.rackify.repository.UserRepository
import com.example.rackify.repository.data.AppDatabase
import com.example.rackify.repository.data.UserEntity
import com.example.rackify.navigation.NavigationEvent
import com.example.rackify.navigation.Screen
import android.widget.Toast
import com.example.rackify.repository.RackRepository
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.launch

class MainViewModel(application: Application) : AndroidViewModel(application) {

    private val _navigationEvents = MutableSharedFlow<NavigationEvent>()
    val navigationEvents: SharedFlow<NavigationEvent> = _navigationEvents.asSharedFlow()
    private val database = AppDatabase.getDatabase(application)
    private val repository = UserRepository(database.userDao())

    private var isScanning = false

    var currentUser by mutableStateOf<UserEntity?>(null)
        private set

    init {
        checkActiveSession()
    }

    fun loadCurrentUser() {
        viewModelScope.launch {
            currentUser = repository.getCurrentUser()
        }
    }

    private fun checkActiveSession() {
        viewModelScope.launch {
            val user = repository.getCurrentUser()
            if (user != null) {
                currentUser = user
                navigateTo(
                    screen = Screen.Scanner,
                    popUpTo = Screen.Login,
                    inclusive = true
                )
            }
        }
    }

    fun navigateTo(
        screen: Screen,
        popUpTo: Screen? = null,
        inclusive: Boolean = false,
        singleTop: Boolean = false
    ) {
        viewModelScope.launch {
            _navigationEvents.emit(
                NavigationEvent.NavigateTo(
                    route = screen,
                    popupToRoute = popUpTo,
                    inclusive = inclusive,
                    singleTop = singleTop
                )
            )
        }
    }

    fun navigateBack() {
        viewModelScope.launch {
            _navigationEvents.emit(NavigationEvent.PopBackStack)
        }
    }

    fun escanearRack() {
        if (isScanning) return
        isScanning = true
        viewModelScope.launch {
            try {
                val rack = RackRepository.addScannedRack()
                RackRepository.selectedRackId = rack.id
                navigateTo(Screen.Resumen)
            } catch (e: Exception) {
                Toast.makeText(getApplication(), "No se pudo conectar con el servidor", Toast.LENGTH_SHORT).show()
                navigateTo(
                    screen = Screen.Scanner,
                    popUpTo = Screen.Scanner,
                    inclusive = true
                )
            } finally {
                isScanning = false
            }
        }
    }

    fun logout() {
        viewModelScope.launch {
            repository.clearSession()
            currentUser = null
            navigateTo(
                screen = Screen.Login,
                popUpTo = Screen.Scanner,
                inclusive = true
            )
        }
    }
}
