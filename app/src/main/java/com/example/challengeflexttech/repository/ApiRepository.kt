package com.example.challengeflexttech.repository

import com.example.challengeflexttech.model.Cat
import com.example.challengeflexttech.utils.Constans
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query
import retrofit2.http.Url

interface ApiRepository {

    @GET(value = "images/search?limit=10")
    suspend fun getCats(): List<Cat>
}

/**
 * creacion de un objeto Retrofit con el paton singleton
 * */
object Retrofit{
    val retrofit by lazy {
        Retrofit.Builder().baseUrl(Constans.url).addConverterFactory(GsonConverterFactory.create()).build().create(ApiRepository::class.java)
    }
}