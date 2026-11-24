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
import kotlinx.coroutines.launch

data class ProfileUiState(
    val user: UserEntity? = null,
    val isLoading: Boolean = true
)

class ProfileViewModel(application: Application) : AndroidViewModel(application) {
    private val database = AppDatabase.getDatabase(application)
    private val repository = UserRepository(database.userDao())

    var uiState by mutableStateOf(ProfileUiState())
        private set

    init {
        loadUserProfile()
    }

    private fun loadUserProfile() {
        viewModelScope.launch {
            val user = repository.getCurrentUser()
            uiState = uiState.copy(user = user, isLoading = false)
        }
    }
}