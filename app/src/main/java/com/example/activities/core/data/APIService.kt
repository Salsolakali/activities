package com.example.activities.core.data

import com.example.activities.features.home.data.JobPositionApi
import retrofit2.Response
import retrofit2.http.GET

interface APIService {

    @GET("positions.json")
    suspend fun getJobs(): Response<List<JobPositionApi>>

}