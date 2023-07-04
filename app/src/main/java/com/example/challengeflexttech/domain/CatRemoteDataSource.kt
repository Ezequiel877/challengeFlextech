package com.example.challengeflexttech.domain


import com.example.challengeflexttech.model.Cat
import com.example.challengeflexttech.repository.ApiRepository

class CatRemoteDataSource(private val repository: ApiRepository) {

    /**
     * se utiliza el singleton para obtener los datos
     * */
    suspend fun getCats():List<Cat>{
        return repository.getCats()
    }

}