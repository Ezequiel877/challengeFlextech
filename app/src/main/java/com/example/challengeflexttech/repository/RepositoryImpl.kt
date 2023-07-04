package com.example.challengeflexttech.repository

import com.example.challengeflexttech.domain.CatRemoteDataSource
import com.example.challengeflexttech.model.Cat

/**
 *
 *Implementacion Vista -Modelo
 *
 * */
class RepositoryImpl(private val data:CatRemoteDataSource):Repository  {

    override suspend fun getCat(): List<Cat>{
        return data.getCats()
    }

}