package com.example.challengeflexttech.model

import com.google.gson.annotations.SerializedName

data class Cat(
    @SerializedName("id") val id: String,
    @SerializedName("width") val width: Int,
    @SerializedName("height") val height: Int,
    @SerializedName("url") val url: String,
    @SerializedName("breeds") val breeds: List<Breed>
)

data class Breed(
    @SerializedName("weight") val weight: Weight,
    @SerializedName("id") val id: String,
    @SerializedName("name") val name: String,
    @SerializedName("temperament") val temperament: String,
    @SerializedName("origin") val origin: String,
    @SerializedName("country_codes") val countryCodes: String,
    @SerializedName("country_code") val countryCode: String,
    @SerializedName("life_span") val lifeSpan: String,
    @SerializedName("wikipedia_url") val wikipediaUrl: String
)

data class Weight(
    @SerializedName("imperial") val imperial: String,
    @SerializedName("metric") val metric: String
)
