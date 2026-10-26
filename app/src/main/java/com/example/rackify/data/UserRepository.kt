package com.example.rackify.data

import com.example.rackify.data.local.UserDao
import com.example.rackify.data.local.UserEntity

class UserRepository(private val userDao: UserDao) {
    suspend fun registerUser(user: UserEntity) {
        userDao.insertUser(user)
    }
    suspend fun getUserByUsername(username: String): UserEntity? {
        return userDao.getUserByUsername(username)
    }

}