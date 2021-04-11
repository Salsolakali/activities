package com.example.activities.core.data

import com.example.beersmvvm.features.home.data.response.BeerApi
import com.example.beersmvvm.features.home.data.response.BeersResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface APIService {

    @GET("beers")
    suspend fun getBeers(): Response<List<BeerApi>>

    @GET("beers")
    suspend fun getBeersFiltered(@Query("beer_name") query: String): Response<List<BeerApi>>
}