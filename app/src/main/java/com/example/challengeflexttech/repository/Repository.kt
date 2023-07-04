package com.example.challengeflexttech.repository

import com.example.challengeflexttech.model.Cat


interface Repository {
    suspend fun getCat(): List<Cat>

}