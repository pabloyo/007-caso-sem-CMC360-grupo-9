package com.example.rackify.data

import com.example.rackify.data.local.SessionEntity
import com.example.rackify.data.local.UserDao
import com.example.rackify.data.local.UserEntity

class UserRepository(private val userDao: UserDao) {
    suspend fun registerUser(user: UserEntity) {
        userDao.insertUser(user)
    }
    suspend fun getUserByUsername(username: String): UserEntity? {
        return userDao.getUserByUsername(username)
    }

    suspend fun saveSession(username: String) {
        userDao.saveSession(SessionEntity(username = username))
    }

    suspend fun getActiveSession(): SessionEntity? {
        return userDao.getActiveSession()
    }

    suspend fun clearSession() {
        userDao.clearSession()
    }

    suspend fun getCurrentUser(): UserEntity? {
        val activeSession = getActiveSession() ?: return null
        return getUserByUsername(activeSession.username)
    }
}